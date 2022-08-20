package com.singlecas.cas.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.singlecas.cas.core.enums.AuthConstants;
import com.singlecas.cas.models.AuthentificationRequest;
import com.singlecas.cas.models.TokenResponse;
import com.singlecas.cas.models.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;

/**
 * Authservices
 */
@Service
public class Authservices {
    @Autowired
    private Userservice userservice;

    private String SECRET_KEY = "abcd";

    // public String extractUsername(String token) {
    // return extractClaim(token, Claims::getSubject);
    // }

    public Claims extractPassword(String token) {
        Claims claims = new DefaultClaims();
        try {
            claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            System.out.println("errrrrr" + e);
            return null;
        }
    }

    // public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    // final Claims claims = extractAllClaims(token);
    // return claimsResolver.apply(claims);
    // }

    // private Date extractExpiration(String token) {
    // return extractClaim(token, Claims::getExpiration);
    // }

    // private boolean isTokenExpired(String token) {
    // return extractExpiration(token).before(new Date());
    // }

    // private Claims extractAllClaims(String token) {
    // return
    // Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    // }

    public TokenResponse generateToken(AuthentificationRequest authentificationRequest) {
        Map<String, Object> claims = new HashMap<>();
        Users user = userservice.getUser();
        TokenResponse tokenResponse = new TokenResponse();
        if (authentificationRequest.getGrandType().equals(AuthConstants.PASSWORD.name())) {
            tokenResponse.setToken(createToken(claims, authentificationRequest.getGrandType(), user));
            tokenResponse.setRefreshToken(createToken(claims, authentificationRequest.getGrandType(), user.getName().toString()));
            tokenResponse.setName(user.getName());
            tokenResponse.setBrandId(user.getBrandId());
            tokenResponse.setSuccess(true);
            return tokenResponse;
        } else if (authentificationRequest.getGrandType().equals(AuthConstants.REFRESH.name())) {
            tokenResponse.setRefreshToken(createToken(claims, authentificationRequest.getGrandType(), "Anonymous"));
            tokenResponse.setBrandId("1234567890");
            tokenResponse.setSuccess(true);
            return tokenResponse;
        }
        return null;
    }

    private String createToken(Map<String, Object> claims, String subject, String userName) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .claim("brandId", "1234567890")
                .claim("name", userName)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 2))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    private String createToken(Map<String, Object> claims, String subject, Users user) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .claim("name", user.getName())
                .claim("password", user.getPassword())
                .claim("brandId", user.getBrandId())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public boolean validateToken(String token) {
        Claims claimBody = extractPassword(token);
        if (null != claimBody) {
            if (claimBody.get("sub").equals(AuthConstants.REFRESH.name())
                    && claimBody.get("brandId").equals("1234567890")) {
                return true;
            } else if (claimBody.get("sub").equals(AuthConstants.PASSWORD.name())
                    && claimBody.get("brandId").equals("1234567890")) {
                // validate user
                return true;
            }
        }
        return false;
    }
}
