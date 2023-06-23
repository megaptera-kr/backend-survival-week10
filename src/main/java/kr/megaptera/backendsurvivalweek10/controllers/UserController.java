package kr.megaptera.backendsurvivalweek10.controllers;

import jakarta.validation.Valid;
import kr.megaptera.backendsurvivalweek10.application.user.SignupService;
import kr.megaptera.backendsurvivalweek10.dtos.SignupRequestDto;
import kr.megaptera.backendsurvivalweek10.dtos.SignupResultDto;
import kr.megaptera.backendsurvivalweek10.exceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final SignupService signupService;


    public UserController(SignupService signupService) {
        this.signupService = signupService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SignupResultDto signup(
            @Valid @RequestBody SignupRequestDto signupRequestDto) {
        return signupService.signup(
                signupRequestDto.username().trim(),
                signupRequestDto.password().trim());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String alreadyExists() {
        return "User already exists.";
    }
}
