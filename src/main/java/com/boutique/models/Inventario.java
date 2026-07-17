package com.boutique.models;

import jakarta.persistence.*;

@Entity
@Table(name = "inventario", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"producto_id", "talla", "color"})
})
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    private String talla;

    private String color;

    @Column(nullable = false)
    private int stock = 0;

    @Column(name = "stock_minimo")
    private int stockMinimo = 5;

    public Inventario() {}

    public Inventario(Producto producto, String talla, String color, int stock) {
        this.producto = producto;
        this.talla = talla;
        this.color = color;
        this.stock = stock;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public String getTalla() { return talla; }
    public void setTalla(String talla) { this.talla = talla; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public int getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(int stockMinimo) { this.stockMinimo = stockMinimo; }
}
