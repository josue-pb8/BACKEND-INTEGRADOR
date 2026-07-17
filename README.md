# Sistema de Boutique - MODA SYSTEM

Sistema de gestión integral para boutique que permite administrar productos, inventario, ventas, apartados, clientes y descuentos. Cuenta con tres roles: **Administrador**, **Empleado** y **Cliente**.

---

## Tecnologías

- **Frontend:** HTML, CSS, JavaScript
- **Backend:** Java (Orientado a Objetos), SQL
- **Base de datos:** MySQL (Workbench)
- **IDE:** IntelliJ IDEA
- **API Testing:** Postman

---

## Arquitectura

### Frontend
- Arquitectura por tecnología (HTML + CSS + JavaScript)

### Backend
- Arquitectura Limpia (Clean Architecture)
- Orientado a Objetos
- Capas: **Controladores → Servicios → Repositorios → Modelos**

---

## Roles del Sistema

---

### Administrador

#### Historias de Usuario

| # | Historia de Usuario |
|---|---------------------|
| HU-01 | Quiero iniciar sesión para acceder de forma segura al sistema. |
| HU-02 | Quiero registrar nuevos productos para mantener actualizado el inventario. |
| HU-03 | Quiero editar la información de un producto para corregir precios, tallas, colores o descripciones. |
| HU-04 | Quiero eliminar productos que ya no se venderán para mantener organizado el catálogo. |
| HU-05 | Quiero consultar el inventario para conocer la disponibilidad de cada producto. |
| HU-06 | Quiero recibir alertas de productos con stock bajo para reabastecerlos a tiempo. |
| HU-07 | Quiero registrar categorías para organizar mejor los productos. |
| HU-08 | Quiero modificar las categorías para mantener la organización del catálogo. |
| HU-09 | Quiero registrar descuentos para ofrecer promociones a los clientes. |
| HU-10 | Quiero editar o finalizar descuentos para mantener vigentes solo las promociones activas. |
| HU-11 | Quiero consultar todas las ventas realizadas para llevar el control del negocio. |
| HU-12 | Quiero ver el detalle de cada venta para conocer los productos vendidos, el cliente y el método de pago (en efectivo). |
| HU-13 | Quiero consultar las ganancias del negocio para evaluar el rendimiento de las ventas. |
| HU-14 | Quiero visualizar estadísticas de ventas para apoyar la toma de decisiones. |
| HU-15 | Quiero consultar los productos más vendidos para identificar los artículos con mayor demanda. |
| HU-16 | Quiero consultar las ventas por día para analizar el comportamiento del negocio. |
| HU-17 | Quiero consultar los apartados registrados para dar seguimiento a los pagos pendientes. |
| HU-18 | Quiero consultar la información de los clientes para brindar un mejor servicio. |
| HU-19 | Quiero cerrar sesión para proteger la información del sistema. |

#### Funciones Principales

- Iniciar y cerrar sesión.
- Administrar productos (agregar, editar, eliminar y consultar).
- Controlar el inventario.
- Administrar categorías.
- Administrar descuentos.
- Consultar ventas.
- Ver detalles de las ventas.
- Consultar clientes.
- Dar seguimiento a apartados.
- Consultar ganancias.
- Visualizar estadísticas (productos más vendidos, ventas mensuales, métodos de pago y stock bajo).

---

### Empleado

#### Historias de Usuario

| # | Historia de Usuario |
|---|---------------------|
| HU-01 | Como empleado, quiero iniciar sesión para acceder únicamente a las funciones autorizadas del sistema. |
| HU-02 | Como empleado, quiero consultar el catálogo de productos para ofrecer información a los clientes. |
| HU-03 | Como empleado, quiero consultar la disponibilidad de un producto para informar al cliente si está en existencia. |
| HU-04 | Como empleado, quiero registrar una venta para completar la compra de un cliente. |
| HU-05 | Como empleado, quiero seleccionar el método de pago para registrar correctamente la venta. |
| HU-06 | Como empleado, quiero registrar un apartado para apartar productos solicitados por un cliente. |
| HU-07 | Como empleado, quiero consultar los apartados registrados para informar a los clientes sobre el estado de sus compras. |
| HU-08 | Como empleado, quiero consultar la información de un cliente para brindar una mejor atención durante la venta. |
| HU-09 | Como empleado, quiero registrar nuevos clientes para facilitar futuras compras y apartados. |
| HU-10 | Como empleado, quiero actualizar los datos de un cliente cuando sea necesario. |
| HU-11 | Como empleado, quiero consultar los descuentos disponibles para aplicarlos correctamente durante la venta. |
| HU-12 | Como empleado, quiero consultar mi historial de ventas para dar seguimiento a las ventas que he realizado. |
| HU-13 | Como empleado, quiero cerrar sesión al finalizar mi jornada para proteger la información del sistema. |

#### Funciones que NO debería tener el empleado

Estas acciones corresponden únicamente al administrador:

- Agregar productos al inventario.
- Eliminar productos.
- Modificar precios.
- Crear o eliminar categorías.
- Crear o modificar descuentos.
- Consultar ganancias del negocio.
- Ver estadísticas generales.
- Eliminar ventas registradas.
- Autorizar cambios importantes sin permisos.

#### Funciones que SÍ puede realizar

- Iniciar y cerrar sesión.
- Consultar productos y existencias.
- Registrar ventas.
- Aplicar descuentos ya autorizados.
- Registrar clientes.
- Registrar y administrar apartados.
- Registrar abonos.
- Generar tickets.
- Consultar sus ventas.

---

### Cliente

#### Historias de Usuario

| # | Historia de Usuario |
|---|---------------------|
| HU-01 | Como cliente, quiero registrarme en el sistema para realizar compras y dar seguimiento a mis pedidos. |
| HU-02 | Como cliente, quiero iniciar sesión para acceder a mi cuenta de forma segura. |
| HU-03 | Como cliente, quiero consultar el catálogo de productos para conocer los artículos disponibles. |
| HU-04 | Como cliente, quiero buscar productos por nombre, categoría o marca para encontrar fácilmente lo que deseo. |
| HU-05 | Como cliente, quiero filtrar productos por talla, color o precio para encontrar opciones que se adapten a mis necesidades. |
| HU-06 | Como cliente, quiero ver la información detallada de un producto para conocer su precio, descripción y disponibilidad. |
| HU-07 | Como cliente, quiero agregar productos a mi carrito para comprarlos posteriormente. |
| HU-08 | Como cliente, quiero modificar la cantidad de productos en mi carrito para ajustar mi compra antes de pagar. |
| HU-09 | Como cliente, quiero eliminar productos de mi carrito para cambiar mi decisión de compra. |
| HU-10 | Como cliente, quiero realizar el pago de mi compra utilizando un método de pago disponible para completar mi pedido (pago en efectivo). |
| HU-11 | Como cliente, quiero consultar el estado de mis apartados para conocer cuánto me falta por pagar. |
| HU-12 | Como cliente, quiero consultar mi historial de compras para revisar mis pedidos anteriores. |
| HU-13 | Como cliente, quiero actualizar mis datos personales para mantener mi información correcta. |
| HU-14 | Como cliente, quiero recuperar mi contraseña en caso de olvidarla para volver a acceder a mi cuenta. |
| HU-15 | Como cliente, quiero cerrar sesión para proteger la seguridad de mi cuenta. |

#### ¿Qué puede hacer el cliente?

- Registrarse.
- Iniciar sesión.
- Ver el catálogo de productos.
- Buscar y filtrar productos.
- Ver detalles de un producto.
- Agregar productos al carrito.
- Modificar o eliminar productos del carrito.
- Realizar una compra.
- Consultar sus apartados.
- Consultar su historial de compras.
- Actualizar sus datos personales.
- Recuperar su contraseña.
- Cerrar sesión.

#### ¿Qué NO puede hacer el cliente?

El cliente no tiene acceso a funciones administrativas, por lo que no puede:

- Agregar, editar o eliminar productos.
- Modificar precios.
- Administrar inventario.
- Registrar descuentos.
- Consultar ventas generales.
- Ver ganancias del negocio.
- Acceder a estadísticas.
- Administrar categorías.

---

## Endpoints de la API

---

### Autenticación

#### POST `/api/auth/login`

Iniciar sesión y obtener token JWT.

**Body:**
```json
{
  "nombreUsuario": "string",
  "contrasena": "string"
}
```

**Response (200):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

**Error (401):**
```json
{
  "error": "Credenciales incorrectas"
}
```

---

#### POST `/api/auth/logout`

Cerrar sesión del usuario autenticado.

**Body:** Sin body

**Response (200):**
```json
{
  "mensaje": "Sesión cerrada correctamente"
}
```

---

#### POST `/api/auth/recuperar-contrasena`

Recuperar contraseña de un usuario.

**Body:**
```json
{
  "nombreUsuario": "string",
  "nuevaContrasena": "string"
}
```

**Response (200):**
```json
{
  "mensaje": "Contraseña actualizada correctamente"
}
```

**Error (404):**
```json
{
  "error": "Usuario no encontrado"
}
```

---

### Productos

#### GET `/api/productos`

Listar todos los productos.

**Body:** Sin body

**Response (200):**
```json
[
  {
    "id": 1,
    "nombre": "Falda Midi",
    "descripcion": "Falda midi elegante",
    "precio": 299.99,
    "marca": "Zara",
    "imagenUrl": "https://ejemplo.com/imagen.jpg",
    "categoria": {
      "id": 1,
      "nombre": "Faldas",
      "descripcion": "Faldas para dama",
      "activa": true
    },
    "activo": true,
    "fechaCreacion": "2025-07-10T14:30:00"
  }
]
```

---

#### GET `/api/productos/{id}`

Obtener un producto por su ID.

**Body:** Sin body

**Response (200):**
```json
{
  "id": 1,
  "nombre": "Falda Midi",
  "descripcion": "Falda midi elegante",
  "precio": 299.99,
  "marca": "Zara",
  "imagenUrl": "https://ejemplo.com/imagen.jpg",
  "categoria": {
    "id": 1,
    "nombre": "Faldas",
    "descripcion": "Faldas para dama",
    "activa": true
  },
  "activo": true,
  "fechaCreacion": "2025-07-10T14:30:00"
}
```

**Error (404):**
```json
{
  "error": "Producto no encontrado"
}
```

---

#### GET `/api/productos/buscar?nombre=&categoriaId=&marca=`

Buscar productos por nombre, categoría o marca (parámetros opcionales).

**Body:** Sin body

**Response (200):**
```json
[
  {
    "id": 1,
    "nombre": "Falda Midi",
    "descripcion": "Falda midi elegante",
    "precio": 299.99,
    "marca": "Zara",
    "imagenUrl": "https://ejemplo.com/imagen.jpg",
    "categoria": {
      "id": 1,
      "nombre": "Faldas",
      "descripcion": "Faldas para dama",
      "activa": true
    },
    "activo": true,
    "fechaCreacion": "2025-07-10T14:30:00"
  }
]
```

---

#### GET `/api/productos/filtrar?talla=&color=&precioMin=&precioMax=`

Filtrar productos por talla, color o rango de precio (parámetros opcionales).

**Body:** Sin body

**Response (200):**
```json
[
  {
    "id": 1,
    "nombre": "Falda Midi",
    "descripcion": "Falda midi elegante",
    "precio": 299.99,
    "marca": "Zara",
    "imagenUrl": "https://ejemplo.com/imagen.jpg",
    "categoria": {
      "id": 1,
      "nombre": "Faldas",
      "descripcion": "Faldas para dama",
      "activa": true
    },
    "activo": true,
    "fechaCreacion": "2025-07-10T14:30:00"
  }
]
```

---

#### POST `/api/productos`

Registrar un nuevo producto (Admin).

**Body:**
```json
{
  "nombre": "string",
  "descripcion": "string",
  "precio": 299.99,
  "marca": "string",
  "imagenUrl": "string",
  "categoria": {
    "id": 1
  },
  "activo": true
}
```

**Response (201):**
```json
{
  "id": 1,
  "nombre": "Falda Midi",
  "descripcion": "Falda midi elegante",
  "precio": 299.99,
  "marca": "Zara",
  "imagenUrl": "https://ejemplo.com/imagen.jpg",
  "categoria": {
    "id": 1,
    "nombre": "Faldas",
    "descripcion": "Faldas para dama",
    "activa": true
  },
  "activo": true,
  "fechaCreacion": "2025-07-10T14:30:00"
}
```

---

#### PUT `/api/productos/{id}`

Editar la información de un producto (Admin).

**Body:**
```json
{
  "nombre": "string",
  "descripcion": "string",
  "precio": 349.99,
  "marca": "string",
  "imagenUrl": "string",
  "categoria": {
    "id": 1
  },
  "activo": true
}
```

**Response (200):**
```json
{
  "id": 1,
  "nombre": "Falda Midi Actualizada",
  "descripcion": "Falda midi elegante actualizada",
  "precio": 349.99,
  "marca": "Zara",
  "imagenUrl": "https://ejemplo.com/imagen.jpg",
  "categoria": {
    "id": 1,
    "nombre": "Faldas",
    "descripcion": "Faldas para dama",
    "activa": true
  },
  "activo": true,
  "fechaCreacion": "2025-07-10T14:30:00"
}
```

**Error (404):**
```json
{
  "error": "Producto no encontrado"
}
```

---

#### DELETE `/api/productos/{id}`

Eliminar un producto (Admin).

**Body:** Sin body

**Response (200):**
```json
{
  "mensaje": "Producto eliminado correctamente"
}
```

**Error (404):**
```json
{
  "error": "Producto no encontrado"
}
```

---

### Categorías

#### GET `/api/categorias`

Listar todas las categorías.

**Body:** Sin body

**Response (200):**
```json
[
  {
    "id": 1,
    "nombre": "Faldas",
    "descripcion": "Faldas para dama",
    "activa": true
  }
]
```

---

#### POST `/api/categorias`

Registrar una nueva categoría (Admin).

**Body:**
```json
{
  "nombre": "string",
  "descripcion": "string",
  "activa": true
}
```

**Response (201):**
```json
{
  "id": 1,
  "nombre": "Vestidos",
  "descripcion": "Vestidos para dama",
  "activa": true
}
```

---

#### PUT `/api/categorias/{id}`

Modificar una categoría (Admin).

**Body:**
```json
{
  "nombre": "string",
  "descripcion": "string",
  "activa": true
}
```

**Response (200):**
```json
{
  "id": 1,
  "nombre": "Vestidos Actualizados",
  "descripcion": "Vestidos para dama actualizados",
  "activa": true
}
```

**Error (404):**
```json
{
  "error": "Categoría no encontrada"
}
```

---

#### DELETE `/api/categorias/{id}`

Eliminar una categoría (Admin).

**Body:** Sin body

**Response (200):**
```json
{
  "mensaje": "Categoría eliminada correctamente"
}
```

**Error (404):**
```json
{
  "error": "Categoría no encontrada"
}
```

---

### Descuentos

#### GET `/api/descuentos`

Listar todos los descuentos.

**Body:** Sin body

**Response (200):**
```json
[
  {
    "id": 1,
    "nombre": "Descuento Verano",
    "porcentaje": 15.00,
    "fechaInicio": "2025-06-01",
    "fechaFin": "2025-08-31",
    "activo": true
  }
]
```

---

#### GET `/api/descuentos/activos`

Listar solo los descuentos vigentes (activos y dentro del rango de fechas).

**Body:** Sin body

**Response (200):**
```json
[
  {
    "id": 1,
    "nombre": "Descuento Verano",
    "porcentaje": 15.00,
    "fechaInicio": "2025-06-01",
    "fechaFin": "2025-08-31",
    "activo": true
  }
]
```

---

#### POST `/api/descuentos`

Registrar un nuevo descuento (Admin).

**Body:**
```json
{
  "nombre": "string",
  "porcentaje": 10.00,
  "fechaInicio": "2025-07-01",
  "fechaFin": "2025-07-31",
  "activo": true
}
```

**Response (201):**
```json
{
  "id": 1,
  "nombre": "Descuento Especial",
  "porcentaje": 10.00,
  "fechaInicio": "2025-07-01",
  "fechaFin": "2025-07-31",
  "activo": true
}
```

---

#### PUT `/api/descuentos/{id}`

Editar un descuento (Admin).

**Body:**
```json
{
  "nombre": "string",
  "porcentaje": 20.00,
  "fechaInicio": "2025-07-01",
  "fechaFin": "2025-07-31",
  "activo": true
}
```

**Response (200):**
```json
{
  "id": 1,
  "nombre": "Descuento Especial Actualizado",
  "porcentaje": 20.00,
  "fechaInicio": "2025-07-01",
  "fechaFin": "2025-07-31",
  "activo": true
}
```

**Error (404):**
```json
{
  "error": "Descuento no encontrado"
}
```

---

#### PUT `/api/descuentos/{id}/finalizar`

Finalizar un descuento (Admin). Marca el descuento como inactivo.

**Body:** Sin body

**Response (200):**
```json
{
  "mensaje": "Descuento finalizado correctamente"
}
```

**Error (404):**
```json
{
  "error": "Descuento no encontrado"
}
```

---

### Inventario

#### GET `/api/inventario`

Consultar el inventario completo.

**Body:** Sin body

**Response (200):**
```json
[
  {
    "id": 1,
    "producto": {
      "id": 1,
      "nombre": "Falda Midi",
      "descripcion": "Falda midi elegante",
      "precio": 299.99,
      "marca": "Zara",
      "imagenUrl": "https://ejemplo.com/imagen.jpg",
      "categoria": {
        "id": 1,
        "nombre": "Faldas",
        "descripcion": "Faldas para dama",
        "activa": true
      },
      "activo": true,
      "fechaCreacion": "2025-07-10T14:30:00"
    },
    "talla": "M",
    "color": "Negro",
    "stock": 15,
    "stockMinimo": 5
  }
]
```

---

#### GET `/api/inventario/alertas-stock`

Consultar productos con stock por debajo del mínimo (Admin).

**Body:** Sin body

**Response (200):**
```json
[
  {
    "id": 2,
    "producto": {
      "id": 2,
      "nombre": "Blusa Seda",
      "descripcion": "Blusa de seda elegante",
      "precio": 189.99,
      "marca": "H&M",
      "imagenUrl": "https://ejemplo.com/blusa.jpg",
      "categoria": {
        "id": 2,
        "nombre": "Blusas",
        "descripcion": "Blusas para dama",
        "activa": true
      },
      "activo": true,
      "fechaCreacion": "2025-07-10T14:30:00"
    },
    "talla": "S",
    "color": "Blanco",
    "stock": 2,
    "stockMinimo": 5
  }
]
```

---

#### GET `/api/inventario/{productoId}`

Consultar la disponibilidad de un producto específico.

**Body:** Sin body

**Response (200):**
```json
{
  "id": 1,
  "producto": {
    "id": 1,
    "nombre": "Falda Midi",
    "descripcion": "Falda midi elegante",
    "precio": 299.99,
    "marca": "Zara",
    "imagenUrl": "https://ejemplo.com/imagen.jpg",
    "categoria": {
      "id": 1,
      "nombre": "Faldas",
      "descripcion": "Faldas para dama",
      "activa": true
    },
    "activo": true,
    "fechaCreacion": "2025-07-10T14:30:00"
  },
  "talla": "M",
  "color": "Negro",
  "stock": 15,
  "stockMinimo": 5
}
```

---

#### POST `/api/inventario`

Registrar un nuevo registro de inventario.

**Body:**
```json
{
  "producto": {
    "id": 1
  },
  "talla": "string",
  "color": "string",
  "stock": 10,
  "stockMinimo": 5
}
```

**Response (201):**
```json
{
  "id": 1,
  "producto": {
    "id": 1,
    "nombre": "Falda Midi",
    "descripcion": "Falda midi elegante",
    "precio": 299.99,
    "marca": "Zara",
    "imagenUrl": "https://ejemplo.com/imagen.jpg",
    "categoria": {
      "id": 1,
      "nombre": "Faldas",
      "descripcion": "Faldas para dama",
      "activa": true
    },
    "activo": true,
    "fechaCreacion": "2025-07-10T14:30:00"
  },
  "talla": "M",
  "color": "Negro",
  "stock": 10,
  "stockMinimo": 5
}
```

---

#### PUT `/api/inventario/{id}`

Actualizar el stock de un registro de inventario.

**Body:**
```json
{
  "stock": 20
}
```

**Response (200):**
```json
{
  "id": 1,
  "producto": {
    "id": 1,
    "nombre": "Falda Midi",
    "descripcion": "Falda midi elegante",
    "precio": 299.99,
    "marca": "Zara",
    "imagenUrl": "https://ejemplo.com/imagen.jpg",
    "categoria": {
      "id": 1,
      "nombre": "Faldas",
      "descripcion": "Faldas para dama",
      "activa": true
    },
    "activo": true,
    "fechaCreacion": "2025-07-10T14:30:00"
  },
  "talla": "M",
  "color": "Negro",
  "stock": 20,
  "stockMinimo": 5
}
```

**Error (404):**
```json
{
  "error": "Registro de inventario no encontrado"
}
```

---

### Ventas

#### GET `/api/ventas`

Listar todas las ventas (Admin).

**Body:** Sin body

**Response (200):**
```json
[
  {
    "id": 1,
    "cliente": {
      "id": 1,
      "nombre": "María",
      "apellido": "García",
      "email": "maria@email.com",
      "telefono": "5551234567"
    },
    "empleado": {
      "id": 2,
      "nombreUsuario": "empleado1",
      "rol": {
        "id": 2,
        "nombre": "Empleado"
      }
    },
    "metodoPago": {
      "id": 1,
      "nombre": "Efectivo"
    },
    "total": 599.98,
    "descuento": null,
    "fechaVenta": "2025-07-10T15:00:00",
    "detalles": [
      {
        "id": 1,
        "producto": {
          "id": 1,
          "nombre": "Falda Midi",
          "precio": 299.99
        },
        "cantidad": 2,
        "precioUnitario": 299.99,
        "subtotal": 599.98
      }
    ]
  }
]
```

---

#### GET `/api/ventas/{id}`

Obtener el detalle de una venta específica.

**Body:** Sin body

**Response (200):**
```json
{
  "id": 1,
  "cliente": {
    "id": 1,
    "nombre": "María",
    "apellido": "García",
    "email": "maria@email.com",
    "telefono": "5551234567"
  },
  "empleado": {
    "id": 2,
    "nombreUsuario": "empleado1",
    "rol": {
      "id": 2,
      "nombre": "Empleado"
    }
  },
  "metodoPago": {
    "id": 1,
    "nombre": "Efectivo"
  },
  "total": 599.98,
  "descuento": null,
  "fechaVenta": "2025-07-10T15:00:00",
  "detalles": [
    {
      "id": 1,
      "producto": {
        "id": 1,
        "nombre": "Falda Midi",
        "precio": 299.99
      },
      "cantidad": 2,
      "precioUnitario": 299.99,
      "subtotal": 599.98
    }
  ]
}
```

**Error (404):**
```json
{
  "error": "Venta no encontrada"
}
```

---

#### GET `/api/ventas/dia?fecha=`

Consultar las ventas de un día específico (Admin). Si no se envía fecha, usa la fecha actual.

**Body:** Sin body

**Response (200):**
```json
[
  {
    "id": 1,
    "cliente": {
      "id": 1,
      "nombre": "María",
      "apellido": "García"
    },
    "empleado": {
      "id": 2,
      "nombreUsuario": "empleado1"
    },
    "metodoPago": {
      "id": 1,
      "nombre": "Efectivo"
    },
    "total": 599.98,
    "descuento": null,
    "fechaVenta": "2025-07-10T15:00:00",
    "detalles": []
  }
]
```

---

#### GET `/api/ventas/historial/{empleadoId}`

Consultar el historial de ventas de un empleado.

**Body:** Sin body

**Response (200):**
```json
[
  {
    "id": 1,
    "cliente": {
      "id": 1,
      "nombre": "María",
      "apellido": "García"
    },
    "empleado": {
      "id": 2,
      "nombreUsuario": "empleado1"
    },
    "metodoPago": {
      "id": 1,
      "nombre": "Efectivo"
    },
    "total": 599.98,
    "descuento": null,
    "fechaVenta": "2025-07-10T15:00:00",
    "detalles": []
  }
]
```

---

#### GET `/api/ventas/cliente/{clienteId}`

Consultar el historial de compras de un cliente.

**Body:** Sin body

**Response (200):**
```json
[
  {
    "id": 1,
    "cliente": {
      "id": 1,
      "nombre": "María",
      "apellido": "García"
    },
    "empleado": {
      "id": 2,
      "nombreUsuario": "empleado1"
    },
    "metodoPago": {
      "id": 1,
      "nombre": "Efectivo"
    },
    "total": 599.98,
    "descuento": null,
    "fechaVenta": "2025-07-10T15:00:00",
    "detalles": []
  }
]
```

---

#### POST `/api/ventas`

Registrar una nueva venta (Empleado).

**Body:**
```json
{
  "clienteId": 1,
  "empleadoId": 2,
  "metodoPagoId": 1,
  "descuentoId": null,
  "detalles": [
    {
      "productoId": 1,
      "cantidad": 2,
      "precioUnitario": 299.99
    }
  ]
}
```

**Response (201):**
```json
{
  "id": 1,
  "cliente": {
    "id": 1,
    "nombre": "María",
    "apellido": "García",
    "email": "maria@email.com",
    "telefono": "5551234567"
  },
  "empleado": {
    "id": 2,
    "nombreUsuario": "empleado1",
    "rol": {
      "id": 2,
      "nombre": "Empleado"
    }
  },
  "metodoPago": {
    "id": 1,
    "nombre": "Efectivo"
  },
  "total": 599.98,
  "descuento": null,
  "fechaVenta": "2025-07-10T15:00:00",
  "detalles": [
    {
      "id": 1,
      "producto": {
        "id": 1,
        "nombre": "Falda Midi"
      },
      "cantidad": 2,
      "precioUnitario": 299.99,
      "subtotal": 599.98
    }
  ]
}
```

---

### Clientes

#### GET `/api/clientes`

Listar todos los clientes (Admin).

**Body:** Sin body

**Response (200):**
```json
[
  {
    "id": 1,
    "usuario": {
      "id": 3,
      "nombreUsuario": "maria123",
      "rol": {
        "id": 3,
        "nombre": "Cliente"
      },
      "activo": true,
      "fechaCreacion": "2025-07-10T10:00:00"
    },
    "nombre": "María",
    "apellido": "García",
    "email": "maria@email.com",
    "telefono": "5551234567",
    "direccion": "Calle Principal 123",
    "fechaNacimiento": "1995-05-15",
    "fechaRegistro": "2025-07-10T10:00:00"
  }
]
```

---

#### GET `/api/clientes/{id}`

Obtener la información de un cliente específico.

**Body:** Sin body

**Response (200):**
```json
{
  "id": 1,
  "usuario": {
    "id": 3,
    "nombreUsuario": "maria123",
    "rol": {
      "id": 3,
      "nombre": "Cliente"
    },
    "activo": true,
    "fechaCreacion": "2025-07-10T10:00:00"
  },
  "nombre": "María",
  "apellido": "García",
  "email": "maria@email.com",
  "telefono": "5551234567",
  "direccion": "Calle Principal 123",
  "fechaNacimiento": "1995-05-15",
  "fechaRegistro": "2025-07-10T10:00:00"
}
```

**Error (404):**
```json
{
  "error": "Cliente no encontrado"
}
```

---

#### POST `/api/clientes`

Registrar un nuevo cliente.

**Body:**
```json
{
  "nombreUsuario": "string",
  "contrasena": "string",
  "nombre": "string",
  "apellido": "string",
  "email": "string",
  "telefono": "string"
}
```

**Response (201):**
```json
{
  "id": 1,
  "usuario": {
    "id": 3,
    "nombreUsuario": "maria123",
    "rol": {
      "id": 3,
      "nombre": "Cliente"
    },
    "activo": true,
    "fechaCreacion": "2025-07-10T10:00:00"
  },
  "nombre": "María",
  "apellido": "García",
  "email": "maria@email.com",
  "telefono": "5551234567",
  "direccion": null,
  "fechaNacimiento": null,
  "fechaRegistro": "2025-07-10T10:00:00"
}
```

---

#### PUT `/api/clientes/{id}`

Actualizar los datos de un cliente.

**Body:**
```json
{
  "nombre": "string",
  "apellido": "string",
  "email": "string",
  "telefono": "string",
  "direccion": "string",
  "fechaNacimiento": "1995-05-15"
}
```

**Response (200):**
```json
{
  "id": 1,
  "usuario": {
    "id": 3,
    "nombreUsuario": "maria123",
    "rol": {
      "id": 3,
      "nombre": "Cliente"
    },
    "activo": true,
    "fechaCreacion": "2025-07-10T10:00:00"
  },
  "nombre": "María Actualizada",
  "apellido": "García",
  "email": "maria.nuevo@email.com",
  "telefono": "5559876543",
  "direccion": "Nueva Dirección 456",
  "fechaNacimiento": "1995-05-15",
  "fechaRegistro": "2025-07-10T10:00:00"
}
```

**Error (404):**
```json
{
  "error": "Cliente no encontrado"
}
```

---

### Apartados

#### GET `/api/apartados`

Listar todos los apartados.

**Body:** Sin body

**Response (200):**
```json
[
  {
    "id": 1,
    "cliente": {
      "id": 1,
      "nombre": "María",
      "apellido": "García"
    },
    "empleado": {
      "id": 2,
      "nombreUsuario": "empleado1"
    },
    "total": 599.98,
    "abonado": 200.00,
    "pendiente": 399.98,
    "estado": "ACTIVO",
    "fechaApartado": "2025-07-10T16:00:00",
    "detalles": [
      {
        "id": 1,
        "producto": {
          "id": 1,
          "nombre": "Falda Midi"
        },
        "cantidad": 2,
        "precioUnitario": 299.99,
        "subtotal": 599.98
      }
    ]
  }
]
```

---

#### GET `/api/apartados/{id}`

Obtener el detalle de un apartado específico.

**Body:** Sin body

**Response (200):**
```json
{
  "id": 1,
  "cliente": {
    "id": 1,
    "nombre": "María",
    "apellido": "García"
  },
  "empleado": {
    "id": 2,
    "nombreUsuario": "empleado1"
  },
  "total": 599.98,
  "abonado": 200.00,
  "pendiente": 399.98,
  "estado": "ACTIVO",
  "fechaApartado": "2025-07-10T16:00:00",
  "detalles": [
    {
      "id": 1,
      "producto": {
        "id": 1,
        "nombre": "Falda Midi"
      },
      "cantidad": 2,
      "precioUnitario": 299.99,
      "subtotal": 599.98
    }
  ]
}
```

**Error (404):**
```json
{
  "error": "Apartado no encontrado"
}
```

---

#### GET `/api/apartados/cliente/{clienteId}`

Consultar los apartados de un cliente específico.

**Body:** Sin body

**Response (200):**
```json
[
  {
    "id": 1,
    "cliente": {
      "id": 1,
      "nombre": "María",
      "apellido": "García"
    },
    "empleado": {
      "id": 2,
      "nombreUsuario": "empleado1"
    },
    "total": 599.98,
    "abonado": 200.00,
    "pendiente": 399.98,
    "estado": "ACTIVO",
    "fechaApartado": "2025-07-10T16:00:00",
    "detalles": []
  }
]
```

---

#### POST `/api/apartados`

Registrar un nuevo apartado (Empleado).

**Body:**
```json
{
  "clienteId": 1,
  "empleadoId": 2,
  "detalles": [
    {
      "productoId": 1,
      "cantidad": 2,
      "precioUnitario": 299.99
    }
  ]
}
```

**Response (201):**
```json
{
  "id": 1,
  "cliente": {
    "id": 1,
    "nombre": "María",
    "apellido": "García"
  },
  "empleado": {
    "id": 2,
    "nombreUsuario": "empleado1"
  },
  "total": 599.98,
  "abonado": 0.00,
  "pendiente": 599.98,
  "estado": "ACTIVO",
  "fechaApartado": "2025-07-10T16:00:00",
  "detalles": [
    {
      "id": 1,
      "producto": {
        "id": 1,
        "nombre": "Falda Midi"
      },
      "cantidad": 2,
      "precioUnitario": 299.99,
      "subtotal": 599.98
    }
  ]
}
```

---

#### PUT `/api/apartados/{id}/abono`

Registrar un abono a un apartado (Empleado).

**Body:**
```json
{
  "monto": 200.00
}
```

**Response (200):**
```json
{
  "id": 1,
  "cliente": {
    "id": 1,
    "nombre": "María",
    "apellido": "García"
  },
  "empleado": {
    "id": 2,
    "nombreUsuario": "empleado1"
  },
  "total": 599.98,
  "abonado": 200.00,
  "pendiente": 399.98,
  "estado": "ACTIVO",
  "fechaApartado": "2025-07-10T16:00:00",
  "detalles": []
}
```

**Error (404):**
```json
{
  "error": "Apartado no encontrado"
}
```

---

### Carrito

#### GET `/api/carrito/{clienteId}`

Ver el carrito de compras de un cliente.

**Body:** Sin body

**Response (200):**
```json
[
  {
    "id": 1,
    "cliente": {
      "id": 1,
      "nombre": "María",
      "apellido": "García"
    },
    "producto": {
      "id": 1,
      "nombre": "Falda Midi",
      "descripcion": "Falda midi elegante",
      "precio": 299.99,
      "marca": "Zara",
      "imagenUrl": "https://ejemplo.com/imagen.jpg",
      "categoria": {
        "id": 1,
        "nombre": "Faldas",
        "descripcion": "Faldas para dama",
        "activa": true
      },
      "activo": true,
      "fechaCreacion": "2025-07-10T14:30:00"
    },
    "cantidad": 2,
    "fechaAgregado": "2025-07-10T17:00:00"
  }
]
```

---

#### POST `/api/carrito/{clienteId}/agregar`

Agregar un producto al carrito de un cliente.

**Body:**
```json
{
  "productoId": 1,
  "cantidad": 2
}
```

**Response (201):**
```json
{
  "id": 1,
  "cliente": {
    "id": 1,
    "nombre": "María",
    "apellido": "García"
  },
  "producto": {
    "id": 1,
    "nombre": "Falda Midi",
    "descripcion": "Falda midi elegante",
    "precio": 299.99,
    "marca": "Zara",
    "imagenUrl": "https://ejemplo.com/imagen.jpg",
    "categoria": {
      "id": 1,
      "nombre": "Faldas",
      "descripcion": "Faldas para dama",
      "activa": true
    },
    "activo": true,
    "fechaCreacion": "2025-07-10T14:30:00"
  },
  "cantidad": 2,
  "fechaAgregado": "2025-07-10T17:00:00"
}
```

---

#### PUT `/api/carrito/{clienteId}/actualizar`

Actualizar la cantidad de un producto en el carrito. Si la cantidad es 0, el producto se elimina.

**Body:**
```json
{
  "productoId": 1,
  "cantidad": 3
}
```

**Response (200):**
```json
{
  "id": 1,
  "cliente": {
    "id": 1,
    "nombre": "María",
    "apellido": "García"
  },
  "producto": {
    "id": 1,
    "nombre": "Falda Midi",
    "descripcion": "Falda midi elegante",
    "precio": 299.99,
    "marca": "Zara",
    "imagenUrl": "https://ejemplo.com/imagen.jpg",
    "categoria": {
      "id": 1,
      "nombre": "Faldas",
      "descripcion": "Faldas para dama",
      "activa": true
    },
    "activo": true,
    "fechaCreacion": "2025-07-10T14:30:00"
  },
  "cantidad": 3,
  "fechaAgregado": "2025-07-10T17:00:00"
}
```

**Response si cantidad es 0:**
```json
{
  "mensaje": "Producto eliminado del carrito"
}
```

---

#### DELETE `/api/carrito/{clienteId}/eliminar/{productoId}`

Eliminar un producto del carrito.

**Body:** Sin body

**Response (200):**
```json
{
  "mensaje": "Producto eliminado del carrito"
}
```

**Error (404):**
```json
{
  "error": "Producto no encontrado en el carrito"
}
```

---

### Estadísticas (Admin)

#### GET `/api/estadisticas/ventas-mensuales?mes=&anio=`

Consultar las ventas mensuales. Si no se envían parámetros, usa el mes y año actuales.

**Body:** Sin body

**Response (200):**
```json
{
  "mes": 7,
  "anio": 2025,
  "totalVentas": 15,
  "montoTotal": 4500.00
}
```

---

#### GET `/api/estadisticas/productos-mas-vendidos`

Consultar los productos más vendidos.

**Body:** Sin body

**Response (200):**
```json
[
  {
    "productoId": 1,
    "nombre": "Falda Midi",
    "totalVendidos": 25
  },
  {
    "productoId": 3,
    "nombre": "Blusa Seda",
    "totalVendidos": 18
  }
]
```

---

#### GET `/api/estadisticas/ganancias`

Consultar las ganancias del negocio.

**Body:** Sin body

**Response (200):**
```json
{
  "gananciasTotales": 15000.00,
  "totalVentas": 50
}
```

---

#### GET `/api/estadisticas/metodos-pago`

Consultar los métodos de pago utilizados y su distribución.

**Body:** Sin body

**Response (200):**
```json
[
  {
    "metodoPagoId": 1,
    "nombre": "Efectivo",
    "cantidadUsos": 35
  },
  {
    "metodoPagoId": 2,
    "nombre": "Tarjeta",
    "cantidadUsos": 15
  }
]
```
