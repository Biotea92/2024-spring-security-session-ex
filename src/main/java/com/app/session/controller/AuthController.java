package com.app.session.controller;

import com.app.session.controller.request.UserCreateRequest;
import com.app.session.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public String login() {
        return "로그인 페이지입니다.";
    }

    @PostMapping("/signup")
    public void signup(@RequestBody UserCreateRequest request) {
        userService.register(request);
    }
}
