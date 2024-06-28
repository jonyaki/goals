package com.jonathan.goals.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.Cookie;

import java.util.Arrays;
import java.util.Date;

@Component
public class JWTUtil {
    private static final String SECRET_KEY = "goalsJony";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);
    private static final Date EXPIRES_AT_ONE_MINUTE =  new Date(System.currentTimeMillis() + 3600000);
    private static final String TOKEN_NAME = "jwtToken";

    public void addCokieWithJWT(String issuerClaim, HttpServletResponse response){
        String token = JWT.create()
                .withIssuer(issuerClaim)
                .withExpiresAt(EXPIRES_AT_ONE_MINUTE)
                .sign(ALGORITHM);

        Cookie cookie = new Cookie(TOKEN_NAME, token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);
    }

    public void validateCokieWithJWT(HttpServletRequest request) throws JWTVerificationException {
        Cookie[] cookies = request.getCookies();
        String token = Arrays.stream(cookies)
                .filter(cookie -> TOKEN_NAME.equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new JWTVerificationException("Token no Encontrado"));
        JWT.require(ALGORITHM).build().verify(token);
    }
}
