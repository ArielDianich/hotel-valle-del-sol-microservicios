package cl.hotelvalledelsol.inventario.service;

import cl.hotelvalledelsol.inventario.model.Amenidad;
import cl.hotelvalledelsol.inventario.repository.AmenidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmenidadService {

    private final AmenidadRepository repo;

    public AmenidadService(AmenidadRepository repo) {
        this.repo = repo;
    }

    public List<Amenidad> listarTodas() {
        return repo.findAll();
    }

    public Optional<Amenidad> obtenerPorId(Long id) {
        return repo.findById(id);
    }

    public Amenidad crear(Amenidad a) {
        return repo.save(a);
    }

    public Amenidad actualizar(Long id, Amenidad datos) {
        datos.setId(id);
        return repo.save(datos);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
