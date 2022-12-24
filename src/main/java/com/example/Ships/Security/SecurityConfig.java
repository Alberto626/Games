package com.example.Ships.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {//fix this later for https://websparrow.org/spring/spring-boot-security-how-to-change-default-login-page

    @Override
    protected void configure(HttpSecurity security) throws Exception
    {
        security.httpBasic().disable();//remove springs default login page
    }
}