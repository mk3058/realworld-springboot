package com.minkyu.realworld.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("jwt")
@Setter
public class JwtUtil {

    private String secret;

    private Long accessTokenValidityInSeconds;

    public String getUsername(String token) {

        return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret))).build()
            .parseClaimsJws(token).getBody()
            .get("username", String.class);
    }

    public String getRole(String token) {

        return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret))).build()
            .parseClaimsJws(token).getBody()
            .get("role", String.class);
    }

    public Boolean isExpired(String token) {

        Date expiration = Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret))).build()
            .parseClaimsJws(token).getBody()
            .getExpiration();

        if (expiration == null) {
            return false;
        }
        return expiration.before(new Date());
    }

    public String createJwt(String username, String role) {

        Claims claims = Jwts.claims();
        claims.put("username", username);
        claims.put("role", role);

        JwtBuilder jwt = Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)), SignatureAlgorithm.HS256);
        if (accessTokenValidityInSeconds > 0) {
            jwt.setExpiration(
                new Date(System.currentTimeMillis() + accessTokenValidityInSeconds * 1000));
        }
        return jwt.compact();
    }
}
