CREATE DATABASE IF NOT EXISTS boutique_moda;
USE boutique_moda;

-- Roles
CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO roles (nombre) VALUES ('ADMIN'), ('EMPLEADO'), ('CLIENTE');

-- Usuarios (login)
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    rol_id INT NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (rol_id) REFERENCES roles(id)
);

-- Clientes
CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE,
    telefono VARCHAR(20),
    direccion TEXT,
    fecha_nacimiento DATE,
    fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Categorias
CREATE TABLE categorias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    descripcion TEXT,
    activa BOOLEAN DEFAULT TRUE
);

-- Productos
CREATE TABLE productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(200) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL,
    marca VARCHAR(100),
    imagen_url VARCHAR(500),
    categoria_id INT,
    activo BOOLEAN DEFAULT TRUE,
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);

-- Inventario (stock por talla/color)
CREATE TABLE inventario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    producto_id INT NOT NULL,
    talla VARCHAR(20),
    color VARCHAR(50),
    stock INT NOT NULL DEFAULT 0,
    stock_minimo INT DEFAULT 5,
    FOREIGN KEY (producto_id) REFERENCES productos(id),
    UNIQUE KEY unique_variante (producto_id, talla, color)
);

-- Descuentos
CREATE TABLE descuentos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    porcentaje DECIMAL(5,2) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);

-- Metodos de pago
CREATE TABLE metodos_pago (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);

INSERT INTO metodos_pago (nombre) VALUES ('Efectivo');

-- Ventas (sin total - se calcula desde detalle_venta)
CREATE TABLE ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT,
    empleado_id INT NOT NULL,
    metodo_pago_id INT NOT NULL,
    descuento_id INT,
    fecha_venta DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    FOREIGN KEY (empleado_id) REFERENCES usuarios(id),
    FOREIGN KEY (metodo_pago_id) REFERENCES metodos_pago(id),
    FOREIGN KEY (descuento_id) REFERENCES descuentos(id)
);

-- Detalle de venta (sin subtotal - se calcula: cantidad * precio_unitario)
CREATE TABLE detalle_venta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    venta_id INT NOT NULL,
    producto_id INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (venta_id) REFERENCES ventas(id),
    FOREIGN KEY (producto_id) REFERENCES productos(id)
);

-- Apartados (sin total, abonado, pendiente - todo se calcula)
CREATE TABLE apartados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    empleado_id INT NOT NULL,
    estado ENUM('ACTIVO', 'PAGADO', 'CANCELADO') DEFAULT 'ACTIVO',
    fecha_apartado DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    FOREIGN KEY (empleado_id) REFERENCES usuarios(id)
);

-- Detalle de apartado (sin subtotal - se calcula: cantidad * precio_unitario)
CREATE TABLE detalle_apartado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    apartado_id INT NOT NULL,
    producto_id INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (apartado_id) REFERENCES apartados(id),
    FOREIGN KEY (producto_id) REFERENCES productos(id)
);

-- Abonos (tabla nueva para registrar pagos de apartados)
CREATE TABLE abonos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    apartado_id INT NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    fecha_abono DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (apartado_id) REFERENCES apartados(id)
);

-- Carrito
CREATE TABLE carrito (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    producto_id INT NOT NULL,
    cantidad INT NOT NULL DEFAULT 1,
    fecha_agregado DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    FOREIGN KEY (producto_id) REFERENCES productos(id),
    UNIQUE KEY unique_carrito (cliente_id, producto_id)
);
