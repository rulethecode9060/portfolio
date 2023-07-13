package com.portfolio.sns.controller;

import com.portfolio.sns.dto.auth.SignUpDto;
import com.portfolio.sns.exception.CustomValidationException;
import com.portfolio.sns.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/auth/signin")
    public String signInPage(){
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signUpPage(){
        return "auth/signup";
    }

    @PostMapping("/auth/signup")
    public String signup(@Valid SignUpDto signUpDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            for(FieldError error : bindingResult.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationException("회원 가입 유효성 검증 실패", errorMap);
        }
        authService.signup(signUpDto.toEntity());
        return "redirect:/auth/signin";
    }


}
