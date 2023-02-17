package com.example.elective.config;

import com.example.elective.models.Role;
import com.example.elective.security.AccountDetails;
import com.example.elective.security.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
@ComponentScan("com.example.elective")
public class SecurityConfig {

  private final UserDetailsService accountDetailsService;

  @Autowired
  public SecurityConfig(UserDetailsService accountDetailsService) {
    this.accountDetailsService = accountDetailsService;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
    return security
        .authorizeHttpRequests(authorize ->
            authorize.antMatchers("/auth/login", "/auth/signup").permitAll()
                .antMatchers("/courses/enroll/**",
                    "/courses/available", "/courses/ongoing",
                    "/courses/registered", "/courses/completed")
                .hasRole("STUDENT")
                .antMatchers("/students",
                    "/teachers", "/courses/**",
                    "/teachers/register")
                .hasRole("ADMIN")
                .antMatchers("/teachers/**")
                .hasRole("TEACHER")
                .anyRequest().authenticated())
        .formLogin()
        .loginPage("/auth/login")
        .loginProcessingUrl("/auth/login")
        .successHandler((req, resp, auth) -> {
          AccountDetails details = (AccountDetails) auth.getPrincipal();
          Role role = details.account().getRole();
          resp.sendRedirect(role.getHomeUrl());
        })
        .failureUrl("/auth/login?error")
        .and().logout()
        .logoutUrl("/auth/logout")
        .logoutSuccessUrl("/auth/login")
        .and().userDetailsService(accountDetailsService)
        .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
