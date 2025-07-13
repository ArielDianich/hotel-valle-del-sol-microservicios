package cl.hotelvalledelsol.inventario.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.hotelvalledelsol.inventario.model.Habitacion;
import cl.hotelvalledelsol.inventario.service.HabitacionService;
import cl.hotelvalledelsol.assembler.HabitacionModelAssembler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/habitaciones")
@Tag(name = "Habitaciones", description = "Operaciones sobre las habitaciones")
public class HabitacionController {

    private final HabitacionService service;
    private final HabitacionModelAssembler assembler;

    public HabitacionController(HabitacionService service,
                                HabitacionModelAssembler assembler) {
        this.service   = service;
        this.assembler = assembler;
    }

    @Operation(summary = "Listar todas las habitaciones", description = "Devuelve todas las habitaciones disponibles")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Habitacion>>> listarTodas() {
        List<Habitacion> lista = service.listarTodas();
        CollectionModel<EntityModel<Habitacion>> recursos =
            assembler.toCollectionModel(lista);
        return ResponseEntity.ok(recursos);
    }

    @Operation(summary = "Obtener habitación por ID", description = "Busca y devuelve una habitación según su identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Habitación encontrada"),
        @ApiResponse(responseCode = "404", description = "Habitación no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Habitacion>> obtenerPorId(@PathVariable Long id) {
        Habitacion h = service.obtenerPorId(id)
            .orElseThrow(() -> new ResourceNotFoundException("Habitación no encontrada: " + id));
        return ResponseEntity.ok(assembler.toModel(h));
    }

    @Operation(summary = "Crear nueva habitación", description = "Registra una nueva habitación con los datos proporcionados")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Habitación creada"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    @PostMapping
    public ResponseEntity<EntityModel<Habitacion>> crear(@RequestBody @Valid Habitacion nueva) {
        Habitacion creada = service.crear(nueva);
        EntityModel<Habitacion> resource = assembler.toModel(creada);
        return ResponseEntity
                .created(resource.getRequiredLink("self").toUri())
                .body(resource);
    }

    @Operation(summary = "Actualizar habitación", description = "Modifica una habitación existente por su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Habitación actualizada"),
        @ApiResponse(responseCode = "404", description = "Habitación no encontrada"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Habitacion>> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid Habitacion datos) {
        Habitacion actualizada = service.actualizar(id, datos);
        return ResponseEntity.ok(assembler.toModel(actualizada));
    }

    @Operation(summary = "Eliminar habitación", description = "Borra una habitación por su identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Habitación eliminada"),
        @ApiResponse(responseCode = "404", description = "Habitación no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(code = org.springframework.http.HttpStatus.NOT_FOUND)
    static class ResourceNotFoundException extends RuntimeException {
        ResourceNotFoundException(String msg) {
            super(msg);
        }
    }
}
