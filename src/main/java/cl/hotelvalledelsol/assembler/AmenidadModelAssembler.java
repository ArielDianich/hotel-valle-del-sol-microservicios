package cl.hotelvalledelsol.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import cl.hotelvalledelsol.inventario.controller.AmenidadController;
import cl.hotelvalledelsol.inventario.model.Amenidad;

@Component
public class AmenidadModelAssembler
        extends RepresentationModelAssemblerSupport<Amenidad, EntityModel<Amenidad>> {

    public AmenidadModelAssembler() {
        super(
            AmenidadController.class,
            (Class<EntityModel<Amenidad>>) (Class<?>) EntityModel.class
        );
    }

    @Override
    public EntityModel<Amenidad> toModel(Amenidad entity) {
        return EntityModel.of(entity);
    }
}
