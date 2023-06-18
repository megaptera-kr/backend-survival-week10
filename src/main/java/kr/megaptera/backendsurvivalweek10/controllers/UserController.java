package kr.megaptera.backendsurvivalweek10.controllers;

import jakarta.validation.Valid;
import kr.megaptera.backendsurvivalweek10.application.user.SignupService;
import kr.megaptera.backendsurvivalweek10.dtos.RpSignupDto;
import kr.megaptera.backendsurvivalweek10.dtos.RqSignupDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final SignupService signupService;

    public UserController(SignupService signupService) {
        this.signupService = signupService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    public RpSignupDto signup(@Valid @RequestBody RqSignupDto dto) {
        return signupService.signup(dto.username().trim(), dto.password().trim());
    }
}
