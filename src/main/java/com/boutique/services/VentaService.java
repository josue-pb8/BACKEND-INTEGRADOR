package com.boutique.services;

import com.boutique.models.*;
import com.boutique.repositories.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class VentaService {

    private final VentaRepository ventaRepository = new VentaRepository();
    private final InventarioRepository inventarioRepository = new InventarioRepository();

    public List<Venta> listarTodas() {
        return ventaRepository.listarTodas();
    }

    public Optional<Venta> buscarPorId(int id) {
        return ventaRepository.buscarPorId(id);
    }

    public List<Venta> ventasPorDia(LocalDate fecha) {
        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.atTime(23, 59, 59);
        return ventaRepository.ventasPorDia(inicio, fin);
    }

    public List<Venta> ventasPorEmpleado(int empleadoId) {
        return ventaRepository.ventasPorEmpleado(empleadoId);
    }

    public List<Venta> ventasPorCliente(int clienteId) {
        return ventaRepository.ventasPorCliente(clienteId);
    }

    public Venta registrarVenta(Venta venta, List<DetalleVenta> detalles) {
        for (DetalleVenta detalle : detalles) {
            List<Inventario> variantes = inventarioRepository.buscarPorProducto(detalle.getProducto().getId());
            if (!variantes.isEmpty()) {
                boolean stockDescontado = false;
                for (Inventario inv : variantes) {
                    if (inv.getStock() >= detalle.getCantidad()) {
                        inv.setStock(inv.getStock() - detalle.getCantidad());
                        inventarioRepository.actualizar(inv);
                        stockDescontado = true;
                        break;
                    }
                }
                if (!stockDescontado) {
                    throw new RuntimeException("Stock insuficiente para el producto: " + detalle.getProducto().getNombre());
                }
            }
            detalle.setVenta(venta);
        }
        venta.setDetalles(detalles);
        return ventaRepository.guardar(venta);
    }

    public boolean eliminar(int id) {
        return ventaRepository.eliminar(id);
    }
}
