package com.boutique.repositories;

import com.boutique.config.HibernateUtil;
import com.boutique.models.Venta;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class VentaRepository {

    public List<Venta> listarTodas() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "SELECT v FROM Venta v LEFT JOIN FETCH v.detalles dv LEFT JOIN FETCH dv.producto " +
                "LEFT JOIN FETCH v.cliente LEFT JOIN FETCH v.empleado LEFT JOIN FETCH v.metodoPago " +
                "LEFT JOIN FETCH v.descuento ORDER BY v.fechaVenta DESC", Venta.class).list();
        }
    }

    public Optional<Venta> buscarPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "SELECT v FROM Venta v LEFT JOIN FETCH v.detalles dv LEFT JOIN FETCH dv.producto " +
                "LEFT JOIN FETCH v.cliente LEFT JOIN FETCH v.empleado LEFT JOIN FETCH v.metodoPago " +
                "LEFT JOIN FETCH v.descuento WHERE v.id = :id", Venta.class)
                .setParameter("id", id)
                .uniqueResultOptional();
        }
    }

    public List<Venta> ventasPorDia(LocalDateTime inicio, LocalDateTime fin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "SELECT v FROM Venta v LEFT JOIN FETCH v.detalles dv LEFT JOIN FETCH dv.producto " +
                "LEFT JOIN FETCH v.cliente LEFT JOIN FETCH v.empleado LEFT JOIN FETCH v.metodoPago " +
                "WHERE v.fechaVenta BETWEEN :inicio AND :fin ORDER BY v.fechaVenta DESC", Venta.class)
                .setParameter("inicio", inicio)
                .setParameter("fin", fin)
                .list();
        }
    }

    public List<Venta> ventasPorEmpleado(int empleadoId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "SELECT v FROM Venta v LEFT JOIN FETCH v.detalles dv LEFT JOIN FETCH dv.producto " +
                "LEFT JOIN FETCH v.cliente LEFT JOIN FETCH v.empleado LEFT JOIN FETCH v.metodoPago " +
                "WHERE v.empleado.id = :empleadoId ORDER BY v.fechaVenta DESC", Venta.class)
                .setParameter("empleadoId", empleadoId)
                .list();
        }
    }

    public List<Venta> ventasPorCliente(int clienteId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "SELECT v FROM Venta v LEFT JOIN FETCH v.detalles dv LEFT JOIN FETCH dv.producto " +
                "LEFT JOIN FETCH v.cliente LEFT JOIN FETCH v.empleado LEFT JOIN FETCH v.metodoPago " +
                "WHERE v.cliente.id = :clienteId ORDER BY v.fechaVenta DESC", Venta.class)
                .setParameter("clienteId", clienteId)
                .list();
        }
    }

    public Venta guardar(Venta venta) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(venta);
            tx.commit();
            return venta;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public boolean eliminar(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var transaction = session.beginTransaction();
            try {
                Venta venta = session.get(Venta.class, id);
                if (venta == null) return false;
                session.remove(venta);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }
}
