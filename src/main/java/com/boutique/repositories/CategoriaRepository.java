package com.boutique.repositories;

import com.boutique.config.HibernateUtil;
import com.boutique.models.Categoria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Optional;

public class CategoriaRepository {

    public List<Categoria> listarTodas() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Categoria WHERE activa = true", Categoria.class).list();
        }
    }

    public Optional<Categoria> buscarPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Categoria categoria = session.get(Categoria.class, id);
            return Optional.ofNullable(categoria);
        }
    }

    public Categoria guardar(Categoria categoria) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(categoria);
            tx.commit();
            return categoria;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public Categoria actualizar(Categoria categoria) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Categoria merged = session.merge(categoria);
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
            Categoria categoria = session.get(Categoria.class, id);
            if (categoria != null) {
                categoria.setActiva(false);
                session.merge(categoria);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}
