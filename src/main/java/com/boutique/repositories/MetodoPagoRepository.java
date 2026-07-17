package com.boutique.repositories;

import com.boutique.config.HibernateUtil;
import com.boutique.models.MetodoPago;
import org.hibernate.Session;
import java.util.List;
import java.util.Optional;

public class MetodoPagoRepository {

    public List<MetodoPago> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM MetodoPago WHERE activo = true", MetodoPago.class).list();
        }
    }

    public Optional<MetodoPago> buscarPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            MetodoPago mp = session.get(MetodoPago.class, id);
            return Optional.ofNullable(mp);
        }
    }
}
