package com.app.session.controller.request;

public record UserCreateRequest(String email, String password, String nickname, Boolean isAdmin) {

    public UserCreateRequest(String email, String password, String nickname, Boolean isAdmin) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.isAdmin = isAdmin != null && isAdmin;
    }
}
