package com.boutique.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "abonos")
public class Abono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "apartado_id", nullable = false)
    private Apartado apartado;

    @Column(nullable = false)
    private BigDecimal monto;

    @Column(name = "fecha_abono")
    private LocalDateTime fechaAbono = LocalDateTime.now();

    public Abono() {}

    public Abono(Apartado apartado, BigDecimal monto) {
        this.apartado = apartado;
        this.monto = monto;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Apartado getApartado() { return apartado; }
    public void setApartado(Apartado apartado) { this.apartado = apartado; }

    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }

    public LocalDateTime getFechaAbono() { return fechaAbono; }
    public void setFechaAbono(LocalDateTime fechaAbono) { this.fechaAbono = fechaAbono; }
}
