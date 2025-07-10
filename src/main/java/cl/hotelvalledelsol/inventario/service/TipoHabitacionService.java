package cl.hotelvalledelsol.inventario.service;

import cl.hotelvalledelsol.inventario.model.TipoHabitacion;
import cl.hotelvalledelsol.inventario.repository.TipoHabitacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoHabitacionService {

    private final TipoHabitacionRepository repo;

    public TipoHabitacionService(TipoHabitacionRepository repo) {
        this.repo = repo;
    }

    public List<TipoHabitacion> listarTodos() {
        return repo.findAll();
    }

    public Optional<TipoHabitacion> obtenerPorId(Long id) {
        return repo.findById(id);
    }

    public TipoHabitacion crear(TipoHabitacion t) {
        return repo.save(t);
    }

    public TipoHabitacion actualizar(Long id, TipoHabitacion datos) {
        datos.setId(id);
        return repo.save(datos);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
