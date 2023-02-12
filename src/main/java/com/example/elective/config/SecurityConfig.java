package com.example.elective.config;

import com.example.elective.security.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@ComponentScan("com.example.elective")
public class SecurityConfig {

  @Autowired
  private UserDetailsService accountDetailsService;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
    return security.csrf().disable()
        .authorizeRequests()
        .antMatchers("/auth/login", "/auth/signup").permitAll()
        .anyRequest().authenticated()
        .and().formLogin()
        .loginPage("/auth/login")
        .loginProcessingUrl("/auth/login")
        .defaultSuccessUrl("/courses/all", true)
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
