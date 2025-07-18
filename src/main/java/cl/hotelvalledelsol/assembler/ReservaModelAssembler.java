package cl.hotelvalledelsol.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import cl.hotelvalledelsol.carrito.controller.ReservaController;
import cl.hotelvalledelsol.carrito.model.Reserva;

@Component
public class ReservaModelAssembler
    extends RepresentationModelAssemblerSupport<Reserva, EntityModel<Reserva>> {

    @SuppressWarnings("unchecked")
    public ReservaModelAssembler() {
        super(
            ReservaController.class,
            (Class<EntityModel<Reserva>>) (Class<?>) EntityModel.class
        );
    }

    @Override
    public EntityModel<Reserva> toModel(Reserva entity) {
        return EntityModel.of(entity);
    }
}
