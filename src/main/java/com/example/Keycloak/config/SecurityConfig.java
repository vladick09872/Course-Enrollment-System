package com.example.Keycloak.config;

import com.example.Keycloak.converter.CustomJwtConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-resources/**", "/webjars/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/{id}").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/users/current").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/{id}/isExists").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/all").hasRole("ADMI")
                        .requestMatchers(HttpMethod.POST, "/tasks/create").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/tasks/{taskId}/{userId}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/tasks/update/**").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/tasks/delete/**").hasRole("USER")
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter())))
                .oauth2Login(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public Converter<Jwt, ? extends AbstractAuthenticationToken> jwtConverter() {
        var converter = new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(new CustomJwtConverter());

        return converter;
    }
}
