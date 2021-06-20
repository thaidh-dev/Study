package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
    @GetMapping("/")
    public String showIndex() {
        System.out.println("chuẩn bị nhả ra view");
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        System.out.println("about");
        return "index";
    }

    @GetMapping("/contact")
    public String contact() {
        System.out.println("contact");
        return "index";
    }
}
