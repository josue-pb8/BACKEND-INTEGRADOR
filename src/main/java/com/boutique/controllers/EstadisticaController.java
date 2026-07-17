package com.boutique.controllers;

import com.boutique.services.EstadisticaService;
import io.javalin.http.Context;

public class EstadisticaController {

    private final EstadisticaService estadisticaService = new EstadisticaService();

    public void ventasMensuales(Context ctx) {
        Integer mes = ctx.queryParamAsClass("mes", Integer.class).get();
        Integer anio = ctx.queryParamAsClass("anio", Integer.class).get();
        if (mes == null) mes = java.time.LocalDate.now().getMonthValue();
        if (anio == null) anio = java.time.LocalDate.now().getYear();
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
}
