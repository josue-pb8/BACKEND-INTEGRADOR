package com.boutique.controllers;

import com.boutique.services.EstadisticaService;
import io.javalin.http.Context;

public class EstadisticaController {

    private final EstadisticaService estadisticaService = new EstadisticaService();

    public void ventasMensuales(Context ctx) {
        String mesStr = ctx.queryParam("mes");
        String anioStr = ctx.queryParam("anio");
        int mes = mesStr != null ? Integer.parseInt(mesStr) : java.time.LocalDate.now().getMonthValue();
        int anio = anioStr != null ? Integer.parseInt(anioStr) : java.time.LocalDate.now().getYear();
        ctx.json(estadisticaService.ventasMensuales(mes, anio));
    }

    public void productosMasVendidos(Context ctx) {
        ctx.json(estadisticaService.productosMasVendidos());
    }

    public void ganancias(Context ctx) {
        ctx.json(estadisticaService.ganancias());
    }

    public void metodosPago(Context ctx) {
        ctx.json(estadisticaService.metodosPago());
    }

    public void clientesNuevos(Context ctx) {
        ctx.json(estadisticaService.clientesNuevos());
    }

    public void apartadosActivos(Context ctx) {
        ctx.json(estadisticaService.apartadosActivos());
    }

    public void ventasRecientes(Context ctx) {
        ctx.json(estadisticaService.ventasRecientes());
    }

    public void ventasSemanales(Context ctx) {
        ctx.json(estadisticaService.ventasSemanales());
    }

    public void gananciasSemanales(Context ctx) {
        ctx.json(estadisticaService.gananciasSemanales());
    }
}
