package com.example.notificationservice.consumer;

import com.example.notificationservice.model.EmployeeEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EmployeeConsumer {

    @RabbitListener(queues = "employee.queue")
    public void consume(String message) {

        System.out.println("✅ Message received in Notification Service: "
                + message);

        // Here you can:
        // - send email
        // - send SMS
        // - store logs
    }
}
