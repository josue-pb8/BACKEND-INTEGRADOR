package com.boutique.services;

import com.boutique.models.Producto;
import com.boutique.repositories.ProductoRepository;
import java.util.List;
import java.util.Optional;

public class ProductoService {

    private final ProductoRepository productoRepository = new ProductoRepository();

    public List<Producto> listarTodos() {
        return productoRepository.listarTodos();
    }

    public Optional<Producto> buscarPorId(int id) {
        return productoRepository.buscarPorId(id);
    }

    public List<Producto> buscar(String nombre, Integer categoriaId, String marca) {
        if (nombre != null && !nombre.isEmpty()) {
            return productoRepository.buscarPorNombre(nombre);
        }
        if (categoriaId != null) {
            return productoRepository.buscarPorCategoria(categoriaId);
        }
        if (marca != null && !marca.isEmpty()) {
            return productoRepository.buscarPorMarca(marca);
        }
        return productoRepository.listarTodos();
    }

    public List<Producto> filtrar(String talla, String color, Double precioMin, Double precioMax) {
        return productoRepository.filtrar(talla, color, precioMin, precioMax);
    }

    public Producto guardar(Producto producto) {
        return productoRepository.guardar(producto);
    }

    public Optional<Producto> actualizar(int id, Producto datos) {
        Optional<Producto> existente = productoRepository.buscarPorId(id);
        if (existente.isEmpty()) return Optional.empty();

        Producto producto = existente.get();
        if (datos.getNombre() != null) producto.setNombre(datos.getNombre());
        if (datos.getDescripcion() != null) producto.setDescripcion(datos.getDescripcion());
        if (datos.getPrecio() != null) producto.setPrecio(datos.getPrecio());
        if (datos.getMarca() != null) producto.setMarca(datos.getMarca());
        //if (datos.getImagenUrl() != null) producto.setImagenUrl(datos.getImagenUrl());
        if (datos.getCategoria() != null) producto.setCategoria(datos.getCategoria());

        return Optional.of(productoRepository.actualizar(producto));
    }

    public boolean eliminar(int id) {
        Optional<Producto> existente = productoRepository.buscarPorId(id);
        if (existente.isEmpty()) return false;
        productoRepository.eliminar(id);
        return true;
    }
}
