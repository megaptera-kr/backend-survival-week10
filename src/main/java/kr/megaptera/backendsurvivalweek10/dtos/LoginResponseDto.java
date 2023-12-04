package kr.megaptera.backendsurvivalweek10.dtos;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public record LoginResponseDto(String accessToken, List<String> roles){
}
