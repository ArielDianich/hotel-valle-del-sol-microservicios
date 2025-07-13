package cl.hotelvalledelsol.carrito.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.hotelvalledelsol.carrito.model.CarritoReservas;
import cl.hotelvalledelsol.carrito.service.CarritoReservasService;
import cl.hotelvalledelsol.assembler.CarritoReservasModelAssembler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/carritos")
@Tag(name = "Carrito de Reservas", description = "Operaciones sobre el carrito de reservas")
public class CarritoReservasController {

    private final CarritoReservasService service;
    private final CarritoReservasModelAssembler assembler;

    public CarritoReservasController(CarritoReservasService service,
                                     CarritoReservasModelAssembler assembler) {
        this.service   = service;
        this.assembler = assembler;
    }

    @Operation(summary = "Listar todos los carritos", description = "Devuelve todos los carritos de reserva")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<CarritoReservas>>> listarTodos() {
        List<CarritoReservas> lista = service.listarTodos();
        CollectionModel<EntityModel<CarritoReservas>> recursos =
            assembler.toCollectionModel(lista);
        return ResponseEntity.ok(recursos);
    }

    @Operation(summary = "Obtener carrito por ID", description = "Devuelve un carrito de reservas por su identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Carrito encontrado"),
        @ApiResponse(responseCode = "404", description = "Carrito no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CarritoReservas>> obtenerPorId(@PathVariable Long id) {
        CarritoReservas item = service.obtenerPorId(id)
            .orElseThrow(() -> new ResourceNotFoundException("Carrito no encontrado: " + id));
        return ResponseEntity.ok(assembler.toModel(item));
    }

    @Operation(summary = "Crear carrito de reservas", description = "Crea un nuevo carrito de reservas con los datos proporcionados")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Carrito creado"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    @PostMapping
    public ResponseEntity<EntityModel<CarritoReservas>> crear(
            @RequestBody @Valid CarritoReservas c) {
        CarritoReservas creado = service.crear(c);
        EntityModel<CarritoReservas> resource = assembler.toModel(creado);
        return ResponseEntity
                .created(resource.getRequiredLink("self").toUri())
                .body(resource);
    }

    @Operation(summary = "Actualizar carrito", description = "Actualiza los datos de un carrito existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Carrito actualizado"),
        @ApiResponse(responseCode = "404", description = "Carrito no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<CarritoReservas>> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid CarritoReservas c) {
        CarritoReservas actualizado = service.actualizar(id, c);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @Operation(summary = "Eliminar carrito", description = "Elimina un carrito de reservas por su identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Carrito eliminado"),
        @ApiResponse(responseCode = "404", description = "Carrito no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Excepción para recurso no encontrado
    @ResponseStatus(code = org.springframework.http.HttpStatus.NOT_FOUND)
    static class ResourceNotFoundException extends RuntimeException {
        ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
