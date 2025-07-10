package cl.hotelvalledelsol.inventario.repository;

import cl.hotelvalledelsol.inventario.model.Amenidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenidadRepository extends JpaRepository<Amenidad, Long> {
}
