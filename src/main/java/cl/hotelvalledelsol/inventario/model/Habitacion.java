package cl.hotelvalledelsol.inventario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "habitaciones")
@Data
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;     // e.g. “101”
    private String piso;       // e.g. “1”
    private Double precioBase; // tarifa base por noche

    // relación a TipoHabitación se añadirá después
}
