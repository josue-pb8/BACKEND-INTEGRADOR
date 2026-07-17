package com.boutique.services;

import com.boutique.config.HibernateUtil;
import com.boutique.config.JwtConfig;
import com.boutique.models.Rol;
import com.boutique.models.Usuario;
import com.boutique.repositories.UsuarioRepository;
import org.hibernate.Session;
import java.util.Objects;
import java.util.Optional;

public class AuthService {

    private final UsuarioRepository usuarioRepository = new UsuarioRepository();

    public Optional<String> login(String nombreUsuario, String contrasena) {
        Optional<Usuario> usuarioOpt = usuarioRepository.buscarPorNombreUsuario(nombreUsuario);
        if (usuarioOpt.isEmpty()) return Optional.empty();

        Usuario usuario = usuarioOpt.get();
        if (!Objects.equals(usuario.getContrasena(), contrasena)) return Optional.empty();

        String token = JwtConfig.generarToken(usuario.getId(), usuario.getRol().getNombre());
        return Optional.of(token);
    }

    public Optional<Usuario> registrar(String nombreUsuario, String contrasena, String rolNombre) {
        if (usuarioRepository.buscarPorNombreUsuario(nombreUsuario).isPresent()) {
            return Optional.empty();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var tx = session.beginTransaction();
            try {
                Rol rol = session.createQuery("FROM Rol r WHERE r.nombre = :nombre", Rol.class)
                        .setParameter("nombre", rolNombre)
                        .uniqueResult();
                if (rol == null) {
                    tx.rollback();
                    return Optional.empty();
                }

                Usuario usuario = new Usuario(nombreUsuario, contrasena, rol);
                session.persist(usuario);
                tx.commit();
                return Optional.of(usuario);
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }
}
