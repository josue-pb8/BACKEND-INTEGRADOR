package com.boutique.services;

import com.boutique.config.HibernateUtil;
import com.boutique.models.Cliente;
import com.boutique.models.Rol;
import com.boutique.models.Usuario;
import com.boutique.repositories.ClienteRepository;
import com.boutique.repositories.UsuarioRepository;
import org.hibernate.Session;
import java.util.List;
import java.util.Optional;

public class ClienteService {

    private final ClienteRepository clienteRepository = new ClienteRepository();
    private final UsuarioRepository usuarioRepository = new UsuarioRepository();

    public List<Cliente> listarTodos() {
        return clienteRepository.listarTodos();
    }

    public Optional<Cliente> buscarPorId(int id) {
        return clienteRepository.buscarPorId(id);
    }

    public Optional<Cliente> buscarPorUsuarioId(int usuarioId) {
        return clienteRepository.buscarPorUsuarioId(usuarioId);
    }

    public Cliente registrar(String nombreUsuario, String contrasena, String nombre, String apellido, String email, String telefono) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Rol rolCliente = session.createQuery("FROM Rol r WHERE r.nombre = 'CLIENTE'", Rol.class)
                    .uniqueResult();
            if (rolCliente == null) {
                throw new RuntimeException("Rol CLIENTE no encontrado en la base de datos");
            }

            Usuario usuario = new Usuario(nombreUsuario, contrasena, rolCliente);

            Cliente cliente = new Cliente(usuario, nombre, apellido);
            cliente.setEmail(email);
            cliente.setTelefono(telefono);

            return clienteRepository.guardarClienteConUsuario(usuario, cliente);
        }
    }

    public Optional<Cliente> actualizar(int id, Cliente datos) {
        Optional<Cliente> existente = clienteRepository.buscarPorId(id);
        if (existente.isEmpty()) return Optional.empty();

        Cliente cliente = existente.get();
        if (datos.getNombre() != null) cliente.setNombre(datos.getNombre());
        if (datos.getApellido() != null) cliente.setApellido(datos.getApellido());
        if (datos.getEmail() != null) cliente.setEmail(datos.getEmail());
        if (datos.getTelefono() != null) cliente.setTelefono(datos.getTelefono());
        if (datos.getDireccion() != null) cliente.setDireccion(datos.getDireccion());

        return Optional.of(clienteRepository.actualizar(cliente));
    }

    public boolean recuperarContrasena(String nombreUsuario, String nuevaContrasena) {
        Optional<Usuario> usuarioOpt = usuarioRepository.buscarPorNombreUsuario(nombreUsuario);
        if (usuarioOpt.isEmpty()) return false;

        Usuario usuario = usuarioOpt.get();
        usuario.setContrasena(nuevaContrasena);
        usuarioRepository.actualizar(usuario);
        return true;
    }
}
