package cl.duoc.hotel.inventario.model;

import java.math.BigDecimal;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max = 100)
    @Column(nullable = false, unique = true)
    private String nombre;

    @Min(0)
    @Column(nullable = false)
    private Integer stock;

    @DecimalMin("0.0") @Digits(integer=8, fraction=2)
    @Column(nullable = false)
    private BigDecimal precio;
}
