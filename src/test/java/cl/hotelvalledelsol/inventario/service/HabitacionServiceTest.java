package cl.hotelvalledelsol.inventario.service;

import cl.hotelvalledelsol.inventario.model.Habitacion;
import cl.hotelvalledelsol.inventario.repository.HabitacionRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class HabitacionServiceTest {

    private HabitacionRepository repo;
    private HabitacionService service;
    private Faker faker;

    @BeforeEach
    void setUp() {
        repo = mock(HabitacionRepository.class);
        service = new HabitacionService(repo);
        faker = new Faker();
    }

    @Test
    void crearHabitacion_guardaYDevuelve() {
        Habitacion h = new Habitacion();
        h.setNumero(faker.number().digits(3));
        h.setPiso(faker.number().digit());
        h.setPrecioBase(faker.number().randomDouble(2, 50, 200));

        when(repo.save(any(Habitacion.class))).thenAnswer(inv -> inv.getArgument(0));

        Habitacion creado = service.crear(h);

        ArgumentCaptor<Habitacion> captor = ArgumentCaptor.forClass(Habitacion.class);
        verify(repo).save(captor.capture());
        Habitacion guardado = captor.getValue();

        assertThat(guardado.getNumero()).isEqualTo(h.getNumero());
        assertThat(creado).isSameAs(guardado);
    }

    @Test
    void listarTodas_retornaLista() {
        service.listarTodas();
        verify(repo).findAll();
    }

    @Test
    void obtenerPorId_existe_retornaOptional() {
        Habitacion h = new Habitacion();
        h.setId(1L);
        when(repo.findById(1L)).thenReturn(Optional.of(h));

        Optional<Habitacion> found = service.obtenerPorId(1L);

        assertThat(found).isPresent().contains(h);
        verify(repo).findById(1L);
    }

    @Test
    void eliminarHabitacion_llamaADelete() {
        service.eliminar(10L);
        verify(repo).deleteById(10L);
    }
}
