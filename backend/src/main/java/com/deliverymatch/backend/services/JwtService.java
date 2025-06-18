package com.deliverymatch.backend.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private static final String SECRET_KEY = "F81/lEwXRHJ2Syv2akuZtdsMrLPb1qjVe7Ez4wXWj24Ec5xy/TDuCvNbqGgw3X1o6XR2KOGETED5HG12srAZQS51iSCNlbpM2GYNMGK+izzdmZMzSXKo9GXC7vok6yogymlMoEErCjXX7xQe8YWfQgIqDN2tWbx0nzr5Q4MVq2oMcHZNOG5icIPGQpzuErs3IAshaKQdRQrjOxyCbaCc6DkyohJIzfL3X2GyYNEbOlJMRn7TsjScKOFoRoQoEM97sWKhgiVUn8R1qwANXX1YVRlv78JjQFwqU+j6qxZgvWlU+UR+0yMEhLvwC8529z0zV+QxpQJsTZXHa0qwpsa17C3lpfkFLqheBA8zukKQa1U=\n";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //extract one claims method
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        if (userDetails instanceof com.deliverymatch.backend.security.CustomUserDetails customUser) {
            claims.put("role", customUser.getUser().getRole().name()); // ajoute le rôle
        }

        return generateToken(claims, userDetails);
    }


    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //extract all the claims
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
