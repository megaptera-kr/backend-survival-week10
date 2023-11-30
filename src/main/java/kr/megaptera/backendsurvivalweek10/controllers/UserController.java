package kr.megaptera.backendsurvivalweek10.controllers;

import jakarta.validation.Valid;
import kr.megaptera.backendsurvivalweek10.application.exceptions.UserAlreadyExistsException;
import kr.megaptera.backendsurvivalweek10.application.user.SignupService;
import kr.megaptera.backendsurvivalweek10.dtos.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final SignupService signupService;

    public UserController(SignupService signupService) {
        this.signupService = signupService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponseDto signup(
            @Valid @RequestBody RegisterRequestDto signupRequestDto) {
        String accessToken = signupService.signup(
                signupRequestDto.username().trim(),
                signupRequestDto.password().trim());

        return new RegisterResponseDto(accessToken);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String alreadyExists() {
        return "User Already Exists";
    }
}
