package com.example.employeeservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // marks this class as a source of bean definitions
@EnableWebSecurity // enables Spring Security's web security support

public class SecurityConfig {

    private final JwtFilter jwtFilter;

    SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }
    /**
     * This bean defines HOW security will behave for every HTTP request.
     * Spring Security creates a filter chain based on this configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // http.
        // // Disable CSRF because we are building REST APIs (stateless)
        //     csrf(csrf->csrf.disable()) 
        //     // Require authentication for every request
        //     .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
        //     // Use HTTP Basic authentication
        //     .httpBasic(Customizer.withDefaults());
        http.
            csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/login").
            permitAll()
            .anyRequest().authenticated())
            .addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

    /* *
     * This bean defines HOW user details are retrieved.
     * In a real application, you would implement this to fetch user details from a database or another source.
    */
    @Bean
    public UserDetailsService users() {
        UserDetails user = 
           User.builder()
                .username("Ajay")
                .password(passwordEncoder().encode("password")) // In a real application, passwords should be encoded
                .roles("USER")
                .build();
        // store user details in memory
        return new InMemoryUserDetailsManager(user);
    }

    /*
        * This bean defines the password encoding mechanism.
        * BCrypt is a strong hashing function that is widely used for securely storing passwords.
    */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    }

}
