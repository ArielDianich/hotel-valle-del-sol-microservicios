package cl.hotelvalledelsol.carrito.repository;

import cl.hotelvalledelsol.carrito.model.CarritoReservas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoReservasRepository extends JpaRepository<CarritoReservas, Long> {
}
