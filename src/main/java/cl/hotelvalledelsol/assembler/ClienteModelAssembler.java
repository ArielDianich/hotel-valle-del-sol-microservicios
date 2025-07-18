package cl.hotelvalledelsol.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import cl.hotelvalledelsol.usuario.controller.ClienteController;
import cl.hotelvalledelsol.usuario.model.Cliente;

@Component
public class ClienteModelAssembler
    extends RepresentationModelAssemblerSupport<Cliente, EntityModel<Cliente>> {

    @SuppressWarnings("unchecked")
    public ClienteModelAssembler() {
        super(
            ClienteController.class,
            (Class<EntityModel<Cliente>>) (Class<?>) EntityModel.class
        );
    }

    @Override
    public EntityModel<Cliente> toModel(Cliente entity) {
        return EntityModel.of(entity);
    }
}
