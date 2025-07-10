package cl.hotelvalledelsol.usuario.service;

import cl.hotelvalledelsol.usuario.model.Cliente;
import cl.hotelvalledelsol.usuario.repository.ClienteRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    private ClienteRepository repo;
    private ClienteService service;
    private Faker faker;

    @BeforeEach
    void setUp() {
        repo = mock(ClienteRepository.class);
        service = new ClienteService(repo);
        faker = new Faker();
    }

    @Test
    void crearCliente_guardaYDevuelve() {
        Cliente c = new Cliente();
        c.setNombre(faker.name().firstName());
        c.setApellido(faker.name().lastName());
        c.setEmail(faker.internet().emailAddress());
        c.setTelefono(faker.phoneNumber().cellPhone());

        when(repo.save(any(Cliente.class))).thenAnswer(inv -> inv.getArgument(0));

        Cliente creado = service.crear(c);

        ArgumentCaptor<Cliente> captor = ArgumentCaptor.forClass(Cliente.class);
        verify(repo).save(captor.capture());
        Cliente guardado = captor.getValue();

        assertThat(guardado.getNombre()).isEqualTo(c.getNombre());
        assertThat(creado).isSameAs(guardado);
    }

    @Test
    void listarTodos_retornaLista() {
        service.listarTodos();
        verify(repo).findAll();
    }

    @Test
    void obtenerPorId_existe_retornaOptional() {
        Cliente c = new Cliente();
        c.setId(1L);
        when(repo.findById(1L)).thenReturn(Optional.of(c));

        Optional<Cliente> found = service.obtenerPorId(1L);

        assertThat(found).isPresent().contains(c);
        verify(repo).findById(1L);
    }

    @Test
    void eliminarCliente_llamaADelete() {
        service.eliminar(5L);
        verify(repo).deleteById(5L);
    }
}
