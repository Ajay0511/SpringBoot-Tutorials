package com.example.employeeservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    
    @Bean
    // @LoadBalanced.  //commnenting bcoz eureka is not used here
    //Bean must be public, Because Spring uses subclass-based proxies (CGLIB) to intercept @Bean methods, and private methods cannot be overridden.
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
