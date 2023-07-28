package com.example.eureka_client_2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/new")
public class NewController {

    @GetMapping("/name")
    public String name() {
        return "new client 2";
    }
}
