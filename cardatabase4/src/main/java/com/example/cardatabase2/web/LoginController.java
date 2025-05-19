package com.example.cardatabase2.web;

import com.example.cardatabase2.domain.AccountCredentials;
import com.example.cardatabase2.service.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public LoginController(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> getToken(@RequestBody AccountCredentials credentials) {
        UsernamePasswordAuthenticationToken creds = new UsernamePasswordAuthenticationToken
                (
                        credentials.username(),
                        credentials.password()
                );

        Authentication auth = authenticationManager.authenticate(creds);



        // 토큰 생성
        String jwts = jwtService.getToken(auth.getName());

        // 생성된 토큰 반환
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,
                "Bearer " + jwts).header(
                        HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,
                        "Authorization").build();

    }
}
