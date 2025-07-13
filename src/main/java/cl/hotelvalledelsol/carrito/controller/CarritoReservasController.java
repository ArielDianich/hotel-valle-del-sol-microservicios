package cl.hotelvalledelsol.carrito.controller;

import cl.hotelvalledelsol.carrito.model.CarritoReservas;
import cl.hotelvalledelsol.carrito.service.CarritoReservasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carritos")
@Tag(name = "Carrito de Reservas", description = "Operaciones sobre el carrito de reservas")
public class CarritoReservasController {

    private final CarritoReservasService service;

    public CarritoReservasController(CarritoReservasService service) {
        this.service = service;
    }

    @Operation(summary = "Listar todos los carritos", description = "Devuelve todos los carritos de reserva")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public List<CarritoReservas> listarTodos() {
        return service.listarTodos();
    }

    @Operation(summary = "Obtener carrito por ID", description = "Devuelve un carrito de reservas por su identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Carrito encontrado"),
        @ApiResponse(responseCode = "404", description = "Carrito no encontrado")
    })
    @GetMapping("/{id}")
    public CarritoReservas obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                      .orElseThrow(() -> new ResourceNotFoundException("Carrito no encontrado: " + id));
    }

    @Operation(summary = "Crear carrito de reservas", description = "Crea un nuevo carrito de reservas con los datos proporcionados")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Carrito creado"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarritoReservas crear(@RequestBody @Valid CarritoReservas c) {
        return service.crear(c);
    }

    @Operation(summary = "Actualizar carrito", description = "Actualiza los datos de un carrito existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Carrito actualizado"),
        @ApiResponse(responseCode = "404", description = "Carrito no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    @PutMapping("/{id}")
    public CarritoReservas actualizar(
            @PathVariable Long id,
            @RequestBody @Valid CarritoReservas c
    ) {
        return service.actualizar(id, c);
    }

    @Operation(summary = "Eliminar carrito", description = "Elimina un carrito de reservas por su identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Carrito eliminado"),
        @ApiResponse(responseCode = "404", description = "Carrito no encontrado")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    // Excepción para recurso no encontrado
    @ResponseStatus(HttpStatus.NOT_FOUND)
    static class ResourceNotFoundException extends RuntimeException {
        ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
