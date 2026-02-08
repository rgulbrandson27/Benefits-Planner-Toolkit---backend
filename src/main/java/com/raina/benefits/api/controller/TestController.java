package com.raina.benefits.api.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        System.out.println("Hello endpoint has been called!");
        return "Benefits Planner API is running!";
    }

    @PostMapping("/ping")
    public Map<String, Object> ping(@RequestBody Map<String, Object> body) {
        System.out.println("Frontend POSTed to /ping with body: " + body);

        return Map.of(
                "ok", true,
                "message", "pong",
                "received", body
        );
    }
}