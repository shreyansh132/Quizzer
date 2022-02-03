package com.shreyansh.quizzer.auth.util;

import com.shreyansh.quizzer.auth.entity.User;
import com.shreyansh.quizzer.auth.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    @Autowired
    private UserRepository userRepository;

    private String secret;
    private int tokenExpirationTimeInMinutes;
    // private int tokenRefreshExpirationTimeInMinutes;

    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Value("${jwt.tokenExpirationTimeInMinutes}")
    public void setTokenExpirationTimeInMinutes(int tokenExpirationTimeInMinutes) {
        this.tokenExpirationTimeInMinutes = tokenExpirationTimeInMinutes;
    }

//    @Value("${jwt.tokenRefreshExpirationTimeInMinutes}")
//    public void setTokenRefreshExpirationTimeInMinutes(int tokenRefreshExpirationTimeInMinutes) {
//        this.tokenRefreshExpirationTimeInMinutes = tokenRefreshExpirationTimeInMinutes;
//    }

    public String extractUsername(String token) { return extractClaim(token, Claims::getSubject); }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Date extractIssuedAt(String token) {
        return extractClaim(token, Claims::getIssuedAt);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) { return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody(); }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

//    public String generateRefreshToken(Map<String, Object> claims, String subject) {
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(subject)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + tokenRefreshExpirationTimeInMinutes))
//                .signWith(SignatureAlgorithm.HS512, secret).compact();
//    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpirationTimeInMinutes))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public User currentLoggedInUser(String token){
        String arr[] = token.split(" ");
        String userName = this.extractUsername(arr[1]);
        return userRepository.findByEmail(userName);
    }

    //learn code with durgesh used this to get current loggedIn user
//    public User currentUser(Principal principal){
//        principal.getName();
//
//    }
}

