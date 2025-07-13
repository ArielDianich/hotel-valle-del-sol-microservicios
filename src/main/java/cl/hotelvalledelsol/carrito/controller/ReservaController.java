package cl.hotelvalledelsol.carrito.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.hotelvalledelsol.carrito.model.Reserva;
import cl.hotelvalledelsol.carrito.service.ReservaService;
import cl.hotelvalledelsol.assembler.ReservaModelAssembler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reservas")
@Tag(name = "Reservas", description = "Operaciones sobre las reservas")
public class ReservaController {

    private final ReservaService service;
    private final ReservaModelAssembler assembler;

    public ReservaController(ReservaService service,
                             ReservaModelAssembler assembler) {
        this.service   = service;
        this.assembler = assembler;
    }

    @Operation(summary = "Listar todas las reservas", description = "Devuelve todas las reservas registradas")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Reserva>>> listarTodas() {
        List<Reserva> lista = service.listarTodas();
        CollectionModel<EntityModel<Reserva>> recursos =
            assembler.toCollectionModel(lista);
        return ResponseEntity.ok(recursos);
    }

    @Operation(summary = "Obtener reserva por ID", description = "Busca y devuelve una reserva según su identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Reserva encontrada"),
        @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Reserva>> obtenerPorId(@PathVariable Long id) {
        Reserva r = service.obtenerPorId(id)
            .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada: " + id));
        return ResponseEntity.ok(assembler.toModel(r));
    }

    @Operation(summary = "Crear nueva reserva", description = "Registra una nueva reserva con los datos proporcionados")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Reserva creada"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    @PostMapping
    public ResponseEntity<EntityModel<Reserva>> crear(@RequestBody @Valid Reserva nueva) {
        Reserva creada = service.crear(nueva);
        EntityModel<Reserva> resource = assembler.toModel(creada);
        return ResponseEntity
                .created(resource.getRequiredLink("self").toUri())
                .body(resource);
    }

    @Operation(summary = "Actualizar reserva", description = "Modifica una reserva existente por su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Reserva actualizada"),
        @ApiResponse(responseCode = "404", description = "Reserva no encontrada"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Reserva>> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid Reserva datos) {
        // lanza ResourceNotFoundException si no existe
        Reserva actualizada = service.actualizar(id, datos);
        return ResponseEntity.ok(assembler.toModel(actualizada));
    }

    @Operation(summary = "Eliminar reserva", description = "Borra una reserva por su identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Reserva eliminada"),
        @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
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
