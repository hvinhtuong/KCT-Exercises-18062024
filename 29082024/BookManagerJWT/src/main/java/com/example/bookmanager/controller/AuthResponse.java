package com.example.bookmanager.controller;

import lombok.Getter;

@Getter
public class AuthResponse {
    // Getter cho token
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }

}
