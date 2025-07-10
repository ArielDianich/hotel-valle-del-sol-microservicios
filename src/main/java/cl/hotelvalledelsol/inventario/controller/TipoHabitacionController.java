package cl.hotelvalledelsol.inventario.controller;

import cl.hotelvalledelsol.inventario.model.TipoHabitacion;
import cl.hotelvalledelsol.inventario.service.TipoHabitacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-habitacion")
public class TipoHabitacionController {

    private final TipoHabitacionService service;

    public TipoHabitacionController(TipoHabitacionService service) {
        this.service = service;
    }

    @GetMapping
    public List<TipoHabitacion> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoHabitacion> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                      .map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TipoHabitacion crear(@RequestBody TipoHabitacion nuevo) {
        return service.crear(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoHabitacion> actualizar(@PathVariable Long id, @RequestBody TipoHabitacion datos) {
        return service.obtenerPorId(id)
                      .map(t -> ResponseEntity.ok(service.actualizar(id, datos)))
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
