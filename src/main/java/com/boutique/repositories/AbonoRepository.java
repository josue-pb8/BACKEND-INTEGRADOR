package com.boutique.repositories;

import com.boutique.config.HibernateUtil;
import com.boutique.models.Abono;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class AbonoRepository {

    public List<Abono> buscarPorApartado(int apartadoId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM Abono WHERE apartado.id = :apartadoId ORDER BY fechaAbono DESC",
                Abono.class)
                .setParameter("apartadoId", apartadoId)
                .list();
        }
    }

    public Abono guardar(Abono abono) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(abono);
            tx.commit();
            return abono;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}
