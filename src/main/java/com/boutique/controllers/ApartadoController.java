package com.boutique.controllers;

import com.boutique.models.*;
import com.boutique.repositories.ClienteRepository;
import com.boutique.repositories.ProductoRepository;
import com.boutique.repositories.UsuarioRepository;
import com.boutique.services.ApartadoService;
import io.javalin.http.Context;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApartadoController {

    private final ApartadoService apartadoService = new ApartadoService();
    private final ClienteRepository clienteRepository = new ClienteRepository();
    private final UsuarioRepository usuarioRepository = new UsuarioRepository();
    private final ProductoRepository productoRepository = new ProductoRepository();

    public void listar(Context ctx) {
        ctx.json(apartadoService.listarTodos());
    }

    public void buscarPorId(Context ctx) {
        int id = ctx.pathParamAsClass("id", int.class).get();
        var apartado = apartadoService.buscarPorId(id);
        if (apartado.isEmpty()) {
            ctx.status(404).json(Map.of("error", "Apartado no encontrado"));
            return;
        }
        ctx.json(apartado.get());
    }

    public void buscarPorCliente(Context ctx) {
        int clienteId = ctx.pathParamAsClass("clienteId", int.class).get();
        ctx.json(apartadoService.buscarPorCliente(clienteId));
    }

    public void registrar(Context ctx) {
        var body = ctx.bodyAsClass(Map.class);

        if (body.get("clienteId") == null || body.get("empleadoId") == null || body.get("detalles") == null) {
            ctx.status(400).json(Map.of("error", "Faltan campos requeridos: clienteId, empleadoId, detalles"));
            return;
        }

        Apartado apartado = new Apartado();
        clienteRepository.buscarPorId(((Number) body.get("clienteId")).intValue())
            .ifPresent(apartado::setCliente);
        usuarioRepository.buscarPorId(((Number) body.get("empleadoId")).intValue())
            .ifPresent(apartado::setEmpleado);

        List<Map<String, Object>> items = (List<Map<String, Object>>) body.get("detalles");
        List<DetalleApartado> detalles = new ArrayList<>();
        if (items != null) {
            for (Map<String, Object> item : items) {
                if (item.get("productoId") == null || item.get("cantidad") == null || item.get("precioUnitario") == null) {
                    continue;
                }
                var producto = productoRepository.buscarPorId(((Number) item.get("productoId")).intValue());
                if (producto.isEmpty()) continue;

                DetalleApartado da = new DetalleApartado();
                da.setProducto(producto.get());
                da.setCantidad(((Number) item.get("cantidad")).intValue());
                da.setPrecioUnitario(new BigDecimal(item.get("precioUnitario").toString()));
                detalles.add(da);
            }
        }

        if (detalles.isEmpty()) {
            ctx.status(400).json(Map.of("error", "No se encontraron productos válidos en los detalles"));
            return;
        }

        Apartado registrado = apartadoService.registrarApartado(apartado, detalles);
        ctx.status(201).json(registrado);
    }

    public void registrarAbono(Context ctx) {
        int id = ctx.pathParamAsClass("id", int.class).get();
        var body = ctx.bodyAsClass(Map.class);

        if (body.get("monto") == null) {
            ctx.status(400).json(Map.of("error", "Falta el campo monto"));
            return;
        }

        BigDecimal monto = new BigDecimal(body.get("monto").toString());
        var resultado = apartadoService.registrarAbono(id, monto);
        if (resultado.isEmpty()) {
            ctx.status(404).json(Map.of("error", "Apartado no encontrado"));
            return;
        }
        ctx.json(resultado.get());
    }
}
