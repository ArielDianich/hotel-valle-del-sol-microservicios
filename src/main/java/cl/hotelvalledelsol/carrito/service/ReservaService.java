package cl.hotelvalledelsol.carrito.service;

import cl.hotelvalledelsol.carrito.model.Reserva;
import cl.hotelvalledelsol.carrito.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private final ReservaRepository repo;

    public ReservaService(ReservaRepository repo) {
        this.repo = repo;
    }

    public List<Reserva> listarTodas() {
        return repo.findAll();
    }

    public Optional<Reserva> obtenerPorId(Long id) {
        return repo.findById(id);
    }

    public Reserva crear(Reserva r) {
        return repo.save(r);
    }

    public Reserva actualizar(Long id, Reserva datos) {
        datos.setId(id);
        return repo.save(datos);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
