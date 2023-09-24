package kr.megaptera.backendsurvivalweek10.dtos.auth;

import jakarta.validation.constraints.NotBlank;

public record SignupRequestDto(
        @NotBlank
        String username,
        String password
) {
}
