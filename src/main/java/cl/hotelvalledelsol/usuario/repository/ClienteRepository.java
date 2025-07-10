package cl.hotelvalledelsol.usuario.repository;

import cl.hotelvalledelsol.usuario.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
