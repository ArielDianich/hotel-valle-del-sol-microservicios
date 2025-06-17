package cl.duoc.hotel.usuario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(min = 2, max = 50)
    @Column(nullable = false)
    private String nombre;

    @NotBlank @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank @Size(min = 9, max = 15)
    @Column(nullable = false)
    private String telefono;
}
