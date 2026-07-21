package com.boutique.controllers;

import com.boutique.models.Empleado;
import com.boutique.services.EmpleadoService;
import io.javalin.http.Context;
import java.util.Map;

public class EmpleadoController {

    private final EmpleadoService empleadoService = new EmpleadoService();

    public void listar(Context ctx) {
        ctx.json(empleadoService.listarTodos());
    }

    public void buscarPorId(Context ctx) {
        int id = ctx.pathParamAsClass("id", int.class).get();
        var empleado = empleadoService.buscarPorId(id);
        if (empleado.isEmpty()) {
            ctx.status(404).json(Map.of("error", "Empleado no encontrado"));
            return;
        }
        ctx.json(empleado.get());
    }

    public void registrar(Context ctx) {
        var body = ctx.bodyAsClass(Map.class);
        Empleado empleado = empleadoService.registrar(
            (String) body.get("nombreUsuario"),
            (String) body.get("contrasena"),
            (String) body.get("nombre"),
            (String) body.get("apellido"),
            (String) body.get("email"),
            (String) body.get("telefono"),
            (String) body.get("puesto")
        );
        ctx.status(201).json(empleado);
    }

    public void actualizar(Context ctx) {
        int id = ctx.pathParamAsClass("id", int.class).get();
        Empleado datos = ctx.bodyAsClass(Empleado.class);
        var resultado = empleadoService.actualizar(id, datos);
        if (resultado.isEmpty()) {
            ctx.status(404).json(Map.of("error", "Empleado no encontrado"));
            return;
        }
        ctx.json(resultado.get());
    }

    public void eliminar(Context ctx) {
        int id = ctx.pathParamAsClass("id", int.class).get();
        boolean eliminado = empleadoService.eliminar(id);
        if (!eliminado) {
            ctx.status(404).json(Map.of("error", "Empleado no encontrado"));
            return;
        }
        ctx.json(Map.of("mensaje", "Empleado eliminado correctamente"));
    }
}
