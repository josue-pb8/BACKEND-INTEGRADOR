package com.boutique.controllers;

import com.boutique.models.Producto;
import com.boutique.services.ProductoService;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;

import java.io.IOException;
import java.util.List;

public class ProductoController {

    private final ProductoService productoService = new ProductoService();

    public void listar(Context ctx) {
        ctx.json(productoService.listarTodos());
    }

    public void buscarPorId(Context ctx) {
        int id = ctx.pathParamAsClass("id", int.class).get();
        var producto = productoService.buscarPorId(id);
        if (producto.isEmpty()) {
            ctx.status(404).json(java.util.Map.of("error", "Producto no encontrado"));
            return;
        }
        ctx.json(producto.get());
    }

    public void buscar(Context ctx) {
        String nombre = ctx.queryParam("nombre");
        Integer categoriaId = ctx.queryParamAsClass("categoriaId", Integer.class).get();
        String marca = ctx.queryParam("marca");
        List<Producto> productos = productoService.buscar(nombre, categoriaId, marca);
        ctx.json(productos);
    }

    public void filtrar(Context ctx) {
        String talla = ctx.queryParam("talla");
        String color = ctx.queryParam("color");
        Double precioMin = ctx.queryParamAsClass("precioMin", Double.class).get();
        Double precioMax = ctx.queryParamAsClass("precioMax", Double.class).get();
        ctx.json(productoService.filtrar(talla, color, precioMin, precioMax));
    }

    public void crear(Context ctx) {
        Producto producto = ctx.bodyAsClass(Producto.class);
        try {
            UploadedFile archivo = ctx.uploadedFile("imagen");


            if (archivo != null) {
                producto.setImagen(archivo.content().readAllBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        Producto creado = productoService.guardar(producto);
        ctx.status(201).json(creado);
    }

    public void actualizar(Context ctx) {
        int id = ctx.pathParamAsClass("id", int.class).get();
        Producto datos = ctx.bodyAsClass(Producto.class);
        var resultado = productoService.actualizar(id, datos);
        if (resultado.isEmpty()) {
            ctx.status(404).json(java.util.Map.of("error", "Producto no encontrado"));
            return;
        }
        ctx.json(resultado.get());
    }

    public void buscarImagen(Context ctx) {
        int id = ctx.pathParamAsClass("id", int.class).get();
        var producto = productoService.buscarPorId(id);
        if (producto.isEmpty() || producto.get().getImagen() == null) {
            ctx.status(404);
            return;
        }
        ctx.contentType("image/jpeg").result(producto.get().getImagen());
    }

    public void eliminar(Context ctx) {
        int id = ctx.pathParamAsClass("id", int.class).get();
        boolean eliminado = productoService.eliminar(id);
        if (!eliminado) {
            ctx.status(404).json(java.util.Map.of("error", "Producto no encontrado"));
            return;
        }
        ctx.json(java.util.Map.of("mensaje", "Producto eliminado correctamente"));
    }
}
