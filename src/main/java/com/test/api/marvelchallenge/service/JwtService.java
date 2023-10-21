package com.test.api.marvelchallenge.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * JwtService es un servicio encargado de generar y validar tokens JWT (JSON Web Tokens) utilizados para la autenticación.
 * Permite la generación de tokens con reclamaciones personalizadas y la extracción de información de tokens.
 */
@Service
public class JwtService {

    @Value("${security.jwt.expiration-in-minutes}")
    private Long EXPIRATION_IN_MINUTES;

    @Value("${security.jwt.secret-key}")
    String SECRET_KEY;

    /**
     * Genera un token JWT con las reclamaciones proporcionadas y la información del usuario.
     *
     * @param user       Detalles del usuario para incluir en el token.
     * @param extraClaims Reclamaciones adicionales a agregar al token.
     * @return Token JWT generado.
     */
    public String generateToken(UserDetails user, Map<String, Object> extraClaims) {

        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + (EXPIRATION_IN_MINUTES * 60 * 1000));

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extrae el sujeto (subject) de un token JWT.
     *
     * @param jwt Token JWT del cual se extraerá el sujeto.
     * @return El sujeto del token JWT.
     */
    public String extractSubject(String jwt) {
        return extractAllClaims(jwt).getSubject();
    }

    private Key generateKey() {
        byte[] keyAsBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyAsBytes);
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parserBuilder().setSigningKey(generateKey()).build()
                .parseClaimsJws(jwt).getBody();
    }
}

