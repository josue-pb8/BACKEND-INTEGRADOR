package com.boutique.repositories;

import com.boutique.config.HibernateUtil;
import com.boutique.models.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Optional;

public class UsuarioRepository {

    public Optional<Usuario> buscarPorNombreUsuario(String nombreUsuario) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM Usuario u WHERE u.nombreUsuario = :nombre AND u.activo = true",
                Usuario.class)
                .setParameter("nombre", nombreUsuario)
                .uniqueResultOptional();
        }
    }

    public Optional<Usuario> buscarPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Usuario usuario = session.get(Usuario.class, id);
            return Optional.ofNullable(usuario);
        }
    }

    public Usuario guardar(Usuario usuario) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(usuario);
            tx.commit();
            return usuario;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public Usuario actualizar(Usuario usuario) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Usuario merged = session.merge(usuario);
            tx.commit();
            return merged;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}
