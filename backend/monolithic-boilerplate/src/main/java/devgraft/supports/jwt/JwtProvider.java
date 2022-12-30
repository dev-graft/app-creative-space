package devgraft.supports.jwt;

import devgraft.supports.provider.DateProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Component
public class JwtProvider {
    private final Key signKey;
    private final DateProvider dateProvider;

    public JwtProvider(final JwtProperties jwtProperties, final DateProvider dateProvider) {
        final String secret = Base64.getEncoder().encodeToString(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));
        signKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.dateProvider = dateProvider;
    }

    public String issue(final Map<String, Object> data, final long periodSecond) {
        final Claims claims = Jwts.claims();
        claims.putAll(data);
        final Date nowDate = dateProvider.now();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(nowDate)
                .setExpiration(new Date(nowDate.getTime() + periodSecond * 1000))
                .signWith(signKey)
                .compact();
    }

    public Map<String, Object> verify(final String jwt) throws JwtException {
        final JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(signKey).build();
        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(jwt);
        return claimsJws.getBody();
    }
}
