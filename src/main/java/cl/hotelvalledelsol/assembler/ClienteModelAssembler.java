package cl.hotelvalledelsol.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import cl.hotelvalledelsol.usuario.controller.ClienteController;
import cl.hotelvalledelsol.usuario.model.Cliente;

public class ClienteModelAssembler extends RepresentationModelAssemblerSupport<Cliente, EntityModel<Cliente>> {
    public ClienteModelAssembler() {
        super(ClienteController.class, (Class<EntityModel<Cliente>>)(Class<?>) EntityModel.class);
    }

    @Override
    public EntityModel<Cliente> toModel(Cliente entity) {
        return EntityModel.of(entity);
    }
}