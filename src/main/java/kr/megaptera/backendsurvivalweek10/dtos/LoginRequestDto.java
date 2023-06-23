package kr.megaptera.backendsurvivalweek10.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
        @NotBlank
        String username,
        String password) {

}
