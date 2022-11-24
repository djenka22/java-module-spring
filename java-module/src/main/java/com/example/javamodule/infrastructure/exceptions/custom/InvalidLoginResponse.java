package com.example.javamodule.infrastructure.exceptions.custom;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvalidLoginResponse {
    private String username;
    private String password;

    public InvalidLoginResponse() {
        this.username = "Invalid e-mail adress!";
        this.password = "Invalid password!";
    }
}
