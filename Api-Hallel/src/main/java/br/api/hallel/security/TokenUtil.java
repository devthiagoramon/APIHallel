package br.api.hallel.security;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;

import br.api.hallel.model.Membro;
import br.api.hallel.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

public class TokenUtil {

    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer ";
    private static final long EXPIRATION = 12 * 60 * 60 * 1000;
    private static final String SECRET_KEY = "m(4S%4PmhD%h¨_j!UxbRpBx)w*ofkZhL";
    private static final String EMISSOR = "HallelToken";

    public static String createToken(Membro membro) {
        Key secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        String token = Jwts.builder()
                .setSubject(membro.getEmail())
                .setIssuer(EMISSOR)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        return PREFIX + token;
    }

    private static boolean isExpirationValid(Date expiration) {
        return expiration.after(new Date(System.currentTimeMillis()));
    }

    private static boolean isEmissorValid(String emissor) {
        return emissor.equals(EMISSOR);
    }

    private static boolean isSubjectValid(String email) {
        return email != null && email.length() > 0 && email.contains("@");
    }

    public static Authentication validate(HttpServletRequest request) {

        String token = request.getHeader(HEADER);

        token = token.replace(PREFIX, "");

        Jws<Claims> jwsClaims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token);

        String email = jwsClaims.getBody().getSubject();
        String issuer = jwsClaims.getBody().getIssuer();
        Date expira = jwsClaims.getBody().getExpiration();

        if (isEmissorValid(issuer) && isSubjectValid(email) && isExpirationValid(expira)) {
            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        }

        return null;
    }
}
