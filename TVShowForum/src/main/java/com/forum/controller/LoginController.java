package com.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {

        return "login/login-form";
    }

    // add request mapping for /access-denied

    @GetMapping("/accessDenied")
    public String showAccessDenied() {

        return "access-denied";
    }
}
