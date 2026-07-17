package com.boutique.repositories;

import com.boutique.config.HibernateUtil;
import com.boutique.models.Descuento;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class DescuentoRepository {

    public List<Descuento> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Descuento", Descuento.class).list();
        }
    }

    public List<Descuento> listarActivos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM Descuento WHERE activo = true AND fechaInicio <= :hoy AND fechaFin >= :hoy",
                Descuento.class)
                .setParameter("hoy", LocalDate.now())
                .list();
        }
    }

    public Optional<Descuento> buscarPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Descuento descuento = session.get(Descuento.class, id);
            return Optional.ofNullable(descuento);
        }
    }

    public Descuento guardar(Descuento descuento) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(descuento);
            tx.commit();
            return descuento;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public Descuento actualizar(Descuento descuento) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Descuento merged = session.merge(descuento);
            tx.commit();
            return merged;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}
