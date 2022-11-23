package com.example.javamodule.web;

import com.example.javamodule.domain.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LanguageController {
    private final LanguageService service;

    @Autowired
    public LanguageController(LanguageService service) {
        this.service = service;
    }


    @GetMapping("/hello-rest")
    public ResponseEntity<?> getHelloWorld() {
        return ResponseEntity.ok("Hello world");
    }

    @GetMapping("/hello")
    public ResponseEntity<?> findHelloWorld(@RequestParam String language) {
        String value = service.findHelloWorld(language);
        return ResponseEntity.ok(value);
    }
}
