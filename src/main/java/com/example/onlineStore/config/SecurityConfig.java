package com.example.onlineStore.config;

import com.example.onlineStore.entities.Role;
import com.example.onlineStore.repositories.RoleRepository;
import com.example.onlineStore.security.UserDetailsServicesImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
public class SecurityConfig {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserDetailsServicesImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostConstruct
    public void addRoles() {
        if (!roleRepository.existsById("ROLE_ADMIN"))
            roleRepository.save(new Role("ROLE_ADMIN"));
        if (!roleRepository.existsById("ROLE_USER"))
            roleRepository.save(new Role("ROLE_USER"));

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request->
                        request.requestMatchers("/product/all", "/user/register").permitAll()
                                .requestMatchers("/user/create").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .authenticationManager(authenticationManager(http))
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        return builder.build();
    }
}

