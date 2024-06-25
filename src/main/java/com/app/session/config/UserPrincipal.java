package com.app.session.config;

import com.app.session.entity.Account;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class UserPrincipal extends User {

    private final Long accountId;

    public UserPrincipal(Account account) {
        // TODO authority를 조건에 따라 수정할 것
        super(account.getEmail(), account.getPassword(), List.of(new SimpleGrantedAuthority("ADMIN")));
        this.accountId = account.getId();
    }
}
