package cl.hotelvalledelsol.inventario.repository;

import cl.hotelvalledelsol.inventario.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
}
