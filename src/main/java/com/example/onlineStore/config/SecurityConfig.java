package com.example.onlineStore.config;

import com.example.onlineStore.security.UserDetailsServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final static String[] allowedetEndpoint={"/user/register", "/product/all", "/"};

    private final UserDetailsServicesImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        return builder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           AuthenticationManager authenticationManager) throws Exception {
        http
                .authenticationManager(authenticationManager)
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(allowedetEndpoint).permitAll()


                        .requestMatchers(HttpMethod.POST, "/api/orders/").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/product/all").authenticated()

                        .anyRequest().permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}