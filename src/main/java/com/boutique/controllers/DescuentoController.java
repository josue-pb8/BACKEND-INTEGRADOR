package com.boutique.controllers;

import com.boutique.models.Descuento;
import com.boutique.services.DescuentoService;
import io.javalin.http.Context;

public class DescuentoController {

    private final DescuentoService descuentoService = new DescuentoService();

    public void listar(Context ctx) {
        ctx.json(descuentoService.listarTodos());
    }

    public void listarActivos(Context ctx) {
        ctx.json(descuentoService.listarActivos());
    }

    public void buscarPorId(Context ctx) {
        int id = ctx.pathParamAsClass("id", int.class).get();
        var descuento = descuentoService.buscarPorId(id);
        if (descuento.isEmpty()) {
            ctx.status(404).json(java.util.Map.of("error", "Descuento no encontrado"));
            return;
        }
        ctx.json(descuento.get());
    }

    public void crear(Context ctx) {
        Descuento descuento = ctx.bodyAsClass(Descuento.class);
        Descuento creado = descuentoService.guardar(descuento);
        ctx.status(201).json(creado);
    }

    public void actualizar(Context ctx) {
        int id = ctx.pathParamAsClass("id", int.class).get();
        Descuento datos = ctx.bodyAsClass(Descuento.class);
        var resultado = descuentoService.actualizar(id, datos);
        if (resultado.isEmpty()) {
            ctx.status(404).json(java.util.Map.of("error", "Descuento no encontrado"));
            return;
        }
        ctx.json(resultado.get());
    }

    public void finalizar(Context ctx) {
        int id = ctx.pathParamAsClass("id", int.class).get();
        var resultado = descuentoService.finalizar(id);
        if (resultado.isEmpty()) {
            ctx.status(404).json(java.util.Map.of("error", "Descuento no encontrado"));
            return;
        }
        ctx.json(java.util.Map.of("mensaje", "Descuento finalizado correctamente"));
    }
}
