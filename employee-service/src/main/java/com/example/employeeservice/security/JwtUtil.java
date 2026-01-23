package com.example.employeeservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Secret key for signing JWT (keep it safe in production)
    private final Key key;

    // Token validity (1 hour)
    private final long EXPIRATION_TIME = 1000 * 60 * 60;

    /**
     * Initialize JWT utility with a fixed secret key from configuration.
     * Using a fixed secret ensures tokens remain valid across application reloads.
     */
    public JwtUtil(@Value("${jwt.secret}") String secret) {
        // Create a fixed key from the secret string (must be at least 256 bits for HS256)
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)           // set username as subject
                .setIssuedAt(new Date())        // token creation time
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // expiry
                .signWith(this.key)                  // sign with secret key
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
                .setSigningKey(this.key)   // use same key for parsing
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
