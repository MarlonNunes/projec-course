package com.projectcourse.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.projectcourse.model.User;
import com.projectcourse.service.StudentService;
import com.projectcourse.service.TeacherService;
import com.projectcourse.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTValidateFilter extends BasicAuthenticationFilter {

    public static final String HEADER_ATTRIBUTE = "Authorization";
    public static final String PREFIX_ATTRIBUTE = "Bearer ";

    private TeacherService teacherService;
    private UserService userService;
    private StudentService studentService;

    public JWTValidateFilter(AuthenticationManager authenticationManager,
                             UserService userService) {
        super(authenticationManager);
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String attribute = request.getHeader(HEADER_ATTRIBUTE);

        if(attribute == null || !attribute.startsWith(PREFIX_ATTRIBUTE)){
            chain.doFilter(request, response);
            return;
        }

        String token = attribute.replace(PREFIX_ATTRIBUTE, "");
        UsernamePasswordAuthenticationToken authenticationToken = this.getAuthenticationToken(token);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token){
        String user = JWT.require(Algorithm.HMAC512(JWTAuthenticationFilter.TOKEN_SENHA))
                        .build()
                .verify(token)
                .getSubject();

        if(user == null){
            return null;
        }

        User userDetails = this.userService.loadUserByUsername(user);

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
