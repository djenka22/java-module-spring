

package com.example.javamodule.web;

import com.example.javamodule.domain.entity.Language;
import com.example.javamodule.domain.service.LanguageService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class WebController {
    private final LanguageService service;

    @Autowired
    public WebController(LanguageService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String getHello() {
        return "index";
    }


}
