package com.example.Ships.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.annotation.Resource;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Resource
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder bCrypt;//check appconfig file, same encoder

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(bCrypt);
        return authProvider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {//todo fix this, securityFilterchain cannot be with
        http
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/register/**", "/login").permitAll()
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authProvider())
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }
    //ADD CONFIG FILE TO
}