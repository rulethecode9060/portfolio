package com.portfolio.sns.controller;

import com.portfolio.sns.dto.auth.PrincipalDetails;
import com.portfolio.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * The type User controller.
 */
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    /**
     * @return string
     * @methodName : profile
     * @author : rulethecode9060
     * @date : 2023.07.14
     * @description : 회원 프로필 페이지로 이동
     */
    @GetMapping("/user/profile")
    public String profile(){
        return "/user/profile";
    }

    /**
     * @return string
     * @methodName : profilePage
     * @author : rulethecode9060
     * @date : 2023.07.14
     * @description : 회원 번호에 해당하는 프로필 페이지로 이동
     */
    @GetMapping("/user/{pageUserId}")
    public String profilePage() {
        return "user/profile";
    }

    /**
     * @return string
     * @methodName : update
     * @author : rulethecode9060
     * @date : 2023.07.14
     * @description : 회원 정보 수정 페이지로 이동
     */
    @GetMapping("/user/{id}/update")
    public String update() {
        return "user/update";
    }
}
