package com.projectcourse.security;

import com.projectcourse.service.AdministratorService;
import com.projectcourse.service.StudentService;
import com.projectcourse.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final StudentService studentService;

    private final TeacherService teacherService;

    private final AdministratorService administratorService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/student/**", "/teacher/**").hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser("Marlon")
                .password(passwordEncoder.encode("Marlon"))
                .roles("ADMIN");

        auth.userDetailsService(studentService).passwordEncoder(passwordEncoder);
        auth.userDetailsService(teacherService).passwordEncoder(passwordEncoder);
        auth.userDetailsService(administratorService).passwordEncoder(passwordEncoder);
    }
}
