package com.app.session.controller;

import com.app.session.config.UserPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String mainPage() {
        return "메인 페이지입니다.";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user")
    public String userPage(@AuthenticationPrincipal UserPrincipal principal) {
        Long accountId = principal.getAccountId();
        return accountId + " :: 유저 페이지입니다. 🤣";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String adminPage() {
        return "관리자 페이지입니다. 🥰";
    }

}
