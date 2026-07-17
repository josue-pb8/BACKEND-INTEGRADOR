package com.boutique.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "empleado_id", nullable = false)
    private Usuario empleado;

    @ManyToOne
    @JoinColumn(name = "metodo_pago_id", nullable = false)
    private MetodoPago metodoPago;

    @ManyToOne
    @JoinColumn(name = "descuento_id")
    private Descuento descuento;

    @Column(name = "fecha_venta")
    private LocalDateTime fechaVenta = LocalDateTime.now();

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetalleVenta> detalles = new ArrayList<>();

    public Venta() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Usuario getEmpleado() { return empleado; }
    public void setEmpleado(Usuario empleado) { this.empleado = empleado; }

    public MetodoPago getMetodoPago() { return metodoPago; }
    public void setMetodoPago(MetodoPago metodoPago) { this.metodoPago = metodoPago; }

    public Descuento getDescuento() { return descuento; }
    public void setDescuento(Descuento descuento) { this.descuento = descuento; }

    public LocalDateTime getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(LocalDateTime fechaVenta) { this.fechaVenta = fechaVenta; }

    public List<DetalleVenta> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleVenta> detalles) { this.detalles = detalles; }

    public java.math.BigDecimal getTotal() {
        return detalles.stream()
            .map(d -> d.getPrecioUnitario().multiply(java.math.BigDecimal.valueOf(d.getCantidad())))
            .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);
    }
}
