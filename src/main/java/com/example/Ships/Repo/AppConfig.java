package com.example.Ships.Repo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan("com.example.Ships.Repo")
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Autowired
    Environment environment; // check applications.properties files

    private final String URL = "spring.datasource.url";
    private final String USER = "spring.datasource.username";
    private final String DRIVER = "spring.datasource.driverClassName";
    private final String PASSWORD = "spring.datasource.password";

    @Bean
    DataSource dataSource() {// grab details to be able to connect to my database, then use those parameters to use JDBC template, not JDBC
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(environment.getProperty(URL));
        driverManagerDataSource.setUsername(environment.getProperty(USER));
        driverManagerDataSource.setPassword(environment.getProperty(PASSWORD));
        driverManagerDataSource.setDriverClassName(environment.getProperty(DRIVER));
        return driverManagerDataSource;
    }

    @Bean
    JdbcTemplate template() { //a way of to connect to database and execute sql queries
        return new JdbcTemplate(dataSource());
    }
    @Bean
    PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }// a hashing algorithm to turn prevent plain text passwords to gibberish in database,
}
