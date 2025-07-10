package cl.hotelvalledelsol.usuario.service;

import cl.hotelvalledelsol.usuario.model.Cliente;
import cl.hotelvalledelsol.usuario.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository repo;

    public ClienteService(ClienteRepository repo) {
        this.repo = repo;
    }

    public List<Cliente> listarTodos() {
        return repo.findAll();
    }

    public Optional<Cliente> obtenerPorId(Long id) {
        return repo.findById(id);
    }

    public Cliente crear(Cliente c) {
        return repo.save(c);
    }

    public Cliente actualizar(Long id, Cliente datos) {
        datos.setId(id);
        return repo.save(datos);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
