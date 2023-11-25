package com.project.config;
import com.project.exceptions.AppException;
import io.jsonwebtoken.*;
import com.project.config.CustomUsersDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${pj.jwt.secret}")
    private String JWT_SECRET;
    @Value("${pj.jwt.expiration}")
    private long JWT_EXPIRATION;

    public String generateToken(CustomUsersDetails customUserDetails){
        Date now = new Date();
        Date expired = new Date(now.getTime() + JWT_EXPIRATION);
        return Jwts.builder()
                .setSubject(customUserDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expired)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
    }

    public String getUsernameFromJwt(String token){
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET)
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException ex){
            log.error("JWT token không hợp lệ","401");
        }catch (ExpiredJwtException ex){
            throw new AppException("JWT token đã hết hạn","401");
        }catch (UnsupportedJwtException ex){
            log.error("JWT token không hỡ trợ");
        }catch (IllegalArgumentException ex){
            log.error("Chuỗi JWT claims trống");
        }
        return false;
    }
}
