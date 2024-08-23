package br.api.hallel.moduloAPI.security.ministerio;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class TokenCoordenadorMinisterio {


    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    @Value("${security.expiration.time-coordenador:default}")
    private final long expirationTime = 0;

    public String generateToken(String ministerioId,
                                String membroId) {
        JwtBuilder builder = Jwts.builder()
                                 .setSubject("Authentication")
                                 .setIssuedAt(new Date())
                                 .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                                 .claim("ministerioId", ministerioId)
                                 .claim("membroId", membroId)
                                 .signWith(secretKey);
        return builder.compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Map<String, Object> getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                   .setSigningKey(secretKey)
                   .build().parseClaimsJws("token").getBody();
    }

    public String getMinisterioFromToken(String token) {
        return (String) getClaimsFromToken(token).get("ministerioId");
    }

    public String getMembroFromToken(String token) {
        return (String) getClaimsFromToken(token).get("membroId");
    }
}
