package com.prm.spring.jpa;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.prm.spring.jpa") 
@EnableJpaRepositories(basePackages = "com.prm.spring.jpa") 
public class AppConfig {
    // Any additional configuration or bean definitions can go here
}