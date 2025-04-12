package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // This will allow requests from React's default port
public class HomeController {

    @GetMapping("/api/hello")
    public ApiResponse welcome() {
        return new ApiResponse("Welcome to Spring Boot + React Application!");
    }
}

class ApiResponse {
    private String message;

    public ApiResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
