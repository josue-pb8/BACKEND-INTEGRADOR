package com.boutique.repositories;

import com.boutique.config.HibernateUtil;
import com.boutique.models.Apartado;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;
import java.util.Optional;

public class ApartadoRepository {

    private void inicializarColecciones(Apartado a) {
        Hibernate.initialize(a.getAbonos());
    }

    public List<Apartado> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Apartado> lista = session.createQuery(
                "SELECT a FROM Apartado a LEFT JOIN FETCH a.detalles da LEFT JOIN FETCH da.producto " +
                "LEFT JOIN FETCH a.cliente LEFT JOIN FETCH a.empleado " +
                "ORDER BY a.fechaApartado DESC", Apartado.class).list();
            for (Apartado a : lista) {
                inicializarColecciones(a);
            }
            return lista;
        }
    }

    public Optional<Apartado> buscarPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Optional<Apartado> result = session.<Apartado>createQuery(
                "SELECT a FROM Apartado a LEFT JOIN FETCH a.detalles da LEFT JOIN FETCH da.producto " +
                "LEFT JOIN FETCH a.cliente LEFT JOIN FETCH a.empleado WHERE a.id = :id",
                Apartado.class)
                .setParameter("id", id)
                .uniqueResultOptional();
            result.ifPresent(this::inicializarColecciones);
            return result;
        }
    }

    public List<Apartado> buscarPorCliente(int clienteId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Apartado> lista = session.createQuery(
                "SELECT a FROM Apartado a LEFT JOIN FETCH a.detalles da LEFT JOIN FETCH da.producto " +
                "LEFT JOIN FETCH a.cliente LEFT JOIN FETCH a.empleado " +
                "WHERE a.cliente.id = :clienteId ORDER BY a.fechaApartado DESC", Apartado.class)
                .setParameter("clienteId", clienteId)
                .list();
            for (Apartado a : lista) {
                inicializarColecciones(a);
            }
            return lista;
        }
    }

    public List<Apartado> buscarPorEstado(Apartado.EstadoApartado estado) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Apartado> lista = session.createQuery(
                "SELECT a FROM Apartado a LEFT JOIN FETCH a.detalles da LEFT JOIN FETCH da.producto " +
                "LEFT JOIN FETCH a.cliente LEFT JOIN FETCH a.empleado " +
                "WHERE a.estado = :estado ORDER BY a.fechaApartado DESC", Apartado.class)
                .setParameter("estado", estado)
                .list();
            for (Apartado a : lista) {
                inicializarColecciones(a);
            }
            return lista;
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
