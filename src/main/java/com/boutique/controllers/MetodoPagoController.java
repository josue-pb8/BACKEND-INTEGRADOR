package com.boutique.controllers;

import com.boutique.repositories.MetodoPagoRepository;
import io.javalin.http.Context;

public class MetodoPagoController {
    private final MetodoPagoRepository metodoPagoRepository = new MetodoPagoRepository();

    public void listar(Context ctx) {
        ctx.json(metodoPagoRepository.listarTodos());
    }
}
