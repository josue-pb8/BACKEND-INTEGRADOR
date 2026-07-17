package com.boutique.controllers;

import com.boutique.models.Inventario;
import com.boutique.services.InventarioService;
import io.javalin.http.Context;

public class InventarioController {

    private final InventarioService inventarioService = new InventarioService();

    public void listar(Context ctx) {
        ctx.json(inventarioService.listarTodo());
    }

    public void alertasStockBajo(Context ctx) {
        ctx.json(inventarioService.alertasStockBajo());
    }

    public void buscarPorProducto(Context ctx) {
        int productoId = ctx.pathParamAsClass("productoId", int.class).get();
        ctx.json(inventarioService.buscarPorProducto(productoId));
    }

    public void crear(Context ctx) {
        Inventario inventario = ctx.bodyAsClass(Inventario.class);
        Inventario creado = inventarioService.guardar(inventario);
        ctx.status(201).json(creado);
    }

    public void actualizarStock(Context ctx) {
        int id = ctx.pathParamAsClass("id", int.class).get();
        var body = ctx.bodyAsClass(java.util.Map.class);
        int nuevoStock = ((Number) body.get("stock")).intValue();
        var resultado = inventarioService.actualizarStock(id, nuevoStock);
        if (resultado.isEmpty()) {
            ctx.status(404).json(java.util.Map.of("error", "Registro de inventario no encontrado"));
            return;
        }
        ctx.json(resultado.get());
    }
}
