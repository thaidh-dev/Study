package com.example.demo.controller;

import com.example.demo.model.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping("/hello-world")
    public String dummyItem() {
        return "Hello world";
    }
}
