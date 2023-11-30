package kr.megaptera.backendsurvivalweek10.controllers;

import kr.megaptera.backendsurvivalweek10.application.user.LoginService;
import kr.megaptera.backendsurvivalweek10.application.user.LogoutService;
import kr.megaptera.backendsurvivalweek10.dtos.LoginRequestDto;
import kr.megaptera.backendsurvivalweek10.dtos.LoginResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/session")
public class SessionController {
    private final LoginService loginService;
    private final LogoutService logoutService;

    public SessionController(LoginService loginService,
                             LogoutService logoutService) {
        this.loginService = loginService;
        this.logoutService = logoutService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return loginService.login(
                loginRequestDto.username(),
                loginRequestDto.password()
        );
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(Authentication authentication) {
        String accessToken = authentication.getCredentials().toString();

        logoutService.logout(accessToken);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String loginFailed() {
        return "Bad Request!";
    }
}
