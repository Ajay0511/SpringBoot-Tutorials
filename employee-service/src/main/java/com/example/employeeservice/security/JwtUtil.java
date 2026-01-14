package com.example.employeeservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Secret key for signing JWT (keep it safe in production)
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Token validity (1 hour)
    private final long EXPIRATION_TIME = 1000 * 60 * 60;

    /**
     * Generate JWT token for a username
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)           // set username as subject
                .setIssuedAt(new Date())        // token creation time
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // expiry
                .signWith(key)                  // sign with secret key
                .compact();
    }

    /**
     * Validate token by checking expiration
     */
    public boolean isTokenValid(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Extract username from JWT
     */
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    /**
     * Extract all claims (payload) from JWT
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)   // use same key for parsing
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
