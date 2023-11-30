package kr.megaptera.backendsurvivalweek10.security;

import kr.megaptera.backendsurvivalweek10.infrastructure.UserDetailsDao;
import kr.megaptera.backendsurvivalweek10.utils.AccessTokenGenerator;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenService {
    private final AccessTokenGenerator accessTokenGenerator;
    private final UserDetailsDao userDetailsDao;

    public AccessTokenService(AccessTokenGenerator accessTokenGenerator,
                              UserDetailsDao userDetailsDao) {
        this.accessTokenGenerator = accessTokenGenerator;
        this.userDetailsDao = userDetailsDao;
    }

    public Authentication authentication(String accessToken) {
        if (!accessTokenGenerator.verify(accessToken)) {
            return null;
        }

        return userDetailsDao.findByAccessToken(accessToken)
                .map(userDetails -> UsernamePasswordAuthenticationToken.authenticated(
                        userDetails.getUsername(),
                        userDetails.getPassword(),
                        userDetails.getAuthorities()))
                .orElse(null);
    }
}
