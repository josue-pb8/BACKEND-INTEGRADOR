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
            return session.createQuery("FROM Venta ORDER BY fechaVenta DESC", Venta.class).list();
        }
    }

    public Optional<Venta> buscarPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Venta venta = session.get(Venta.class, id);
            return Optional.ofNullable(venta);
        }
    }

    public List<Venta> ventasPorDia(LocalDateTime inicio, LocalDateTime fin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM Venta WHERE fechaVenta BETWEEN :inicio AND :fin ORDER BY fechaVenta DESC",
                Venta.class)
                .setParameter("inicio", inicio)
                .setParameter("fin", fin)
                .list();
        }
    }

    public List<Venta> ventasPorEmpleado(int empleadoId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM Venta WHERE empleado.id = :empleadoId ORDER BY fechaVenta DESC",
                Venta.class)
                .setParameter("empleadoId", empleadoId)
                .list();
        }
    }

    public List<Venta> ventasPorCliente(int clienteId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM Venta WHERE cliente.id = :clienteId ORDER BY fechaVenta DESC",
                Venta.class)
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
}
