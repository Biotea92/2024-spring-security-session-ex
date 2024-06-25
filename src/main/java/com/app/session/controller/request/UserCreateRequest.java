package com.app.session.controller.request;

public record UserCreateRequest(String email, String password, String nickname) {
}
