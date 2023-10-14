package com.example.jsh_project.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GetClaim {
    @Value("${jwt.token.secret}")
    private String secretKey;
    public Claims decodeJwtToken(String jwtToken, String secretKey) {
        try {
            // JWT 토큰을 복호화하고 클레임(claim)을 얻습니다.
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();

            return claims;
        } catch (Exception e) {
            // 토큰이 유효하지 않은 경우 예외 처리
            e.printStackTrace();
            return null;
        }
    }
}
