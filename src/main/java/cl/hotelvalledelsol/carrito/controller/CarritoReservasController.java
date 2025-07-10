package cl.hotelvalledelsol.carrito.controller;

import cl.hotelvalledelsol.carrito.model.CarritoReservas;
import cl.hotelvalledelsol.carrito.service.CarritoReservasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carritos-reservas")
public class CarritoReservasController {

    private final CarritoReservasService service;

    public CarritoReservasController(CarritoReservasService service) {
        this.service = service;
    }

    @GetMapping
    public List<CarritoReservas> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarritoReservas> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                      .map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CarritoReservas crear(@RequestBody CarritoReservas nuevo) {
        return service.crear(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarritoReservas> actualizar(@PathVariable Long id, @RequestBody CarritoReservas datos) {
        return service.obtenerPorId(id)
                      .map(c -> ResponseEntity.ok(service.actualizar(id, datos)))
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
