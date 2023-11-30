package kr.megaptera.backendsurvivalweek10.application.user;

import jakarta.transaction.Transactional;
import kr.megaptera.backendsurvivalweek10.infrastructure.UserDetailsDao;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LogoutService {
    private final UserDetailsDao userDetailsDao;

    public LogoutService(UserDetailsDao userDetailsDao) {
        this.userDetailsDao = userDetailsDao;
    }

    public void logout(String accessToken) {
        userDetailsDao.removeAccessToken(accessToken);
    }
}