USE boutique_moda;

INSERT INTO usuarios (nombre_usuario, contrasena, rol_id, activo) VALUES
('admin', 'admin123', 1, TRUE),
('carlos_emp', 'empleado123', 2, TRUE),
('maria_cliente', 'cliente123', 3, TRUE),
('juan_cliente', 'cliente123', 3, TRUE);

INSERT INTO clientes (usuario_id, nombre, apellido, email, telefono, direccion, fecha_nacimiento) VALUES
(3, 'Maria', 'Lopez', 'maria@email.com', '555-1234', 'Calle Principal 123', '1990-05-15'),
(4, 'Juan', 'Perez', 'juan@email.com', '555-5678', 'Av. Reforma 456', '1985-08-20');

INSERT INTO categorias (nombre, descripcion, activa) VALUES
('Vestidos', 'Vestidos formales y casuales para mujer', TRUE),
('Blusas', 'Blusas y camisas femeninas', TRUE),
('Pantalones', 'Pantalones y jeans para mujer', TRUE),
('Faldas', 'Faldas de diferentes estilos', TRUE),
('Zapatos', 'Calzado femenino', TRUE),
('Accesorios', 'Bolsas, bisuteria y complementos', TRUE);

INSERT INTO productos (nombre, descripcion, precio, costo, marca, categoria_id, activo) VALUES
('Vestido Floral Largo', 'Vestido largo con estampado floral, ideal para eventos', 1299.00, 650.00, 'Bella Moda', 1, TRUE),
('Vestido Negro Elegante', 'Vestido negro clasico para occasion especial', 1599.00, 800.00, 'Bella Moda', 1, TRUE),
('Blusa Seda Blanca', 'Blusa de seda blanca con cuello alto', 599.00, 280.00, 'Elegance', 2, TRUE),
('Blusa Rayas Azules', 'Blusa a rayas azul y blanco, estilo marino', 449.00, 200.00, 'Elegance', 2, TRUE),
('Jeans Skinny Azul', 'Jeans skinny azul oscuro, tela elastica', 799.00, 400.00, 'Denim Plus', 3, TRUE),
('Pantalon Formal Negro', 'Pantalon formal de vestir negro', 899.00, 450.00, 'Denim Plus', 3, TRUE),
('Falda Midi Plisada', 'Falda midi plisada color rosa', 549.00, 250.00, 'Bella Moda', 4, TRUE),
('Falda Cuero Negra', 'Falda de cuero sintetico negro', 699.00, 320.00, 'Bella Moda', 4, TRUE),
('Zapatos Tacón Rojo', 'Zapatos de tacón alto rojo, piel sintetica', 999.00, 500.00, 'Step Up', 5, TRUE),
('Bolsa Cuero Marron', 'Bolsa de cuero sintetico color marron', 749.00, 350.00, 'Access Style', 6, TRUE),
('Collar Plata Corazon', 'Collar de plata 925 con colgante corazon', 399.00, 180.00, 'Access Style', 6, TRUE),
('Aretes Dorados Circulo', 'Aretes dorados circulares elegantes', 299.00, 120.00, 'Access Style', 6, TRUE);

INSERT INTO inventario (producto_id, talla, color, stock, stock_minimo) VALUES
(1, 'S', 'Floral', 10, 3),
(1, 'M', 'Floral', 15, 3),
(1, 'L', 'Floral', 8, 3),
(2, 'S', 'Negro', 12, 3),
(2, 'M', 'Negro', 18, 3),
(2, 'L', 'Negro', 10, 3),
(3, 'S', 'Blanco', 20, 5),
(3, 'M', 'Blanco', 25, 5),
(3, 'L', 'Blanco', 15, 5),
(4, 'M', 'Azul', 14, 3),
(4, 'L', 'Azul', 10, 3),
(5, '26', 'Azul', 30, 5),
(5, '28', 'Azul', 25, 5),
(5, '30', 'Azul', 20, 5),
(6, '28', 'Negro', 16, 3),
(6, '30', 'Negro', 12, 3),
(7, 'S', 'Rosa', 10, 3),
(7, 'M', 'Rosa', 14, 3),
(8, 'S', 'Negro', 8, 3),
(8, 'M', 'Negro', 12, 3),
(9, '24', 'Rojo', 10, 3),
(9, '25', 'Rojo', 8, 3),
(9, '26', 'Rojo', 6, 3),
(10, 'Unico', 'Marron', 15, 3),
(11, 'Unico', 'Plata', 20, 5),
(12, 'Unico', 'Dorado', 25, 5);

INSERT INTO descuentos (nombre, porcentaje, fecha_inicio, fecha_fin, activo) VALUES
('Rebajas Verano', 15.00, '2026-06-01', '2026-08-31', TRUE),
('Black Friday', 30.00, '2026-11-20', '2026-11-30', FALSE),
('Descuento Empleados', 10.00, '2026-01-01', '2026-12-31', TRUE);

INSERT INTO metodos_pago (nombre, activo) VALUES
('Tarjeta de Credito', TRUE),
('Tarjeta de Debito', TRUE),
('Transferencia', TRUE);

INSERT INTO ventas (cliente_id, empleado_id, metodo_pago_id, descuento_id, fecha_venta) VALUES
(1, 2, 1, NULL, '2026-07-15 10:30:00'),
(2, 2, 3, 1, '2026-07-16 14:00:00'),
(NULL, 2, 1, NULL, '2026-07-17 09:15:00');

INSERT INTO detalle_venta (venta_id, producto_id, cantidad, precio_unitario) VALUES
(1, 1, 1, 1299.00),
(1, 11, 2, 399.00),
(2, 2, 1, 1599.00),
(2, 10, 1, 749.00),
(3, 3, 3, 599.00);

INSERT INTO apartados (cliente_id, empleado_id, estado, fecha_apartado) VALUES
(1, 2, 'ACTIVO', '2026-07-17 16:00:00');

INSERT INTO detalle_apartado (apartado_id, producto_id, cantidad, precio_unitario) VALUES
(1, 9, 1, 999.00),
(1, 5, 1, 799.00);

INSERT INTO abonos (apartado_id, monto, fecha_abono) VALUES
(1, 500.00, '2026-07-17 16:05:00');
