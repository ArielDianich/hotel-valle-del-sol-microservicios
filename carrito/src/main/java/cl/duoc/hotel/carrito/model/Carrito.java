package cl.duoc.hotel.carrito.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    private String producto;

    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private int cantidad;
}
