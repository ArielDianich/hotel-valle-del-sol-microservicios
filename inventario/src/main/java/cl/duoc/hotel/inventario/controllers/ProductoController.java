package cl.duoc.hotel.inventario.controllers;
import jakarta.validation.Valid;
import cl.duoc.hotel.inventario.model.Producto;
import cl.duoc.hotel.inventario.repository.ProductoRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getById(@PathVariable Long id) {
    return productoRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

   @PostMapping
    public Producto save(@Valid @RequestBody Producto producto) {
    return productoRepository.save(producto);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(@PathVariable Long id, @Valid @RequestBody Producto productoActualizado) {
    return productoRepository.findById(id)
            .map(producto -> {
                producto.setNombre(productoActualizado.getNombre());
                producto.setStock(productoActualizado.getStock());
                return ResponseEntity.ok(productoRepository.save(producto));
            })
            .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (productoRepository.existsById(id)) {
        productoRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    } else {
        return ResponseEntity.notFound().build(); // 404 Not Found
    }
}

}

