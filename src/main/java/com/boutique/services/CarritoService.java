package com.boutique.services;

import com.boutique.models.Carrito;
import com.boutique.models.Cliente;
import com.boutique.models.Producto;
import com.boutique.repositories.CarritoRepository;
import com.boutique.repositories.ClienteRepository;
import com.boutique.repositories.ProductoRepository;
import java.util.List;
import java.util.Optional;

public class CarritoService {

    private final CarritoRepository carritoRepository = new CarritoRepository();
    private final ClienteRepository clienteRepository = new ClienteRepository();
    private final ProductoRepository productoRepository = new ProductoRepository();

    public List<Carrito> listarPorCliente(int clienteId) {
        return carritoRepository.listarPorCliente(clienteId);
    }

    public Carrito agregarProducto(int clienteId, int productoId, int cantidad) {
        Optional<Carrito> existente = carritoRepository.buscarPorClienteYProducto(clienteId, productoId);
        if (existente.isPresent()) {
            Carrito carrito = existente.get();
            carrito.setCantidad(carrito.getCantidad() + cantidad);
            return carritoRepository.actualizar(carrito);
        }

        Optional<Cliente> cliente = clienteRepository.buscarPorId(clienteId);
        Optional<Producto> producto = productoRepository.buscarPorId(productoId);

        if (cliente.isEmpty() || producto.isEmpty()) {
            return null;
        }

        Carrito carrito = new Carrito();
        carrito.setCliente(cliente.get());
        carrito.setProducto(producto.get());
        carrito.setCantidad(cantidad);
        return carritoRepository.guardar(carrito);
    }

    public Optional<Carrito> actualizarCantidad(int clienteId, int productoId, int nuevaCantidad) {
        Optional<Carrito> existente = carritoRepository.buscarPorClienteYProducto(clienteId, productoId);
        if (existente.isEmpty()) return Optional.empty();

        Carrito carrito = existente.get();
        if (nuevaCantidad <= 0) {
            carritoRepository.eliminar(carrito.getId());
            return Optional.empty();
        }
        carrito.setCantidad(nuevaCantidad);
        return Optional.of(carritoRepository.actualizar(carrito));
    }

    public boolean eliminarProducto(int clienteId, int productoId) {
        Optional<Carrito> existente = carritoRepository.buscarPorClienteYProducto(clienteId, productoId);
        if (existente.isEmpty()) return false;
        carritoRepository.eliminar(existente.get().getId());
        return true;
    }

    public void limpiarCarrito(int clienteId) {
        carritoRepository.limpiarCarrito(clienteId);
    }
}
