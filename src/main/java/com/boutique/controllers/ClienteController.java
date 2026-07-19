package com.boutique.controllers;

import com.boutique.models.Cliente;
import com.boutique.services.ClienteService;
import io.javalin.http.Context;
import java.util.Map;

public class ClienteController {

    private final ClienteService clienteService = new ClienteService();

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
            (String) body.get("perfil")
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

    public void perfiles(Context ctx) {
        ctx.json(clienteService.listarPerfiles());
    }
}
