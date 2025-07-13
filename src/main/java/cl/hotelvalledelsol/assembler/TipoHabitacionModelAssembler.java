package cl.hotelvalledelsol.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import cl.hotelvalledelsol.inventario.controller.TipoHabitacionController;
import cl.hotelvalledelsol.inventario.model.TipoHabitacion;

public class TipoHabitacionModelAssembler extends RepresentationModelAssemblerSupport<TipoHabitacion, EntityModel<TipoHabitacion>> {
    public TipoHabitacionModelAssembler() {
        super(TipoHabitacionController.class, (Class<EntityModel<TipoHabitacion>>)(Class<?>) EntityModel.class);
    }

    @Override
    public EntityModel<TipoHabitacion> toModel(TipoHabitacion entity) {
        return EntityModel.of(entity);
    }
}
