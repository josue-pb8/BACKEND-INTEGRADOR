package com.boutique.repositories;

import com.boutique.config.HibernateUtil;
import com.boutique.models.Carrito;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Optional;

public class CarritoRepository {

    public List<Carrito> listarPorCliente(int clienteId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM Carrito c JOIN FETCH c.producto WHERE c.cliente.id = :clienteId",
                Carrito.class)
                .setParameter("clienteId", clienteId)
                .list();
        }
    }

    public Optional<Carrito> buscarPorClienteYProducto(int clienteId, int productoId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.<Carrito>createQuery(
                "FROM Carrito WHERE cliente.id = :clienteId AND producto.id = :productoId",
                Carrito.class)
                .setParameter("clienteId", clienteId)
                .setParameter("productoId", productoId)
                .uniqueResultOptional();
        }
    }

    public Carrito guardar(Carrito carrito) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(carrito);
            tx.commit();
            return carrito;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public Carrito actualizar(Carrito carrito) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Carrito merged = session.merge(carrito);
            tx.commit();
            return merged;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public void eliminar(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Carrito carrito = session.get(Carrito.class, id);
            if (carrito != null) {
                session.remove(carrito);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public void limpiarCarrito(int clienteId) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.createQuery("DELETE FROM Carrito WHERE cliente.id = :clienteId")
                .setParameter("clienteId", clienteId)
                .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}
