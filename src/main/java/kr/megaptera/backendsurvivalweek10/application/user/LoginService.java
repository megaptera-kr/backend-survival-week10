package kr.megaptera.backendsurvivalweek10.application.user;

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
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsDao userDetailsDao;


    public LoginService(AccessTokenGenerator accessTokenGenerator, PasswordEncoder passwordEncoder, UserDetailsDao userDetailsDao) {
        this.accessTokenGenerator = accessTokenGenerator;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsDao = userDetailsDao;
    }

    public LoginResultDto login(String username, String password) {
        return userDetailsDao.findByUsername(username)
                .filter(userDetails -> passwordEncoder.matches(
                        password, userDetails.getPassword()
                ))
                .map(userDetails -> {
                    String userId = userDetails.getUsername();
                    String accessToken = accessTokenGenerator.generate(userId);
                    userDetailsDao.addAccessToken(userId, accessToken);
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
