package com.boutique.controllers;

import com.boutique.models.Cliente;
import com.boutique.repositories.ClienteRepository;
import com.boutique.repositories.UsuarioRepository;
import com.boutique.services.ClienteService;
import io.javalin.http.Context;
import java.util.Map;

public class ClienteController {

    private final ClienteService clienteService = new ClienteService();
    private final ClienteRepository clienteRepository = new ClienteRepository();
    private final UsuarioRepository usuarioRepository = new UsuarioRepository();

    public void listar(Context ctx) {
        ctx.json(clienteService.listarTodos());
    }

    public void buscarPorId(Context ctx) {
        int id = ctx.pathParamAsClass("id", int.class).get();
        var cliente = clienteService.buscarPorId(id);
        if (cliente.isEmpty()) {
            ctx.status(404).json(Map.of("error", "Cliente no encontrado"));
            return;
        }
        ctx.json(cliente.get());
    }

    public void buscarPorUsuarioId(Context ctx) {
        int usuarioId = ctx.pathParamAsClass("usuarioId", int.class).get();
        var cliente = clienteService.buscarPorUsuarioId(usuarioId);
        if (cliente.isEmpty()) {
            ctx.status(404).json(Map.of("error", "Cliente no encontrado para ese usuario"));
            return;
        }
        ctx.json(cliente.get());
    }

    public void registrar(Context ctx) {
        var body = ctx.bodyAsClass(Map.class);
        Cliente cliente = clienteService.registrar(
            (String) body.get("nombreUsuario"),
            (String) body.get("contrasena"),
            (String) body.get("nombre"),
            (String) body.get("apellido"),
            (String) body.get("email"),
            (String) body.get("telefono"),
            (String) body.get("perfil"),
            body
        );
        ctx.status(201).json(cliente);
    }

    public void actualizar(Context ctx) {
        int id = ctx.pathParamAsClass("id", int.class).get();
        Cliente datos = ctx.bodyAsClass(Cliente.class);
        var resultado = clienteService.actualizar(id, datos);
        if (resultado.isEmpty()) {
            ctx.status(404).json(Map.of("error", "Cliente no encontrado"));
            return;
        }
        ctx.json(resultado.get());
    }

    public void cambiarContrasena(Context ctx) {
        int id = ctx.pathParamAsClass("id", int.class).get();
        var body = ctx.bodyAsClass(Map.class);

        String contrasenaActual = (String) body.get("contrasenaActual");
        String contrasenaNueva = (String) body.get("contrasenaNueva");

        if (contrasenaActual == null || contrasenaActual.isEmpty()) {
            ctx.status(400).json(Map.of("error", "La contraseña actual es requerida"));
            return;
        }
        if (contrasenaNueva == null || contrasenaNueva.isEmpty()) {
            ctx.status(400).json(Map.of("error", "La nueva contraseña es requerida"));
            return;
        }
        if (contrasenaNueva.length() < 6) {
            ctx.status(400).json(Map.of("error", "La nueva contraseña debe tener al menos 6 caracteres"));
            return;
        }

        var cliente = clienteRepository.buscarPorId(id);
        if (cliente.isEmpty()) {
            ctx.status(404).json(Map.of("error", "Cliente no encontrado"));
            return;
        }

        var usuario = usuarioRepository.buscarPorId(cliente.get().getUsuario().getId());
        if (usuario.isEmpty()) {
            ctx.status(404).json(Map.of("error", "Usuario no encontrado"));
            return;
        }

        if (!usuario.get().getContrasena().equals(contrasenaActual)) {
            ctx.status(400).json(Map.of("error", "La contraseña actual es incorrecta"));
            return;
        }

        usuario.get().setContrasena(contrasenaNueva);
        usuarioRepository.actualizar(usuario.get());
        ctx.json(Map.of("mensaje", "Contraseña actualizada correctamente"));
    }

    public void perfiles(Context ctx) {
        ctx.json(clienteService.listarPerfiles());
    }

    public void eliminar(Context ctx) {
        int id = ctx.pathParamAsClass("id", int.class).get();
        boolean eliminado = clienteService.eliminar(id);
        if (!eliminado) {
            ctx.status(404).json(Map.of("error", "Cliente no encontrado"));
            return;
        }
        ctx.json(Map.of("mensaje", "Cliente eliminado correctamente"));
    }
}
