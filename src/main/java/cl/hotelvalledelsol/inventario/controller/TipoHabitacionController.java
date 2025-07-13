package cl.hotelvalledelsol.inventario.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.hotelvalledelsol.inventario.model.TipoHabitacion;
import cl.hotelvalledelsol.inventario.service.TipoHabitacionService;
import cl.hotelvalledelsol.assembler.TipoHabitacionModelAssembler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tipos-habitacion")
@Tag(name = "Tipos de Habitación", description = "Operaciones sobre los tipos de habitación")
public class TipoHabitacionController {

    private final TipoHabitacionService service;
    private final TipoHabitacionModelAssembler assembler;

    public TipoHabitacionController(TipoHabitacionService service,
                                    TipoHabitacionModelAssembler assembler) {
        this.service   = service;
        this.assembler = assembler;
    }

    @Operation(summary = "Listar todos los tipos de habitación",
               description = "Devuelve todos los tipos de habitación disponibles")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<TipoHabitacion>>> listarTodos() {
        List<TipoHabitacion> lista = service.listarTodos();
        CollectionModel<EntityModel<TipoHabitacion>> recursos =
            assembler.toCollectionModel(lista);
        return ResponseEntity.ok(recursos);
    }

    @Operation(summary = "Obtener tipo de habitación por ID",
               description = "Busca y devuelve un tipo de habitación según su identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tipo de habitación encontrado"),
        @ApiResponse(responseCode = "404", description = "Tipo de habitación no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<TipoHabitacion>> obtenerPorId(@PathVariable Long id) {
        TipoHabitacion t = service.obtenerPorId(id)
            .orElseThrow(() -> new ResourceNotFoundException("TipoHabitacion no encontrado: " + id));
        return ResponseEntity.ok(assembler.toModel(t));
    }

    @Operation(summary = "Crear nuevo tipo de habitación",
               description = "Registra un nuevo tipo de habitación con los datos proporcionados")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Tipo de habitación creado"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    @PostMapping
    public ResponseEntity<EntityModel<TipoHabitacion>> crear(
            @RequestBody @Valid TipoHabitacion nuevo) {
        TipoHabitacion creado = service.crear(nuevo);
        EntityModel<TipoHabitacion> resource = assembler.toModel(creado);
        return ResponseEntity
                .created(resource.getRequiredLink("self").toUri())
                .body(resource);
    }

    @Operation(summary = "Actualizar tipo de habitación",
               description = "Modifica un tipo de habitación existente por su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tipo de habitación actualizado"),
        @ApiResponse(responseCode = "404", description = "Tipo de habitación no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<TipoHabitacion>> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid TipoHabitacion datos) {
        TipoHabitacion actualizado = service.actualizar(id, datos);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @Operation(summary = "Eliminar tipo de habitación",
               description = "Borra un tipo de habitación por su identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Tipo de habitación eliminado"),
        @ApiResponse(responseCode = "404", description = "Tipo de habitación no encontrado")
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
