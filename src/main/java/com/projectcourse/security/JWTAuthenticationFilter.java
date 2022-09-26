package com.projectcourse.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectcourse.model.User;
import com.projectcourse.service.TeacherService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Log4j2
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final int TOKEN_EXPIRATION = 600000;

    public static final String TOKEN_SENHA = "0cb54710-a7a4-4373-899c-587b4834c57c";
    private final AuthenticationManager authenticationManager;
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User usuario = (User) authResult.getPrincipal();

        String token = JWT.create().withSubject(usuario.getUsername())
                                    .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                                    .sign(Algorithm.HMAC512(TOKEN_SENHA));

        response.getWriter().write("{\"Authorization\": \"" +  token + "\"}");
        response.getWriter().flush();
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            User usuario = new ObjectMapper().readValue(request.getInputStream(), User.class);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                                                                                              usuario.getUsername(),
                                                                                              usuario.getPassword());

            setDetails(request, usernamePasswordAuthenticationToken);
            return this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }catch (IOException e){
            throw new RuntimeException("Falha na autenticação", e);
        }
    }
}
