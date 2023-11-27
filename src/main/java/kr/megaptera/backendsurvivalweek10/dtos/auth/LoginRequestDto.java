package kr.megaptera.backendsurvivalweek10.dtos.auth;

public record LoginRequestDto(
        String username,
        String password
) {
}
