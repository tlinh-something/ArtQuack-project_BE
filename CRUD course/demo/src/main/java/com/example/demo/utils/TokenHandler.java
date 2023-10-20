package com.example.demo.utils;

import com.example.demo.dto.UserSecurityDTO;
import com.example.demo.model.User;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenHandler {
    private final String SECRET_KEY="ART_QUACK";
    private final long EXPIRATION = 1 * 24 * 60 * 60 * 1000;

    // create token (encode)
    public String generateToken(User user) {
        Date now = new Date(); // get current time
        Date expirationDate = new Date(now.getTime() + EXPIRATION);

        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
        return token;
    }

    public String generateRefreshToken(UserSecurityDTO userSecurity) {
        Date now = new Date(); // get current time
        Date expirationDate = new Date(now.getTime() + EXPIRATION * 24 * 30);


        String token = Jwts.builder()
                .setSubject(userSecurity.getUsername())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
        return token;
    }



    public String getInfoByToken(String token) throws ExpiredJwtException, MalformedJwtException {
        String username;
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        username = claims.getSubject();

        return username;
    }

    public Date getExpiredDate(String token) {
        Date date = null;
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            date = claims.getExpiration();

        } catch (ExpiredJwtException expiredJwtException) {
            System.out.println(expiredJwtException.getMessage());
        }
        return date;
    }
}
