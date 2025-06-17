package cl.duoc.hotel.carrito.repository;

import cl.duoc.hotel.carrito.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
}
