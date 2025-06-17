package cl.duoc.hotel.carrito.controller;

import cl.duoc.hotel.carrito.model.Carrito;
import cl.duoc.hotel.carrito.repository.CarritoRepository;
import jakarta.validation.Valid;
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

    // GET: Listar todos los ítems del carrito
    @GetMapping
    public List<Carrito> getAll() {
        return carritoRepository.findAll();
    }

    // GET: Obtener un ítem por ID
    @GetMapping("/{id}")
    public ResponseEntity<Carrito> getById(@PathVariable Long id) {
        return carritoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Añadir un ítem al carrito
    @PostMapping
    public ResponseEntity<Carrito> save(@Valid @RequestBody Carrito carrito) {
        Carrito creado = carritoRepository.save(carrito);
        return ResponseEntity.status(201).body(creado);
    }

    // PUT: Actualizar un ítem existente
    @PutMapping("/{id}")
    public ResponseEntity<Carrito> update(@PathVariable Long id,
                                          @Valid @RequestBody Carrito carritoActualizado) {
        return carritoRepository.findById(id)
                .map(existing -> {
                    // Aquí cambiamos productoId en lugar de la entidad Producto
                    existing.setProductoId(carritoActualizado.getProductoId());
                    existing.setCantidad(carritoActualizado.getCantidad());
                    Carrito actualizado = carritoRepository.save(existing);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: Eliminar un ítem del carrito por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!carritoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        carritoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
