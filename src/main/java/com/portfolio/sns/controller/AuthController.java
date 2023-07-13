package com.portfolio.sns.controller;

import com.portfolio.sns.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/auth/signin")
    public String signInPage(){
        return "auth/signin";
    }


}
