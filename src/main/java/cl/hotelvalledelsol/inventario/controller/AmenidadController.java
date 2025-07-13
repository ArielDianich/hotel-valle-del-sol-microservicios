package cl.hotelvalledelsol.inventario.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.hotelvalledelsol.inventario.model.Amenidad;
import cl.hotelvalledelsol.inventario.service.AmenidadService;
import cl.hotelvalledelsol.assembler.AmenidadModelAssembler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/amenidades")
@Tag(name = "Amenidades", description = "Operaciones sobre amenidades")
public class AmenidadController {

    private final AmenidadService service;
    private final AmenidadModelAssembler assembler;

    public AmenidadController(AmenidadService service,
                              AmenidadModelAssembler assembler) {
        this.service   = service;
        this.assembler = assembler;
    }

    @Operation(summary = "Listar todas las amenidades", description = "Devuelve todas las amenidades disponibles")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Amenidad>>> listarTodas() {
        List<Amenidad> lista = service.listarTodas();
        CollectionModel<EntityModel<Amenidad>> recursos =
            assembler.toCollectionModel(lista);
        return ResponseEntity.ok(recursos);
    }

    @Operation(summary = "Obtener amenidad por ID", description = "Busca y devuelve una amenidad según su identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Amenidad encontrada"),
        @ApiResponse(responseCode = "404", description = "Amenidad no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Amenidad>> obtenerPorId(@PathVariable Long id) {
        Amenidad a = service.obtenerPorId(id)
            .orElseThrow(() -> new ResourceNotFoundException("Amenidad no encontrada: " + id));
        return ResponseEntity.ok(assembler.toModel(a));
    }

    @Operation(summary = "Crear nueva amenidad", description = "Registra una nueva amenidad con los datos proporcionados")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Amenidad creada"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    @PostMapping
    public ResponseEntity<EntityModel<Amenidad>> crear(@RequestBody @Valid Amenidad nueva) {
        Amenidad creada = service.crear(nueva);
        EntityModel<Amenidad> resource = assembler.toModel(creada);
        return ResponseEntity
                .created(resource.getRequiredLink("self").toUri())
                .body(resource);
    }

    @Operation(summary = "Actualizar amenidad", description = "Modifica una amenidad existente por su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Amenidad actualizada"),
        @ApiResponse(responseCode = "404", description = "Amenidad no encontrada"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Amenidad>> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid Amenidad datos) {
        Amenidad actualizada = service.actualizar(id, datos);
        return ResponseEntity.ok(assembler.toModel(actualizada));
    }

    @Operation(summary = "Eliminar amenidad", description = "Borra una amenidad por su identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Amenidad eliminada"),
        @ApiResponse(responseCode = "404", description = "Amenidad no encontrada")
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
