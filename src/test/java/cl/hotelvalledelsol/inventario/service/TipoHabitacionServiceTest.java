package cl.hotelvalledelsol.inventario.service;

import cl.hotelvalledelsol.inventario.model.TipoHabitacion;
import cl.hotelvalledelsol.inventario.repository.TipoHabitacionRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class TipoHabitacionServiceTest {

    private TipoHabitacionRepository repo;
    private TipoHabitacionService service;
    private Faker faker;

    @BeforeEach
    void setUp() {
        repo = mock(TipoHabitacionRepository.class);
        service = new TipoHabitacionService(repo);
        faker = new Faker();
    }

    @Test
    void crearTipoHabitacion_guardaYDevuelve() {
        TipoHabitacion t = new TipoHabitacion();
        t.setNombre(faker.options().option("Individual","Doble","Suite"));
        t.setDescripcion(faker.lorem().sentence());

        when(repo.save(any(TipoHabitacion.class))).thenAnswer(inv -> inv.getArgument(0));

        TipoHabitacion creado = service.crear(t);

        ArgumentCaptor<TipoHabitacion> captor = ArgumentCaptor.forClass(TipoHabitacion.class);
        verify(repo).save(captor.capture());
        TipoHabitacion guardado = captor.getValue();

        assertThat(guardado.getNombre()).isEqualTo(t.getNombre());
        assertThat(creado).isSameAs(guardado);
    }

    @Test
    void listarTodos_retornaLista() {
        service.listarTodos();
        verify(repo).findAll();
    }

    @Test
    void obtenerPorId_existe_retornaOptional() {
        TipoHabitacion t = new TipoHabitacion();
        t.setId(1L);
        when(repo.findById(1L)).thenReturn(Optional.of(t));

        Optional<TipoHabitacion> found = service.obtenerPorId(1L);

        assertThat(found).isPresent().contains(t);
        verify(repo).findById(1L);
    }

    @Test
    void eliminarTipoHabitacion_llamaADelete() {
        service.eliminar(7L);
        verify(repo).deleteById(7L);
    }
}
