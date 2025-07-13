package cl.hotelvalledelsol.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import cl.hotelvalledelsol.carrito.controller.CarritoReservasController;
import cl.hotelvalledelsol.carrito.model.CarritoReservas;

public class CarritoReservasModelAssembler extends RepresentationModelAssemblerSupport<CarritoReservas, EntityModel<CarritoReservas>> {
    public CarritoReservasModelAssembler() {
        super(CarritoReservasController.class, (Class<EntityModel<CarritoReservas>>)(Class<?>) EntityModel.class);
    }

    @Override
    public EntityModel<CarritoReservas> toModel(CarritoReservas entity) {
        return EntityModel.of(entity);
    }
}

