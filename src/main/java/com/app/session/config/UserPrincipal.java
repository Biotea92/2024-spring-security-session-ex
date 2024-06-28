package com.app.session.config;

import com.app.session.entity.Account;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class UserPrincipal extends User {

    private final Long accountId;

    // role: 역할 -> 관리자, 사용자, 매니저
    // authority: 권한 -> 글쓰기, 글 읽기, 사용자 정지 시키기 등

    public UserPrincipal(Account account, boolean isUser) {
        super(account.getEmail(), account.getPassword(), isUser ? getRoleUser() : getRoleAdmin());
        this.accountId = account.getId();
    }

    private static List<SimpleGrantedAuthority> getRoleAdmin() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_USER")
        );
    }

    private static List<SimpleGrantedAuthority> getRoleUser() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_USER")
        );
    }
}
