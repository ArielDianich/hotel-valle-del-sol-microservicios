package cl.hotelvalledelsol.carrito.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carritos_reservas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CarritoReservas", description = "Entidad que representa la asociación entre un cliente y una reserva en el carrito")
public class CarritoReservas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del carrito", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull(message = "El campo clienteId es obligatorio")
    @Schema(description = "ID del cliente asociado", example = "42", required = true)
    private Long clienteId;

    @NotNull(message = "El campo reservaId es obligatorio")
    @Schema(description = "ID de la reserva asociada", example = "123", required = true)
    private Long reservaId;
}
