package com.example.sprintrestanimemvc.providers;

import com.example.sprintrestanimemvc.models.User;
import com.example.sprintrestanimemvc.responses.LoginResponse;
import com.example.sprintrestanimemvc.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpSession;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;

    private final HttpSession session;

    public JwtAuthenticationProvider(UserService userService, HttpSession session) {
        this.userService = userService;
        this.session = session;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            LoginResponse loginResponse = userService.login(authentication.getName(), authentication.getCredentials().toString());
            User user = loginResponse.getUser();
            String accessToken = loginResponse.getAccessToken();

            session.setAttribute("accessToken", accessToken);

            return new UsernamePasswordAuthenticationToken(user, null, user.getRoles());
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                return null;
            }

            throw e;
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
