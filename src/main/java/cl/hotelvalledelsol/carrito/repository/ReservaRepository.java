package cl.hotelvalledelsol.carrito.repository;

import cl.hotelvalledelsol.carrito.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
