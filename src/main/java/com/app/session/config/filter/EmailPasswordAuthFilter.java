package com.app.session.config.filter;

import com.app.session.config.wrapper.CustomHttpServletRequestWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.RememberMeServices;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EmailPasswordAuthFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper;
    private RememberMeServices rememberMeServices;

    public EmailPasswordAuthFilter(String loginUrl, ObjectMapper objectMapper) {
        super(loginUrl);
        this.objectMapper = objectMapper;
    }

    @Override
    public void setRememberMeServices(RememberMeServices rememberMeServices) {
        this.rememberMeServices = rememberMeServices;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        EmailPassword emailPassword = objectMapper.readValue(request.getInputStream(), EmailPassword.class);

        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(
                emailPassword.getEmail(),
                emailPassword.getPassword()
        );

        token.setDetails(this.authenticationDetailsSource.buildDetails(request));
        // JSON에서 rememberMe를 읽어서 request 파라미터로 설정
        Map<String, String[]> additionalParams = new HashMap<>();
        additionalParams.put("rememberMe", new String[]{String.valueOf(emailPassword.isRememberMe())});

        HttpServletRequest wrappedRequest = new CustomHttpServletRequestWrapper(request, additionalParams);

        Authentication authResult = this.getAuthenticationManager().authenticate(token);

        if (emailPassword.isRememberMe()) {
            rememberMeServices.loginSuccess(wrappedRequest, response, authResult);
        }

        return authResult;
    }

    @Getter
    private static class EmailPassword {
        private String email;
        private String password;
        private boolean rememberMe;
    }
}
