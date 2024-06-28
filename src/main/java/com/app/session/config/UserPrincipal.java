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

    public UserPrincipal(Account account) {
        // TODO authority를 조건에 따라 수정할 것
        super(account.getEmail(), account.getPassword(),
                List.of(
                        new SimpleGrantedAuthority("ROLE_ADMIN")
//                        new SimpleGrantedAuthority("WRITE")
                ));
        this.accountId = account.getId();
    }
}
