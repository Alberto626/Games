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
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.annotation.Resource;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Resource
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder bCrypt;//check appconfig file, same encoder

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {//a way of processing a specific type of authentication, I gave my userdetailsService to tell it to process it my way
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(bCrypt);
        return authProvider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {//type of configuration for authentication
        http
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/register", "/login").permitAll() // prevent access to the website without a login
                        .anyRequest().authenticated()
                )

                .authenticationProvider(authProvider())// give spring security my custom way of authenticating
                .formLogin((form) -> form //use my login page, but use springs security authentication.
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                .sessionManagement(session -> session //prevent multiple sessions of a single user, no one user in multiple locations
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true)
                )
                .csrf().disable();//TODO find a way to prevent fetch without disabling this


        return http.build();
    }
    //ADD CONFIG FILE TO
}