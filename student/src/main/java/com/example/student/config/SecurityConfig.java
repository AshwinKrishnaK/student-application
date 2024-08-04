package com.example.student.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((request) ->
                        request.requestMatchers("/student/**").hasAnyRole("STUDENT","ADMIN")
                                .requestMatchers("/teacher/**").hasAnyRole("TEACHER","ADMIN")
                                .requestMatchers("/actuator/**").hasRole("ADMIN")
                                .requestMatchers("/weather/**").permitAll()
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/logout").permitAll()
                                .requestMatchers("/public/**").permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
