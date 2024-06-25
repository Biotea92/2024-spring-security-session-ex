package com.app.session.service;

import com.app.session.controller.request.UserCreateRequest;
import com.app.session.entity.Account;
import com.app.session.entity.User;
import com.app.session.repository.AccountRepository;
import com.app.session.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(UserCreateRequest request) {
        String email = request.email();
        checkDuplicateEmail(email);

        String encodedPassword = passwordEncoder.encode(request.password());
        Account newAccount = Account.create(email, encodedPassword);
        User newUser = User.create(newAccount, request.nickname());
        accountRepository.save(newAccount);
        userRepository.save(newUser);
    }

    private void checkDuplicateEmail(String email) {
        accountRepository.findByEmail(email)
                .ifPresent(account -> {
                    throw new IllegalArgumentException("이미 사용중인 이메일입니다.");
                });
    }
}
