package com.example.ClinicaDental.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Públicos - Auth
                        .requestMatchers("/api/auth/").permitAll()
                        // Públicos - Vistas web (Thymeleaf)
                        .requestMatchers("/", "/doctores/", "/pacientes/").permitAll()
                        .requestMatchers("/h2-console/").permitAll()
                        // API REST - GET público, modificaciones requieren auth
                        .requestMatchers(HttpMethod.GET, "/api/doctores/", "/api/pacientes/").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/doctores/", "/api/pacientes/").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/doctores/", "/api/pacientes/").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/doctores/", "/api/pacientes/").authenticated()
                        // Solo ADMIN puede eliminar
                        //.requestMatchers(HttpMethod.DELETE, "/api/").hasRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // H2 console frames
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                // Agregar filtro JWT antes del filtro de autenticación
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}