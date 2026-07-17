package com.boutique.repositories;

import com.boutique.config.HibernateUtil;
import com.boutique.models.Producto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Optional;

public class ProductoRepository {

    public List<Producto> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Producto WHERE activo = true", Producto.class).list();
        }
    }

    public Optional<Producto> buscarPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Producto producto = session.get(Producto.class, id);
            return Optional.ofNullable(producto);
        }
    }

    public List<Producto> buscarPorNombre(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM Producto WHERE nombre LIKE :nombre AND activo = true", Producto.class)
                .setParameter("nombre", "%" + nombre + "%")
                .list();
        }
    }

    public List<Producto> buscarPorCategoria(int categoriaId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM Producto WHERE categoria.id = :catId AND activo = true", Producto.class)
                .setParameter("catId", categoriaId)
                .list();
        }
    }

    public List<Producto> buscarPorMarca(String marca) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM Producto WHERE marca LIKE :marca AND activo = true", Producto.class)
                .setParameter("marca", "%" + marca + "%")
                .list();
        }
    }

    public List<Producto> filtrar(String talla, String color, Double precioMin, Double precioMax) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            StringBuilder hql = new StringBuilder(
                "SELECT DISTINCT p FROM Inventario i JOIN i.producto p WHERE p.activo = true");
            if (talla != null) hql.append(" AND i.talla = :talla");
            if (color != null) hql.append(" AND i.color = :color");
            if (precioMin != null) hql.append(" AND p.precio >= :precioMin");
            if (precioMax != null) hql.append(" AND p.precio <= :precioMax");

            var query = session.createQuery(hql.toString(), Producto.class);
            if (talla != null) query.setParameter("talla", talla);
            if (color != null) query.setParameter("color", color);
            if (precioMin != null) query.setParameter("precioMin", precioMin);
            if (precioMax != null) query.setParameter("precioMax", precioMax);
            return query.list();
        }
    }

    public Producto guardar(Producto producto) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(producto);
            tx.commit();
            return producto;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public Producto actualizar(Producto producto) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Producto merged = session.merge(producto);
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
            Producto producto = session.get(Producto.class, id);
            if (producto != null) {
                producto.setActivo(false);
                session.merge(producto);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}
