package com.boutique.services;

import com.boutique.config.HibernateUtil;
import com.boutique.models.*;
import org.hibernate.Session;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class EstadisticaService {

    public Map<String, Object> ventasMensuales(int mes, int anio) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            LocalDateTime inicio = LocalDateTime.of(anio, mes, 1, 0, 0);
            LocalDateTime fin = inicio.plusMonths(1).minusSeconds(1);

            List<Object[]> resultado = session.createQuery(
                "SELECT v.id, SUM(dv.cantidad * dv.precioUnitario) " +
                "FROM Venta v JOIN v.detalles dv " +
                "WHERE v.fechaVenta BETWEEN :inicio AND :fin " +
                "GROUP BY v.id", Object[].class)
                .setParameter("inicio", inicio)
                .setParameter("fin", fin)
                .list();

            int totalVentas = resultado.size();
            BigDecimal montoTotal = resultado.stream()
                .map(row -> (BigDecimal) row[1])
                .reduce(BigDecimal.ZERO, BigDecimal::add);

            Map<String, Object> map = new HashMap<>();
            map.put("mes", mes);
            map.put("anio", anio);
            map.put("totalVentas", totalVentas);
            map.put("montoTotal", montoTotal);
            return map;
        }
    }

    public List<Map<String, Object>> productosMasVendidos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "SELECT dv.producto.nombre, SUM(dv.cantidad) " +
                "FROM DetalleVenta dv GROUP BY dv.producto.nombre ORDER BY SUM(dv.cantidad) DESC",
                Object[].class)
                .setMaxResults(10)
                .list()
                .stream()
                .map(row -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("producto", row[0]);
                    map.put("totalVendidos", row[1]);
                    return map;
                })
                .toList();
        }
    }

    public Map<String, Object> ganancias() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Object[] resultado = (Object[]) session.createQuery(
                "SELECT COUNT(DISTINCT v), COALESCE(SUM(dv.cantidad * dv.precioUnitario), 0) " +
                "FROM Venta v JOIN v.detalles dv")
                .uniqueResult();

            Map<String, Object> map = new HashMap<>();
            map.put("totalVentas", ((Number) resultado[0]).intValue());
            map.put("montoTotal", resultado[1]);
            return map;
        }
    }

    public List<Map<String, Object>> metodosPago() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "SELECT v.metodoPago.nombre, COUNT(DISTINCT v), SUM(dv.cantidad * dv.precioUnitario) " +
                "FROM Venta v JOIN v.detalles dv GROUP BY v.metodoPago.nombre",
                Object[].class)
                .list()
                .stream()
                .map(row -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("metodoPago", row[0]);
                    map.put("cantidad", row[1]);
                    map.put("total", row[2]);
                    return map;
                })
                .toList();
        }
    }
}
