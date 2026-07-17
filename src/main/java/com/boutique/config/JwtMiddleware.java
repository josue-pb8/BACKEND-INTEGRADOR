package com.boutique.config;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;
import io.javalin.http.ForbiddenResponse;
import org.jetbrains.annotations.NotNull;

public class JwtMiddleware implements Handler {

    private final String rolRequerido;

    public JwtMiddleware(String rolRequerido) {
        this.rolRequerido = rolRequerido;
    }

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String authHeader = ctx.header("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedResponse("Token no proporcionado");
        }

        String token = authHeader.substring(7);
        try {
            String rol = JwtConfig.extraerRol(token);
            int usuarioId = JwtConfig.extraerUsuarioId(token);

            ctx.attribute("usuarioId", usuarioId);
            ctx.attribute("rol", rol);

            if (rolRequerido != null && !rol.equals(rolRequerido) && !rol.equals("ADMIN")) {
                throw new ForbiddenResponse("No tiene permisos para esta acción");
            }
        } catch (UnauthorizedResponse | ForbiddenResponse e) {
            throw e;
        } catch (Exception e) {
            throw new UnauthorizedResponse("Token inválido o expirado");
        }
    }
}
