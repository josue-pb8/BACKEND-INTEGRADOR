package com.boutique.controllers;

import com.boutique.models.*;
import com.boutique.repositories.*;
import com.boutique.services.VentaService;
import io.javalin.http.Context;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VentaController {

    private final VentaService ventaService = new VentaService();
    private final ClienteRepository clienteRepository = new ClienteRepository();
    private final UsuarioRepository usuarioRepository = new UsuarioRepository();
    private final MetodoPagoRepository metodoPagoRepository = new MetodoPagoRepository();
    private final DescuentoRepository descuentoRepository = new DescuentoRepository();
    private final ProductoRepository productoRepository = new ProductoRepository();

    public void listar(Context ctx) {
        ctx.json(ventaService.listarTodas());
    }

    public void buscarPorId(Context ctx) {
        int id = ctx.pathParamAsClass("id", int.class).get();
        var venta = ventaService.buscarPorId(id);
        if (venta.isEmpty()) {
            ctx.status(404).json(Map.of("error", "Venta no encontrada"));
            return;
        }
        ctx.json(venta.get());
    }

    public void ventasPorDia(Context ctx) {
        String fechaStr = ctx.queryParam("fecha");
        LocalDate fecha = fechaStr != null ? LocalDate.parse(fechaStr) : LocalDate.now();
        ctx.json(ventaService.ventasPorDia(fecha));
    }

    public void historialEmpleado(Context ctx) {
        int empleadoId = ctx.pathParamAsClass("empleadoId", int.class).get();
        ctx.json(ventaService.ventasPorEmpleado(empleadoId));
    }

    public void historialCliente(Context ctx) {
        int clienteId = ctx.pathParamAsClass("clienteId", int.class).get();
        ctx.json(ventaService.ventasPorCliente(clienteId));
    }

    public void registrar(Context ctx) {
        var body = ctx.bodyAsClass(Map.class);

        if (body.get("empleadoId") == null || body.get("metodoPagoId") == null || body.get("detalles") == null) {
            ctx.status(400).json(Map.of("error", "Faltan campos requeridos: empleadoId, metodoPagoId, detalles"));
            return;
        }

        Venta venta = new Venta();
        if (body.get("clienteId") != null) {
            clienteRepository.buscarPorId(((Number) body.get("clienteId")).intValue())
                .ifPresent(venta::setCliente);
        }

        usuarioRepository.buscarPorId(((Number) body.get("empleadoId")).intValue())
            .ifPresent(venta::setEmpleado);
        metodoPagoRepository.buscarPorId(((Number) body.get("metodoPagoId")).intValue())
            .ifPresent(venta::setMetodoPago);

        if (body.get("descuentoId") != null) {
            descuentoRepository.buscarPorId(((Number) body.get("descuentoId")).intValue())
                .ifPresent(venta::setDescuento);
        }

        List<Map<String, Object>> items = (List<Map<String, Object>>) body.get("detalles");
        List<DetalleVenta> detalles = new ArrayList<>();
        if (items != null) {
            for (Map<String, Object> item : items) {
                if (item.get("productoId") == null || item.get("cantidad") == null || item.get("precioUnitario") == null) {
                    continue;
                }
                var producto = productoRepository.buscarPorId(((Number) item.get("productoId")).intValue());
                if (producto.isEmpty()) continue;

                DetalleVenta dv = new DetalleVenta();
                dv.setProducto(producto.get());
                dv.setCantidad(((Number) item.get("cantidad")).intValue());
                dv.setPrecioUnitario(new BigDecimal(item.get("precioUnitario").toString()));
                detalles.add(dv);
            }
        }

        if (detalles.isEmpty()) {
            ctx.status(400).json(Map.of("error", "No se encontraron productos válidos en los detalles"));
            return;
        }

        Venta registrada;
        try {
            registrada = ventaService.registrarVenta(venta, detalles);
        } catch (RuntimeException e) {
            ctx.status(400).json(Map.of("error", e.getMessage()));
            return;
        }
        ctx.status(201).json(registrada);
    }
}
