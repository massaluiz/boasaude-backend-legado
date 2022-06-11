package com.example.poc.boasaude.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DebugController {
    @GetMapping("/test")
    String all() {
        return "Hello World!";
    }
}
