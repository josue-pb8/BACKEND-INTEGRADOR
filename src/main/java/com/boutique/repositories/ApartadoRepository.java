package com.boutique.repositories;

import com.boutique.config.HibernateUtil;
import com.boutique.models.Apartado;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Optional;

public class ApartadoRepository {

    public List<Apartado> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Apartado ORDER BY fechaApartado DESC", Apartado.class).list();
        }
    }

    public Optional<Apartado> buscarPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.<Apartado>createQuery(
                "SELECT a FROM Apartado a LEFT JOIN FETCH a.abonos WHERE a.id = :id",
                Apartado.class)
                .setParameter("id", id)
                .uniqueResultOptional();
        }
    }

    public List<Apartado> buscarPorCliente(int clienteId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM Apartado WHERE cliente.id = :clienteId ORDER BY fechaApartado DESC",
                Apartado.class)
                .setParameter("clienteId", clienteId)
                .list();
        }
    }

    public List<Apartado> buscarPorEstado(Apartado.EstadoApartado estado) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM Apartado WHERE estado = :estado ORDER BY fechaApartado DESC",
                Apartado.class)
                .setParameter("estado", estado)
                .list();
        }
    }

    public Apartado guardar(Apartado apartado) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(apartado);
            tx.commit();
            return apartado;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public Apartado actualizar(Apartado apartado) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Apartado merged = session.merge(apartado);
            tx.commit();
            return merged;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}
