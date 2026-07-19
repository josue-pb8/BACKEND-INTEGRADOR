package com.boutique;

import com.boutique.config.JwtMiddleware;
import com.boutique.controllers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.json.JavalinJackson;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {

    private static Handler withAuth(JwtMiddleware middleware, Handler next) {
        return ctx -> {
            middleware.handle(ctx);
            next.handle(ctx);
        };
    }

    public static void main(String[] args) {

        AuthController authController = new AuthController();
        ProductoController productoController = new ProductoController();
        CategoriaController categoriaController = new CategoriaController();
        DescuentoController descuentoController = new DescuentoController();
        InventarioController inventarioController = new InventarioController();
        VentaController ventaController = new VentaController();
        ClienteController clienteController = new ClienteController();
        ApartadoController apartadoController = new ApartadoController();
        CarritoController carritoController = new CarritoController();
        EstadisticaController estadisticaController = new EstadisticaController();
        EmpleadoController empleadoController = new EmpleadoController();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        Javalin app = Javalin.create(config -> {
            config.http.maxRequestSize = 20_000_000; // 20 MB
            config.jsonMapper(new JavalinJackson(mapper, true));

            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(rule -> rule.anyHost());
            });

            config.routes.apiBuilder(() -> {
                get("/", ctx -> ctx.json(java.util.Map.of(
                        "mensaje", "API Boutique MODA SYSTEM funcionando",
                        "version", "1.0")));

                path("api", () -> {
                    path("auth", () -> {
                        post("/login", authController::login);
                        post("/logout", withAuth(new JwtMiddleware(null), authController::logout));
                        post("/recuperar-contrasena", authController::recuperarContrasena);
                    });

                    path("productos", () -> {
                        get(productoController::listar);
                        get("/buscar", productoController::buscar);
                        get("/filtrar", productoController::filtrar);
                        get("/{id}", productoController::buscarPorId);
                        post(withAuth(new JwtMiddleware("ADMIN"), productoController::crear));
                        put("/{id}", withAuth(new JwtMiddleware("ADMIN"), productoController::actualizar));
                        delete("/{id}", withAuth(new JwtMiddleware("ADMIN"), productoController::eliminar));
                    });

                    path("categorias", () -> {
                        get(categoriaController::listar);
                        get("/{id}", categoriaController::buscarPorId);
                        post(withAuth(new JwtMiddleware("ADMIN"), categoriaController::crear));
                        put("/{id}", withAuth(new JwtMiddleware("ADMIN"), categoriaController::actualizar));
                        delete("/{id}", withAuth(new JwtMiddleware("ADMIN"), categoriaController::eliminar));
                    });

                    path("descuentos", () -> {
                        get(descuentoController::listar);
                        get("/activos", descuentoController::listarActivos);
                        get("/{id}", descuentoController::buscarPorId);
                        post(withAuth(new JwtMiddleware("ADMIN"), descuentoController::crear));
                        put("/{id}", withAuth(new JwtMiddleware("ADMIN"), descuentoController::actualizar));
                        put("/{id}/finalizar", withAuth(new JwtMiddleware("ADMIN"), descuentoController::finalizar));
                    });

                    path("inventario", () -> {
                        get(inventarioController::listar);
                        get("/alertas-stock", withAuth(new JwtMiddleware("ADMIN"), inventarioController::alertasStockBajo));
                        get("/producto/{productoId}", inventarioController::buscarPorProducto);
                        post(withAuth(new JwtMiddleware("ADMIN"), inventarioController::crear));
                        put("/{id}", withAuth(new JwtMiddleware("ADMIN"), inventarioController::actualizarStock));
                    });

                    path("ventas", () -> {
                        get(withAuth(new JwtMiddleware("ADMIN"), ventaController::listar));
                        get("/dia", withAuth(new JwtMiddleware("ADMIN"), ventaController::ventasPorDia));
                        get("/historial/{empleadoId}", withAuth(new JwtMiddleware(null), ventaController::historialEmpleado));
                        get("/cliente/{clienteId}", withAuth(new JwtMiddleware(null), ventaController::historialCliente));
                        get("/{id}", ventaController::buscarPorId);
                        post(withAuth(new JwtMiddleware("EMPLEADO"), ventaController::registrar));
                    });

                    path("clientes", () -> {
                        get(withAuth(new JwtMiddleware("ADMIN"), clienteController::listar));
                        get("/{id}", clienteController::buscarPorId);
                        post(clienteController::registrar);
                        put("/{id}", clienteController::actualizar);
                    });

                    path("empleados", () -> {
                        get(empleadoController::listar);
                        get("/{id}", empleadoController::buscarPorId);
                        post(empleadoController::registrar);
                        put("/{id}", empleadoController::actualizar);
                        delete("/{id}", empleadoController::eliminar);
                    });


                    path("apartados", () -> {
                        get(apartadoController::listar);
                        get("/cliente/{clienteId}", apartadoController::buscarPorCliente);
                        get("/{id}", apartadoController::buscarPorId);
                        post(withAuth(new JwtMiddleware("EMPLEADO"), apartadoController::registrar));
                        put("/{id}/abono", withAuth(new JwtMiddleware(null), apartadoController::registrarAbono));
                    });

                    path("carrito", () -> {
                        get("/{clienteId}", withAuth(new JwtMiddleware(null), carritoController::listar));
                        post("/{clienteId}/agregar", withAuth(new JwtMiddleware(null), carritoController::agregar));
                        put("/{clienteId}/actualizar", withAuth(new JwtMiddleware(null), carritoController::actualizarCantidad));
                        delete("/{clienteId}/eliminar/{productoId}", withAuth(new JwtMiddleware(null),
                                carritoController::eliminar));
                    });

                    path("estadisticas", () -> {
                        get("/ventas-mensuales", withAuth(new JwtMiddleware("ADMIN"), estadisticaController::ventasMensuales));
                        get("/productos-mas-vendidos", withAuth(new JwtMiddleware("ADMIN"),
                                estadisticaController::productosMasVendidos));
                        get("/ganancias", withAuth(new JwtMiddleware("ADMIN"), estadisticaController::ganancias));
                        get("/metodos-pago", withAuth(new JwtMiddleware("ADMIN"), estadisticaController::metodosPago));
                    });
                });
            });
        }).start(8081);

        System.out.println("Servidor Boutique MODA SYSTEM iniciado en http://localhost:8080");
    }
}
