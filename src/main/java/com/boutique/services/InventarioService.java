package com.boutique.services;

import com.boutique.models.Inventario;
import com.boutique.repositories.InventarioRepository;
import java.util.List;
import java.util.Optional;

public class InventarioService {

    private final InventarioRepository inventarioRepository = new InventarioRepository();

    public List<Inventario> listarTodo() {
        return inventarioRepository.listarTodo();
    }

    public Optional<Inventario> buscarPorId(int id) {
        return inventarioRepository.buscarPorId(id);
    }

    public List<Inventario> buscarPorProducto(int productoId) {
        return inventarioRepository.buscarPorProducto(productoId);
    }

    public List<Inventario> alertasStockBajo() {
        return inventarioRepository.alertasStockBajo();
    }

    public Optional<Inventario> buscarPorVariante(int productoId, String talla, String color) {
        return inventarioRepository.buscarPorVariante(productoId, talla, color);
    }

    public Inventario guardar(Inventario inventario) {
        return inventarioRepository.guardar(inventario);
    }

    public Optional<Inventario> actualizarStock(int id, int nuevoStock) {
        Optional<Inventario> existente = inventarioRepository.buscarPorId(id);
        if (existente.isEmpty()) return Optional.empty();

        Inventario inv = existente.get();
        inv.setStock(nuevoStock);
        return Optional.of(inventarioRepository.actualizar(inv));
    }

    public boolean reducirStock(int productoId, String talla, String color, int cantidad) {
        Optional<Inventario> invOpt = inventarioRepository.buscarPorVariante(productoId, talla, color);
        if (invOpt.isEmpty()) return false;

        Inventario inv = invOpt.get();
        if (inv.getStock() < cantidad) return false;

        inv.setStock(inv.getStock() - cantidad);
        inventarioRepository.actualizar(inv);
        return true;
    }
}
