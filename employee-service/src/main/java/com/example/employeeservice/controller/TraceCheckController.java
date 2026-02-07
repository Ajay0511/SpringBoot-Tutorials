package com.example.employeeservice.controller;

import io.micrometer.tracing.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TraceCheckController {

    private final Tracer tracer;

    public TraceCheckController(Tracer tracer) {
        this.tracer = tracer;
    }

    @GetMapping("/trace-id")
    public String trace() {
        return "Trace Id: " + tracer.currentSpan().context().traceId();
    }
}
