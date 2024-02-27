package kr.megaptera.backendsurvivalweek10.controllers;

import jakarta.validation.Valid;
import kr.megaptera.backendsurvivalweek10.application.LoginService;
import kr.megaptera.backendsurvivalweek10.application.LogoutService;
import kr.megaptera.backendsurvivalweek10.dtos.LoginRequestDto;
import kr.megaptera.backendsurvivalweek10.dtos.LoginResultDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {
    private final LoginService loginService;
    private final LogoutService logoutService;

    public SessionController(LoginService loginService, LogoutService logoutService) {
        this.loginService = loginService;
        this.logoutService = logoutService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResultDto login(
            @Valid
            @RequestBody
            LoginRequestDto loginRequestDto) {
        return loginService.login(loginRequestDto.username(), loginRequestDto.password());

    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String logout(Authentication authentication) {
        String accessToken = authentication.getCredentials().toString();
        logoutService.logout(accessToken);

        return "Logout";
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String loginFailed() {
        return "Bad Request";
    }
}