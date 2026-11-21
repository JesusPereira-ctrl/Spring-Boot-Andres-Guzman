package com.andres.curso.springboot.app.aop.springbootaop.controllers;

import com.andres.curso.springboot.app.aop.springbootaop.services.GreetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/greeting")
    public ResponseEntity<?> greeting() {
        return ResponseEntity.ok(
                Collections.singletonMap("greeting", greetingService.sayHello("Pepe", "Hola que tal!"))
        );
    }

    @GetMapping("/greeting-error")
    public ResponseEntity<?> greetingError() {
        return ResponseEntity.ok(
                Collections.singletonMap("greeting", greetingService.sayHelloError("Pepe", "Hola que tal!"))
        );
    }
}
