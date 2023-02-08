package com.anaboru.springsecurity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BeforeAuthController {

    @GetMapping("/")
    public String loginRedirect() {
        return "redirect:/login";
    }
}
