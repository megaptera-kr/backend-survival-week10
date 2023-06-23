package kr.megaptera.backendsurvivalweek10.controllers;

import kr.megaptera.backendsurvivalweek10.BackendSurvivalWeek10Application;
import kr.megaptera.backendsurvivalweek10.infrastructure.UserDetailsDao;
import kr.megaptera.backendsurvivalweek10.security.AccessTokenGenerator;
import kr.megaptera.backendsurvivalweek10.security.AccessTokenService;
import kr.megaptera.backendsurvivalweek10.security.WebSecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ContextConfiguration(classes = {
        BackendSurvivalWeek10Application.class,
        WebSecurityConfig.class,
})
public abstract class ControllerTest {

    @SpyBean
    private AccessTokenService accessTokenService;

    @SpyBean
    protected AccessTokenGenerator accessTokenGenerator;

    @MockBean
    protected UserDetailsDao userDetailsDao;

    protected String userAccessToken;
    protected String adminAccessToken;

    @BeforeEach
    void setUpUserDetailsDaoForAuthentication() {
        userAccessToken = accessTokenGenerator.generate("UserID");
        adminAccessToken = accessTokenGenerator.generate("admin");

        UserDetails userDetails = User.withUsername("UserID")
                .password(userAccessToken)
                .authorities("ROLE_USER")
                .build();

        UserDetails adminDetails = User.withUsername("admin")
                .password(userAccessToken)
                .authorities("ROLE_ADMIN")
                .build();

        given(userDetailsDao.findByAccessToken(userAccessToken))
                .willReturn(Optional.of(userDetails));
        given(userDetailsDao.findByAccessToken(adminAccessToken))
                .willReturn(Optional.of(adminDetails));
    }

}
