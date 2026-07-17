package com.boutique.controllers;

import com.boutique.services.CarritoService;
import io.javalin.http.Context;
import java.util.Map;

public class CarritoController {

    private final CarritoService carritoService = new CarritoService();

    public void listar(Context ctx) {
        int clienteId = ctx.pathParamAsClass("clienteId", int.class).get();
        ctx.json(carritoService.listarPorCliente(clienteId));
    }

    public void agregar(Context ctx) {
        int clienteId = ctx.pathParamAsClass("clienteId", int.class).get();
        var body = ctx.bodyAsClass(Map.class);

        if (body.get("productoId") == null) {
            ctx.status(400).json(Map.of("error", "Falta el campo productoId"));
            return;
        }

        int productoId = ((Number) body.get("productoId")).intValue();
        int cantidad = body.get("cantidad") != null ? ((Number) body.get("cantidad")).intValue() : 1;

        var carrito = carritoService.agregarProducto(clienteId, productoId, cantidad);
        if (carrito == null) {
            ctx.status(404).json(Map.of("error", "Cliente o producto no encontrado"));
            return;
        }
        ctx.status(201).json(carrito);
    }

    public void actualizarCantidad(Context ctx) {
        int clienteId = ctx.pathParamAsClass("clienteId", int.class).get();
        var body = ctx.bodyAsClass(Map.class);

        if (body.get("productoId") == null || body.get("cantidad") == null) {
            ctx.status(400).json(Map.of("error", "Faltan campos: productoId, cantidad"));
            return;
        }

        int productoId = ((Number) body.get("productoId")).intValue();
        int cantidad = ((Number) body.get("cantidad")).intValue();

        var resultado = carritoService.actualizarCantidad(clienteId, productoId, cantidad);
        if (resultado.isEmpty()) {
            ctx.json(Map.of("mensaje", "Producto eliminado del carrito"));
            return;
        }
        ctx.json(resultado.get());
    }

    public void eliminar(Context ctx) {
        int clienteId = ctx.pathParamAsClass("clienteId", int.class).get();
        int productoId = ctx.pathParamAsClass("productoId", int.class).get();
        boolean eliminado = carritoService.eliminarProducto(clienteId, productoId);
        if (!eliminado) {
            ctx.status(404).json(Map.of("error", "Producto no encontrado en el carrito"));
            return;
        }
        ctx.json(Map.of("mensaje", "Producto eliminado del carrito"));
    }
}
