package com.example.cardatabase2.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtService {
    // 1일
    static final long EXPIRATION = 84600000;
    static final String PREFIX = "Bearer";

    // 비밀 키 생성
    static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 서명된 JWT 토큰 생성
    public String getToken(String username) {
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key)
                .compact();

        return token;
    }

    // 요청의 Authorization 헤더에서 토큰을 가져온 뒤에 확인하고 사용자 이름을 가져오는 과전
    public String getAuthUser(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null) {
            String user = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token.replace(PREFIX, ""))
                    .getBody()
                    .getSubject();

            return user;
        }

        return null;
    }
}
