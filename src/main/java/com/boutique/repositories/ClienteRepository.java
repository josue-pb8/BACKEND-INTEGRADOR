package com.boutique.repositories;

import com.boutique.config.HibernateUtil;
import com.boutique.models.Cliente;
import com.boutique.models.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Optional;

public class ClienteRepository {

    public List<Cliente> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Cliente", Cliente.class).list();
        }
    }

    public Optional<Cliente> buscarPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Cliente cliente = session.get(Cliente.class, id);
            return Optional.ofNullable(cliente);
        }
    }

    public Optional<Cliente> buscarPorUsuarioId(int usuarioId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.<Cliente>createQuery(
                "FROM Cliente c WHERE c.usuario.id = :usuarioId", Cliente.class)
                .setParameter("usuarioId", usuarioId)
                .uniqueResultOptional();
        }
    }

    public Cliente guardar(Cliente cliente) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(cliente);
            tx.commit();
            return cliente;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public Cliente guardarClienteConUsuario(Usuario usuario, Cliente cliente) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(usuario);
            cliente.setUsuario(usuario);
            session.persist(cliente);
            tx.commit();
            return cliente;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public Cliente actualizar(Cliente cliente) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Cliente merged = session.merge(cliente);
            tx.commit();
            return merged;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}
