package com.boutique.controllers;

import com.boutique.services.AuthService;
import io.javalin.http.Context;
import java.util.Map;

public class AuthController {

    private final AuthService authService = new AuthService();

    public void login(Context ctx) {
        var body = ctx.bodyAsClass(Map.class);
        String nombreUsuario = (String) body.get("nombreUsuario");
        String contrasena = (String) body.get("contrasena");

        var token = authService.login(nombreUsuario, contrasena);
        if (token.isEmpty()) {
            ctx.status(401).json(Map.of("error", "Credenciales incorrectas"));
            return;
        }
        ctx.json(Map.of("token", token.get()));
    }

    public void logout(Context ctx) {
        ctx.json(Map.of("mensaje", "Sesión cerrada correctamente"));
    }

    public void recuperarContrasena(Context ctx) {
        var body = ctx.bodyAsClass(Map.class);
        String nombreUsuario = (String) body.get("nombreUsuario");
        String nuevaContrasena = (String) body.get("nuevaContrasena");

        var clienteService = new com.boutique.services.ClienteService();
        boolean exito = clienteService.recuperarContrasena(nombreUsuario, nuevaContrasena);
        if (!exito) {
            ctx.status(404).json(Map.of("error", "Usuario no encontrado"));
            return;
        }
        ctx.json(Map.of("mensaje", "Contraseña actualizada correctamente"));
    }
}
