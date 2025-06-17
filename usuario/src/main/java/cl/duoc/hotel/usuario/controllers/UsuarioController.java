package cl.duoc.hotel.usuario.controllers;

import cl.duoc.hotel.usuario.model.Usuario;
import cl.duoc.hotel.usuario.repository.UsuarioRepository;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @PostMapping
     public Usuario save(@Valid @RequestBody Usuario usuario) {
    return usuarioRepository.save(usuario);
    }

    
    @DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (usuarioRepository.existsById(id)) {
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    } else {
        return ResponseEntity.notFound().build(); // 404 Not Found
    }
}

}


