package com.boutique.controllers;

import com.boutique.models.Categoria;
import com.boutique.services.CategoriaService;
import io.javalin.http.Context;

public class CategoriaController {

    private final CategoriaService categoriaService = new CategoriaService();

    public void listar(Context ctx) {
        ctx.json(categoriaService.listarTodas());
    }

    public void buscarPorId(Context ctx) {
        int id = ctx.pathParamAsClass("id", int.class).get();
        var categoria = categoriaService.buscarPorId(id);
        if (categoria.isEmpty()) {
            ctx.status(404).json(java.util.Map.of("error", "Categoría no encontrada"));
            return;
        }
        ctx.json(categoria.get());
    }

    public void crear(Context ctx) {
        Categoria categoria = ctx.bodyAsClass(Categoria.class);
        Categoria creada = categoriaService.guardar(categoria);
        ctx.status(201).json(creada);
    }

    public void actualizar(Context ctx) {
        int id = ctx.pathParamAsClass("id", int.class).get();
        Categoria datos = ctx.bodyAsClass(Categoria.class);
        var resultado = categoriaService.actualizar(id, datos);
        if (resultado.isEmpty()) {
            ctx.status(404).json(java.util.Map.of("error", "Categoría no encontrada"));
            return;
        }
        ctx.json(resultado.get());
    }

    public void eliminar(Context ctx) {
        int id = ctx.pathParamAsClass("id", int.class).get();
        boolean eliminada = categoriaService.eliminar(id);
        if (!eliminada) {
            ctx.status(404).json(java.util.Map.of("error", "Categoría no encontrada"));
            return;
        }
        ctx.json(java.util.Map.of("mensaje", "Categoría eliminada correctamente"));
    }
}
