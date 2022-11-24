

package com.example.javamodule.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class WebController {

    @GetMapping("/hello")
    public String getHello() {
        return "index";
    }

    @PostMapping("/create-language-pair")
    @ResponseBody
    public String createHello() {
        return "Hello World";
    }


}
