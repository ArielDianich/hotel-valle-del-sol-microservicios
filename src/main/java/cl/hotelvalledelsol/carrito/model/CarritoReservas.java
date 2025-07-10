package cl.hotelvalledelsol.carrito.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "carritos_reservas")
@Data
public class CarritoReservas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clienteId;     // referencia a Cliente
    private Long reservaId;     // referencia a Reserva
}
