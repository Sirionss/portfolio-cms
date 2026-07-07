package com.portfolio.portfolio_cms.security;

import com.portfolio.portfolio_cms.model.Role;
import com.portfolio.portfolio_cms.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.*;
public class JwtServiceTest {
    private  static final String TEST_SECRET =
            "dGVzdFNlY3JldEtleUZvckp3dFRlc3RpbmdVc2VkT25seUluVGVzdHM=";
    private static final long ONE_HOUR_MS = 3_600_000L;

    private JwtService jwtService;
    private UserDetails testUser;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "secret", TEST_SECRET);
        ReflectionTestUtils.setField(jwtService, "expiration", ONE_HOUR_MS);

        testUser = new User("admin", "hashedPassword", Role.ADMIN);
    }

    @Test
    void generateToken_validUserDetails_returnsNonEmptyToken() {
        String token = jwtService.generateToken(testUser);
        assertThat(token)
                .isNotNull()
                .isNotEmpty()
                .contains(".");
    }

    @Test
    void extractUsername_validToken_returnsUsername() {
        String token = jwtService.generateToken(testUser);
        String extracted = jwtService.extractUsername(token);
        assertThat(extracted).isEqualTo("admin");

    }

    @Test
    void isTokenValid_validTokenAndMatchingUser_returnsTrue() {
        String token = jwtService.generateToken(testUser);
        boolean result = jwtService.isTokenValid(token, testUser);
        assertThat(result).isTrue();
    }

    @Test
    void isTokenValid_validTokenButDifferentUser_returnsFalse () {
        String token = jwtService.generateToken(testUser);
        UserDetails otherUser = new User("someone_else", "pw", Role.ADMIN);
        boolean result = jwtService.isTokenValid(token, otherUser);
        assertThat(result).isFalse();
    }


    @Test
    void isTokenValid_expiredToken_returnsFalse () throws InterruptedException {
        ReflectionTestUtils.setField(jwtService, "expiration", 1L);
        String token = jwtService.generateToken(testUser);
        Thread.sleep(50);
        boolean result = jwtService.isTokenValid(token, testUser);
        assertThat(result).isFalse();
    }

    @Test
    void extractUsername_malformedToken_throwsException() {
        assertThatThrownBy(() -> jwtService.extractUsername("not.a.jwt"))
                .isInstanceOf(io.jsonwebtoken.MalformedJwtException.class);
    }
}
