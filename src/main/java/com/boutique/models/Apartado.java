package com.boutique.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "apartados")
public class Apartado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "empleado_id", nullable = false)
    private Usuario empleado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoApartado estado = EstadoApartado.ACTIVO;

    @Column(name = "fecha_apartado")
    private LocalDateTime fechaApartado = LocalDateTime.now();

    @OneToMany(mappedBy = "apartado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetalleApartado> detalles = new ArrayList<>();

    @OneToMany(mappedBy = "apartado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Abono> abonos = new LinkedHashSet<>();

    public enum EstadoApartado {
        ACTIVO, PAGADO, CANCELADO
    }

    public Apartado() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Usuario getEmpleado() { return empleado; }
    public void setEmpleado(Usuario empleado) { this.empleado = empleado; }

    public EstadoApartado getEstado() { return estado; }
    public void setEstado(EstadoApartado estado) { this.estado = estado; }

    public LocalDateTime getFechaApartado() { return fechaApartado; }
    public void setFechaApartado(LocalDateTime fechaApartado) { this.fechaApartado = fechaApartado; }

    public List<DetalleApartado> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleApartado> detalles) { this.detalles = detalles; }

    public Set<Abono> getAbonos() { return abonos; }
    public void setAbonos(Set<Abono> abonos) { this.abonos = abonos; }

    public BigDecimal getTotal() {
        return detalles.stream()
            .map(d -> d.getPrecioUnitario().multiply(BigDecimal.valueOf(d.getCantidad())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getAbonado() {
        return abonos.stream()
            .map(Abono::getMonto)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getPendiente() {
        return getTotal().subtract(getAbonado());
    }
}
