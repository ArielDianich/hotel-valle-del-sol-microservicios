package cl.duoc.hotel.carrito.controllers;

import jakarta.validation.Valid;
import cl.duoc.hotel.carrito.model.Carrito;
import cl.duoc.hotel.carrito.repository.CarritoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    private final CarritoRepository carritoRepository;

    public CarritoController(CarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
    }

    // GET: Listar todos los carritos
    @GetMapping
    public List<Carrito> getAll() {
        return carritoRepository.findAll();
    }

    // GET: Obtener un carrito por ID
    @GetMapping("/{id}")
    public ResponseEntity<Carrito> getById(@PathVariable Long id) {
        return carritoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Crear un nuevo carrito
    @PostMapping
    public Carrito save(@Valid @RequestBody Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    // PUT: Actualizar un carrito existente
    @PutMapping("/{id}")
    public ResponseEntity<Carrito> update(@PathVariable Long id, @Valid @RequestBody Carrito carritoActualizado) {
        return carritoRepository.findById(id)
                .map(carrito -> {
                    carrito.setProducto(carritoActualizado.getProducto());
                    carrito.setCantidad(carritoActualizado.getCantidad());
                    return ResponseEntity.ok(carritoRepository.save(carrito));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: Eliminar un carrito por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (carritoRepository.existsById(id)) {
            carritoRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }
}
