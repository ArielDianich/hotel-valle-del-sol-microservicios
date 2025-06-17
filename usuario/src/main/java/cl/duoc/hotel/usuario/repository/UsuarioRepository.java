package cl.duoc.hotel.usuario.repository;

import cl.duoc.hotel.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
