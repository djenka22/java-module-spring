package com.example.javamodule.web;

import com.example.javamodule.domain.entity.Language;
import com.example.javamodule.domain.service.LanguageService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
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

    @GetMapping("/api/hello")
    public ResponseEntity<?> findHelloWorld(@RequestParam String language) {
        String value = service.findHelloWorld(language);
        return ResponseEntity.ok(value);
    }
    @PostMapping("/hello")
    public ResponseEntity<?> createLanguagePair(@RequestBody Pair pair) {
        Language language = service.saveNewPair(pair.language, pair.value);
        return ResponseEntity.ok(language);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Pair {
        String language;
        String value;
    }

    @PostMapping("api/translate")
    public ResponseEntity<?> callAnotherApi(@RequestBody Pair pair) {
        return ResponseEntity.ok(service.callAnotherApi(pair.value, pair.language));
    }

    /*

    @GetMapping
    public ModelAndView index() {
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    */
}
