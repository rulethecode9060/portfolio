package com.portfolio.sns.controller;

import com.portfolio.sns.dto.auth.PrincipalDetails;
import com.portfolio.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("/user/profile")
    public String profile(){
        return "/user/profile";
    }

    @GetMapping("/user/{pageUserId}")
    public String profilePage() {
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update() {
        return "user/update";
    }
}
