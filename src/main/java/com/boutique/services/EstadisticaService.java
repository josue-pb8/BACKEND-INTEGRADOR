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

    public Map<String, Object> clientesNuevos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            LocalDateTime inicioMes = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
            Long total = session.createQuery(
                "SELECT COUNT(c) FROM Cliente c WHERE c.fechaRegistro >= :inicio", Long.class)
                .setParameter("inicio", inicioMes)
                .uniqueResult();
            Map<String, Object> map = new HashMap<>();
            map.put("total", total != null ? total : 0L);
            return map;
        }
    }

    public Map<String, Object> apartadosActivos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Long total = session.createQuery(
                "SELECT COUNT(a) FROM Apartado a WHERE a.estado = :estado", Long.class)
                .setParameter("estado", com.boutique.models.Apartado.EstadoApartado.ACTIVO)
                .uniqueResult();
            Map<String, Object> map = new HashMap<>();
            map.put("total", total != null ? total : 0L);
            return map;
        }
    }

    public List<Map<String, Object>> ventasRecientes() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "SELECT v FROM Venta v LEFT JOIN FETCH v.detalles dv LEFT JOIN FETCH v.cliente LEFT JOIN FETCH dv.producto " +
                "ORDER BY v.fechaVenta DESC", Venta.class)
                .setMaxResults(10)
                .list()
                .stream()
                .map(v -> {
                    Map<String, Object> map = new HashMap<>();
                    java.time.format.DateTimeFormatter fmt = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    map.put("fecha", v.getFechaVenta() != null ? v.getFechaVenta().format(fmt) : "");
                    map.put("cliente", v.getCliente() != null ? v.getCliente().getNombre() + " " + v.getCliente().getApellido() : "Sin cliente");
                    String nombresProductos = v.getDetalles().stream()
                        .map(d -> d.getProducto().getNombre() + (d.getCantidad() > 1 ? " x" + d.getCantidad() : ""))
                        .collect(java.util.stream.Collectors.joining(", "));
                    map.put("productos", nombresProductos);
                    map.put("total", v.getTotal());
                    map.put("estado", "completada");
                    return map;
                })
                .toList();
        }
    }

    public Map<String, Object> ventasSemanales() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            LocalDateTime inicioSemana = LocalDateTime.now().minusDays(7).withHour(0).withMinute(0).withSecond(0);
            List<Object[]> resultado = session.createQuery(
                "SELECT v.id, SUM(dv.cantidad * dv.precioUnitario) " +
                "FROM Venta v JOIN v.detalles dv " +
                "WHERE v.fechaVenta >= :inicio " +
                "GROUP BY v.id", Object[].class)
                .setParameter("inicio", inicioSemana)
                .list();

            int totalVentas = resultado.size();
            BigDecimal montoTotal = resultado.stream()
                .map(row -> (BigDecimal) row[1])
                .reduce(BigDecimal.ZERO, BigDecimal::add);

            Map<String, Object> map = new HashMap<>();
            map.put("total", totalVentas);
            map.put("montoTotal", montoTotal);
            map.put("porcentaje", totalVentas > 0 ? "+" + Math.min(totalVentas, 100) : "0");
            return map;
        }
    }

    public Map<String, Object> gananciasSemanales() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            LocalDateTime inicioSemana = LocalDateTime.now().minusDays(7).withHour(0).withMinute(0).withSecond(0);
            Object[] resultado = (Object[]) session.createQuery(
                "SELECT COUNT(DISTINCT v), COALESCE(SUM(dv.cantidad * dv.precioUnitario), 0) " +
                "FROM Venta v JOIN v.detalles dv " +
                "WHERE v.fechaVenta >= :inicio")
                .setParameter("inicio", inicioSemana)
                .uniqueResult();

            Map<String, Object> map = new HashMap<>();
            map.put("totalVentas", ((Number) resultado[0]).intValue());
            map.put("total", resultado[1]);
            map.put("montoTotal", resultado[1]);
            map.put("porcentaje", ((Number) resultado[0]).intValue() > 0 ? "+5" : "0");
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
