package com.boutique.repositories;

import com.boutique.config.HibernateUtil;
import com.boutique.models.Inventario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Optional;

public class InventarioRepository {

    public List<Inventario> listarTodo() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Inventario", Inventario.class).list();
        }
    }

    public Optional<Inventario> buscarPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Inventario inv = session.get(Inventario.class, id);
            return Optional.ofNullable(inv);
        }
    }

    public List<Inventario> buscarPorProducto(int productoId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM Inventario WHERE producto.id = :productoId", Inventario.class)
                .setParameter("productoId", productoId)
                .list();
        }
    }

    public List<Inventario> alertasStockBajo() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM Inventario WHERE stock <= stockMinimo", Inventario.class)
                .list();
        }
    }

    public Optional<Inventario> buscarPorVariante(int productoId, String talla, String color) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            StringBuilder hql = new StringBuilder("FROM Inventario WHERE producto.id = :productoId");
            if (talla != null) hql.append(" AND talla = :talla");
            if (color != null) hql.append(" AND color = :color");

            var query = session.createQuery(hql.toString(), Inventario.class)
                .setParameter("productoId", productoId);
            if (talla != null) query.setParameter("talla", talla);
            if (color != null) query.setParameter("color", color);

            return query.uniqueResultOptional();
        }
    }

    public Inventario guardar(Inventario inventario) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(inventario);
            tx.commit();
            return inventario;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public Inventario actualizar(Inventario inventario) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Inventario merged = session.merge(inventario);
            tx.commit();
            return merged;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public boolean eliminar(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Inventario inv = session.get(Inventario.class, id);
            if (inv == null) return false;
            session.remove(inv);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}
