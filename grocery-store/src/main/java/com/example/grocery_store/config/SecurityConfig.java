package com.example.grocery_store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        // Disables CSRF protection
        .csrf(csrf -> csrf.disable())

        // Configures authorization rules
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll() // Allows access to H2 console
            .anyRequest().authenticated() // Requires authentication for all other requests
        )

        // Enables HTTP Basic authentication
        .httpBasic(Customizer.withDefaults());

    // Disables X-Frame-Options to allow H2 console to be embedded in a frame
    http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

    return http.build();
  }
}
