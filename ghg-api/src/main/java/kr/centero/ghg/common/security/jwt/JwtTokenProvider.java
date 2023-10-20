package kr.centero.ghg.common.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * JwtTokenProvider:
 * This class is used to generate the jwt token and validate the token.
 * Secret key is stored in the application.yml file.
 * Secret key is generated from the url: <a href="https://asecuritysite.com/encryption/plain">...</a>
 */
@Component
public class JwtTokenProvider {

    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    private final JwtExpirationManager jwtExpirationManager;

    @Value("${token.signing.key}")
    private String jwtSecretKey;

    public JwtTokenProvider(JwtExpirationManager jwtExpirationManager) {
        this.jwtExpirationManager = jwtExpirationManager;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(String username, List<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        return generateToken(claims, username);
    }

    public String generateToken(Map<String, Object> claims, String username) {
        long expirationMillis = jwtExpirationManager.getAccessTokenExpirationMillis();
        return buildToken(claims, username, expirationMillis);
    }

    public String generateRefreshToken(String username) {
        long refreshTokenExpirationMillis = jwtExpirationManager.getRefreshTokenExpirationMillis();
        return buildToken(new HashMap<>(), username, refreshTokenExpirationMillis);
    }

    private String buildToken(Map<String, Object> claims, String username, long expirationMillis) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(expirationMillis))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
