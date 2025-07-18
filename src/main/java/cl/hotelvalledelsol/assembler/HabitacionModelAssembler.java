package cl.hotelvalledelsol.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import cl.hotelvalledelsol.inventario.controller.HabitacionController;
import cl.hotelvalledelsol.inventario.model.Habitacion;

@Component
public class HabitacionModelAssembler
    extends RepresentationModelAssemblerSupport<Habitacion, EntityModel<Habitacion>> {

    @SuppressWarnings("unchecked")
    public HabitacionModelAssembler() {
        super(
            HabitacionController.class,
            (Class<EntityModel<Habitacion>>) (Class<?>) EntityModel.class
        );
    }

    @Override
    public EntityModel<Habitacion> toModel(Habitacion entity) {
        return EntityModel.of(entity);
    }
}
