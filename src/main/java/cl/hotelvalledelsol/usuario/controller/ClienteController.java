package cl.hotelvalledelsol.usuario.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.hotelvalledelsol.usuario.model.Cliente;
import cl.hotelvalledelsol.usuario.service.ClienteService;
import cl.hotelvalledelsol.assembler.ClienteModelAssembler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "Operaciones sobre los clientes")
public class ClienteController {

    private final ClienteService service;
    private final ClienteModelAssembler assembler;

    public ClienteController(ClienteService service,
                             ClienteModelAssembler assembler) {
        this.service   = service;
        this.assembler = assembler;
    }

    @Operation(summary = "Listar todos los clientes", description = "Devuelve todos los clientes registrados")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Cliente>>> listarTodos() {
        List<Cliente> lista = service.listarTodos();
        CollectionModel<EntityModel<Cliente>> recursos =
            assembler.toCollectionModel(lista);
        return ResponseEntity.ok(recursos);
    }

    @Operation(summary = "Obtener cliente por ID", description = "Busca y devuelve un cliente según su identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Cliente>> obtenerPorId(@PathVariable Long id) {
        Cliente c = service.obtenerPorId(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado: " + id));
        return ResponseEntity.ok(assembler.toModel(c));
    }

    @Operation(summary = "Crear nuevo cliente", description = "Registra un nuevo cliente con los datos proporcionados")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Cliente creado"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    @PostMapping
    public ResponseEntity<EntityModel<Cliente>> crear(@RequestBody @Valid Cliente nuevo) {
        Cliente creado = service.crear(nuevo);
        EntityModel<Cliente> resource = assembler.toModel(creado);
        return ResponseEntity
                .created(resource.getRequiredLink("self").toUri())
                .body(resource);
    }

    @Operation(summary = "Actualizar cliente", description = "Modifica un cliente existente por su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cliente actualizado"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Cliente>> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid Cliente datos) {
        Cliente actualizado = service.actualizar(id, datos);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @Operation(summary = "Eliminar cliente", description = "Borra un cliente por su identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Cliente eliminado"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
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
