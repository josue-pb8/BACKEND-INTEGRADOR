package com.boutique.services;

import com.boutique.config.HibernateUtil;
import com.boutique.models.Empleado;
import com.boutique.models.Rol;
import com.boutique.models.Usuario;
import com.boutique.repositories.EmpleadoRepository;
import org.hibernate.Session;
import java.util.List;
import java.util.Optional;

public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository = new EmpleadoRepository();

    public List<Empleado> listarTodos() {
        return empleadoRepository.listarTodos();
    }

    public Optional<Empleado> buscarPorId(int id) {
        return empleadoRepository.buscarPorId(id);
    }

    public Optional<Empleado> buscarPorUsuarioId(int usuarioId) {
        return empleadoRepository.buscarPorUsuarioId(usuarioId);
    }

    public Empleado registrar(String nombreUsuario, String contrasena, String nombre, String apellido, String email, String telefono, String puesto) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Rol rolEmpleado = session.createQuery("FROM Rol r WHERE r.nombre = 'EMPLEADO'", Rol.class)
                    .uniqueResult();
            if (rolEmpleado == null) {
                throw new RuntimeException("Rol EMPLEADO no encontrado en la base de datos");
            }

            Usuario usuario = new Usuario(nombreUsuario, contrasena, rolEmpleado);

            Empleado empleado = new Empleado(usuario, nombre, apellido);
            empleado.setEmail(email);
            empleado.setTelefono(telefono);
            empleado.setPuesto(puesto);

            return empleadoRepository.guardarEmpleadoConUsuario(usuario, empleado);
        }
    }

    public Optional<Empleado> actualizar(int id, Empleado datos) {
        Optional<Empleado> existente = empleadoRepository.buscarPorId(id);
        if (existente.isEmpty()) return Optional.empty();

        Empleado empleado = existente.get();
        if (datos.getNombre() != null) empleado.setNombre(datos.getNombre());
        if (datos.getApellido() != null) empleado.setApellido(datos.getApellido());
        if (datos.getEmail() != null) empleado.setEmail(datos.getEmail());
        if (datos.getTelefono() != null) empleado.setTelefono(datos.getTelefono());
        if (datos.getDireccion() != null) empleado.setDireccion(datos.getDireccion());
        if (datos.getPuesto() != null) empleado.setPuesto(datos.getPuesto());

        return Optional.of(empleadoRepository.actualizar(empleado));
    }

    public boolean eliminar(int id) {
        Optional<Empleado> existente = empleadoRepository.buscarPorId(id);
        if (existente.isEmpty()) return false;

        empleadoRepository.eliminar(id);
        return true;
    }
}
