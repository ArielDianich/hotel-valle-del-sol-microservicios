package cl.hotelvalledelsol.carrito.service;

import cl.hotelvalledelsol.carrito.model.CarritoReservas;
import cl.hotelvalledelsol.carrito.repository.CarritoReservasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoReservasService {

    private final CarritoReservasRepository repo;

    public CarritoReservasService(CarritoReservasRepository repo) {
        this.repo = repo;
    }

    public List<CarritoReservas> listarTodos() {
        return repo.findAll();
    }

    public Optional<CarritoReservas> obtenerPorId(Long id) {
        return repo.findById(id);
    }

    public CarritoReservas crear(CarritoReservas c) {
        return repo.save(c);
    }

    public CarritoReservas actualizar(Long id, CarritoReservas datos) {
        datos.setId(id);
        return repo.save(datos);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
