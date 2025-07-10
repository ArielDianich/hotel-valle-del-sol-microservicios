package cl.hotelvalledelsol.inventario.repository;

import cl.hotelvalledelsol.inventario.model.TipoHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoHabitacionRepository extends JpaRepository<TipoHabitacion, Long> {
}
