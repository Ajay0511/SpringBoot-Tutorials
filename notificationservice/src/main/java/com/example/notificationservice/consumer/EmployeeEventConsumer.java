package com.example.notificationservice.consumer;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class EmployeeEventConsumer {

    @Bean
    public Consumer<String> consumeEmployeeEvent() {
        return message -> {
            System.out.println("✅ Employee Event received in Notification Service using Spring Cloud Stream: "
                    + message);
        };
    }
}
