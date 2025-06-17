package cl.duoc.hotel.inventario.repository;

import cl.duoc.hotel.inventario.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
