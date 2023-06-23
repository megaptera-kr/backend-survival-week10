package kr.megaptera.backendsurvivalweek10.application.user;

import io.hypersistence.tsid.TSID;
import jakarta.transaction.Transactional;
import kr.megaptera.backendsurvivalweek10.dtos.SignupResultDto;
import kr.megaptera.backendsurvivalweek10.exceptions.UserAlreadyExistsException;
import kr.megaptera.backendsurvivalweek10.infrastructure.UserDetailsDao;
import kr.megaptera.backendsurvivalweek10.security.AccessTokenGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SignupService {
    private final AccessTokenGenerator accessTokenGenerator;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsDao userDetailsDao;

    public SignupService(AccessTokenGenerator accessTokenGenerator, PasswordEncoder passwordEncoder, UserDetailsDao userDetailsDao) {
        this.accessTokenGenerator = accessTokenGenerator;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsDao = userDetailsDao;
    }

    public SignupResultDto signup(String username, String password) {
        if (userDetailsDao.existsByUsername(username)) {
            throw new UserAlreadyExistsException();
        }

        String id = TSID.Factory.getTsid().toString();
        String encodedPassword = passwordEncoder.encode(password);
        String accessToken = accessTokenGenerator.generate(id);

        userDetailsDao.addUser(id, username, encodedPassword);
        userDetailsDao.addAccessToken(id, accessToken);

        return new SignupResultDto(accessToken);
    }
}
