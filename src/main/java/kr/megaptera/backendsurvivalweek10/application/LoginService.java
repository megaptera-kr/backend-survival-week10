package kr.megaptera.backendsurvivalweek10.application;

import jakarta.transaction.Transactional;
import kr.megaptera.backendsurvivalweek10.dtos.LoginResultDto;
import kr.megaptera.backendsurvivalweek10.infrastructure.UserDetailsDao;
import kr.megaptera.backendsurvivalweek10.security.AccessTokenGenerator;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Transactional
public class LoginService {
    private final AccessTokenGenerator accessTokenGenerator;

    private final UserDetailsDao userDetailsDao;
    private final PasswordEncoder passwordEncoder;

    public LoginService(AccessTokenGenerator accessTokenGenerator,
                        UserDetailsDao userDetailsDao, PasswordEncoder passwordEncoder) {
        this.accessTokenGenerator = accessTokenGenerator;
        this.userDetailsDao = userDetailsDao;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResultDto login(String username, String password) {
        return userDetailsDao.findByUsername(username)
                .filter(userDetails ->
                        passwordEncoder.matches(
                                password, userDetails.getPassword()))
                .map(userDetails -> {
                    String id = userDetails.getUsername();
                    String accessToken = accessTokenGenerator.generate(id);
                    userDetailsDao.addAccessToken(id, accessToken);
                    return new LoginResultDto(accessToken,
                            userDetails.getAuthorities()
                                    .stream().map(Object::toString)
                                    .collect(Collectors.toList()));
                })
                .orElseThrow(() -> new BadCredentialsException("Login failed"));
    }
}