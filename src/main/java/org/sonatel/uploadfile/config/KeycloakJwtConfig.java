package org.sonatel.uploadfile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.beans.factory.annotation.Value;

    @Configuration
    @EnableWebSecurity
    @EnableMethodSecurity(jsr250Enabled = true)
    public class KeycloakJwtConfig {
        @Value("${springdoc.security.oauth2.resourceserver.jwt.set-uri}")
        private String jwtseturi;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            return http
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(
                            authorize ->
                                    authorize
                                            //.requestMatchers(POST, "user/login").permitAll()
                                            .requestMatchers(
                                                    "/auth/**", "/error", "/swagger-ui/**", "/v1/api-docs/**", "/v3/api-docs/**",
                                                    "/swagger-resources/**", "/error/**", "/webjars/", "/api-docs/**", "user/login", "/monitoring/**", "/api/public/**","user/add"
                                            ).permitAll()
                                            .anyRequest().authenticated()

                    ).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .build();
        }


        @Bean
        public JwtDecoder jwtDecoder() {
            System.out.println("JWT Set URI: " + jwtseturi);
            return NimbusJwtDecoder.withJwkSetUri(jwtseturi).build();
        }

}
