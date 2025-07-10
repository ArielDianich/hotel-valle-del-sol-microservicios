package cl.hotelvalledelsol.inventario.service;

import cl.hotelvalledelsol.inventario.model.Habitacion;
import cl.hotelvalledelsol.inventario.repository.HabitacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitacionService {

    private final HabitacionRepository repo;

    public HabitacionService(HabitacionRepository repo) {
        this.repo = repo;
    }

    public List<Habitacion> listarTodas() {
        return repo.findAll();
    }

    public Optional<Habitacion> obtenerPorId(Long id) {
        return repo.findById(id);
    }

    public Habitacion crear(Habitacion h) {
        return repo.save(h);
    }

    public Habitacion actualizar(Long id, Habitacion datos) {
        datos.setId(id);
        return repo.save(datos);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
