package kr.megaptera.backendsurvivalweek10.dtos;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequestDto(@NotBlank String username, @NotBlank String password){
}
