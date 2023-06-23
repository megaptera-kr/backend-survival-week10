package kr.megaptera.backendsurvivalweek10.dtos;

import java.util.List;

public record LoginResultDto(
        String accessToken,
        List<String> roles
) {
}

