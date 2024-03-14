package com.example.first;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(){
        return new testService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(c -> c.requestMatchers("/api/**").permitAll().requestMatchers("/userData/**","/userDataList/**", "/userDataAdd/**").permitAll())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public AuthenticationProvider authenticationProvider() { 
    //     DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); 
    //     authenticationProvider.setUserDetailsService(UserDetailsService()); 
    //     authenticationProvider.setPasswordEncoder(passwordEncoder()); 
    //     return authenticationProvider; 
    // } 
  
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception { 
        return config.getAuthenticationManager(); 
    } 
}
