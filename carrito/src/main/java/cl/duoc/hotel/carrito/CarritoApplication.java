package cl.duoc.hotel.carrito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// Le decimos a Spring dónde buscar @Entity
@EntityScan(basePackages = {
    "cl.duoc.hotel.carrito.model",
    "cl.duoc.hotel.inventario.model"   // <— aquí la entidad Producto
})
// (Opcional, si tus repos están fuera del paquete principal)
@EnableJpaRepositories("cl.duoc.hotel.carrito.repository")
public class CarritoApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarritoApplication.class, args);
    }
}

