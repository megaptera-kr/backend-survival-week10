package kr.megaptera.backendsurvivalweek10.dtos;

import jakarta.validation.constraints.NotBlank;

public record RqSignupDto (
    @NotBlank
    String username,

    @NotBlank
    String password
) {
}
