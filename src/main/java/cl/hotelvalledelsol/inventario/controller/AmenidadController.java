package cl.hotelvalledelsol.inventario.controller;

import cl.hotelvalledelsol.inventario.model.Amenidad;
import cl.hotelvalledelsol.inventario.service.AmenidadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/amenidades")
public class AmenidadController {

    private final AmenidadService service;

    public AmenidadController(AmenidadService service) {
        this.service = service;
    }

    @GetMapping
    public List<Amenidad> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Amenidad> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                      .map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Amenidad crear(@RequestBody Amenidad nueva) {
        return service.crear(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Amenidad> actualizar(@PathVariable Long id, @RequestBody Amenidad datos) {
        return service.obtenerPorId(id)
                      .map(a -> ResponseEntity.ok(service.actualizar(id, datos)))
                      .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.obtenerPorId(id).isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
