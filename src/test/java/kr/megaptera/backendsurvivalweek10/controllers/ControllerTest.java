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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.mockito.BDDMockito.given;


@ContextConfiguration(classes = {
        BackendSurvivalWeek10Application.class,
        WebSecurityConfig.class,
})
@ActiveProfiles("test")
public abstract class ControllerTest {
    @MockBean
    protected UserDetailsDao userDetailsDao;
    @SpyBean
    private AccessTokenService accessTokenService;
    @SpyBean
    protected AccessTokenGenerator accessTokenGenerator;

    protected static final String USER_ID = "UserId";
    protected static final String ADMIN_ID = "AdminId";

    protected String userAccessToken;
    protected String adminAccessToken;


    @BeforeEach
    void setUpUserDetailsDaoForAuthentication() {
        userAccessToken = accessTokenGenerator.generate(USER_ID);
        adminAccessToken = accessTokenGenerator.generate(ADMIN_ID);


        UserDetails user = User.withUsername(USER_ID)
                .password(userAccessToken)
                .authorities("ROLE_USER")
                .build();

        given(userDetailsDao.findByAccessToken(userAccessToken))
                .willReturn(Optional.of(user));

        UserDetails admin = User.withUsername(ADMIN_ID)
                .password(adminAccessToken)
                .authorities("ROLE_ADMIN")
                .build();

        given(userDetailsDao.findByAccessToken(adminAccessToken))
                .willReturn(Optional.of(admin));
    }
}