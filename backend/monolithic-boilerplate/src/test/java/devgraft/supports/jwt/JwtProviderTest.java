package devgraft.supports.jwt;

import devgraft.supports.provider.DateProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class JwtProviderTest {
    private JwtProvider jwtProvider;
    private DateProvider dateProvider;
    private JwtProperties jwtProperties;
    @BeforeEach
    void setUp() {
        jwtProperties = new JwtProperties();
        jwtProperties.setSecretKey("TEST-KEY/AWFCAV!@ERFESFDVSDF");
        dateProvider = Mockito.mock(DateProvider.class);
        BDDMockito.given(dateProvider.now()).willReturn(new Date());

        jwtProvider = new JwtProvider(jwtProperties, dateProvider);
    }

    // 생성 결과 확인

    @DisplayName("주어진 정보로 생성된 결과는 항상 일치한다.")
    @Test
    void issue_returnValue() {
        final Map<String, Object> givenData = new HashMap<>();
        final long givenPeriodSecond = 1000L;
        givenData.put("grant", "ADMIN");
        // when
        final String issueResult = jwtProvider.issue(givenData, givenPeriodSecond);
        // then
        final String secret = Base64.getEncoder().encodeToString(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));
        final Key signKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        final Claims claims = Jwts.claims();
        claims.putAll(givenData);
        final Date nowDate = dateProvider.now();
        final String compact = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(nowDate)
                .setExpiration(new Date(nowDate.getTime() + givenPeriodSecond * 1000))
                .signWith(signKey)
                .compact();

        Assertions.assertThat(issueResult).isEqualTo(compact);
    }

    // 검증 - 틀린 토큰 예외처리
    // 검증 성공 시 정보 반환
}