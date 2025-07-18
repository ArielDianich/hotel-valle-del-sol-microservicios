package cl.hotelvalledelsol.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import cl.hotelvalledelsol.inventario.controller.TipoHabitacionController;
import cl.hotelvalledelsol.inventario.model.TipoHabitacion;

@Component
public class TipoHabitacionModelAssembler
    extends RepresentationModelAssemblerSupport<TipoHabitacion, EntityModel<TipoHabitacion>> {

    @SuppressWarnings("unchecked")
    public TipoHabitacionModelAssembler() {
        super(
            TipoHabitacionController.class,
            (Class<EntityModel<TipoHabitacion>>) (Class<?>) EntityModel.class
        );
    }

    @Override
    public EntityModel<TipoHabitacion> toModel(TipoHabitacion entity) {
        return EntityModel.of(entity);
    }
}
