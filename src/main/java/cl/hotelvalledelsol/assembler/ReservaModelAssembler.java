package cl.hotelvalledelsol.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import cl.hotelvalledelsol.carrito.controller.ReservaController;
import cl.hotelvalledelsol.carrito.model.Reserva;

public class ReservaModelAssembler extends RepresentationModelAssemblerSupport<Reserva, EntityModel<Reserva>> {
    public ReservaModelAssembler() {
        super(ReservaController.class, (Class<EntityModel<Reserva>>)(Class<?>) EntityModel.class);
    }

    @Override
    public EntityModel<Reserva> toModel(Reserva entity) {
        return EntityModel.of(entity);
    }
}
