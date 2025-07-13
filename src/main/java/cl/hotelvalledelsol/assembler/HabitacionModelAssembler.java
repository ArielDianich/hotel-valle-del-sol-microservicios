package cl.hotelvalledelsol.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import cl.hotelvalledelsol.inventario.controller.HabitacionController;
import cl.hotelvalledelsol.inventario.model.Habitacion;

public class HabitacionModelAssembler extends RepresentationModelAssemblerSupport<Habitacion, EntityModel<Habitacion>> {
    public HabitacionModelAssembler() {
        super(HabitacionController.class, (Class<EntityModel<Habitacion>>)(Class<?>) EntityModel.class);
    }

    @Override
    public EntityModel<Habitacion> toModel(Habitacion entity) {
        return EntityModel.of(entity);
    }
}