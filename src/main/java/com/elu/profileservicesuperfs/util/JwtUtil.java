package com.elu.profileservicesuperfs.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "mynameismuhammedelhamthebestpersonintheworldsecretekeyandicametorealisationthiswasnotenoughforjwtwthihopethisworksnow";


    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
