package cl.hotelvalledelsol.carrito.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Data;

@Entity
@Table(name = "reservas")
@Data
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clienteId;    // referencia a Cliente
    private Long habitacionId; // referencia a Habitacion

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
