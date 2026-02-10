package com.example.notificationservice.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EmployeeRabbitConsumer {

    @RabbitListener(queues = "employee.queue")
    public void consume(String message) {

        System.out.println("✅ Message received in Notification Service using rabbit mq: "
                + message);

        // Here you can:
        // - send email
        // - send SMS
        // - store logs
    }
}
