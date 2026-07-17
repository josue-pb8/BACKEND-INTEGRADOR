package com.boutique.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;

public class JwtConfig {

    private static final String SECRETO = "boutique_moda_secreto_2024";
    private static final long EXPIRACION = 8 * 60 * 60 * 1000; // 8 horas

    public static String generarToken(int usuarioId, String rol) {
        return JWT.create()
                .withSubject(String.valueOf(usuarioId))
                .withClaim("rol", rol)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRACION))
                .sign(Algorithm.HMAC256(SECRETO));
    }

    public static DecodedJWT validarToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRETO))
                .build()
                .verify(token);
    }

    public static int extraerUsuarioId(String token) {
        DecodedJWT jwt = validarToken(token);
        return Integer.parseInt(jwt.getSubject());
    }

    public static String extraerRol(String token) {
        DecodedJWT jwt = validarToken(token);
        return jwt.getClaim("rol").asString();
    }
}
