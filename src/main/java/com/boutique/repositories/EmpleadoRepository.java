package com.boutique.repositories;

import com.boutique.config.HibernateUtil;
import com.boutique.models.Empleado;
import com.boutique.models.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Optional;

public class EmpleadoRepository {

    public List<Empleado> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Empleado", Empleado.class).list();
        }
    }

    public Optional<Empleado> buscarPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Empleado empleado = session.get(Empleado.class, id);
            return Optional.ofNullable(empleado);
        }
    }

    public Optional<Empleado> buscarPorUsuarioId(int usuarioId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.<Empleado>createQuery(
                "FROM Empleado e WHERE e.usuario.id = :usuarioId", Empleado.class)
                .setParameter("usuarioId", usuarioId)
                .uniqueResultOptional();
        }
    }

    public Empleado guardar(Empleado empleado) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(empleado);
            tx.commit();
            return empleado;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public Empleado guardarEmpleadoConUsuario(Usuario usuario, Empleado empleado) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(usuario);
            empleado.setUsuario(usuario);
            session.persist(empleado);
            tx.commit();
            return empleado;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public Empleado actualizar(Empleado empleado) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Empleado merged = session.merge(empleado);
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
            Empleado empleado = session.get(Empleado.class, id);
            if (empleado != null) {
                session.remove(empleado);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}
