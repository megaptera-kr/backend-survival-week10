package kr.megaptera.backendsurvivalweek10.application.auth;

import jakarta.transaction.Transactional;
import kr.megaptera.backendsurvivalweek10.dtos.auth.LoginResultDto;
import kr.megaptera.backendsurvivalweek10.infrastructure.UserDetailsDao;
import kr.megaptera.backendsurvivalweek10.util.AccessTokenGenerator;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Transactional
public class LoginService {
    private final AccessTokenGenerator accessTokenGenerator;

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsDao userDetailsDao;

    public LoginService(AccessTokenGenerator accessTokenGenerator,
                        PasswordEncoder passwordEncoder,
                        UserDetailsDao userDetailsDao) {
        this.accessTokenGenerator = accessTokenGenerator;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsDao = userDetailsDao;
    }

    public LoginResultDto login(String username, String password) {
        return userDetailsDao.findByUsername(username)
                .filter(userDetails -> passwordEncoder.matches(
                        password, userDetails.getPassword()))
                .map(userDetails -> {
                    String id = userDetails.getUsername();
                    String accessToken = accessTokenGenerator.generate(id);
                    userDetailsDao.addAccessToken(id, accessToken);
                    return new LoginResultDto(
                            accessToken,
                            userDetails
                                    .getAuthorities()
                                    .stream().map(Object::toString)
                                    .collect(Collectors.toList()));
                })
                .orElseThrow(() -> new BadCredentialsException("Login failed"));
    }
}
