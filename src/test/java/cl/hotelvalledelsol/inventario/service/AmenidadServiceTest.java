package cl.hotelvalledelsol.inventario.service;

import cl.hotelvalledelsol.inventario.model.Amenidad;
import cl.hotelvalledelsol.inventario.repository.AmenidadRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AmenidadServiceTest {

    private AmenidadRepository repo;
    private AmenidadService service;
    private Faker faker;

    @BeforeEach
    void setUp() {
        repo = mock(AmenidadRepository.class);
        service = new AmenidadService(repo);
        faker = new Faker();
    }

    @Test
    void crearAmenidad_guardaYDevuelve() {
        Amenidad a = new Amenidad();
        a.setNombre(faker.options().option("DesayunoIncluido","Sauna"));

        when(repo.save(any(Amenidad.class))).thenAnswer(inv -> inv.getArgument(0));

        Amenidad creado = service.crear(a);

        ArgumentCaptor<Amenidad> captor = ArgumentCaptor.forClass(Amenidad.class);
        verify(repo).save(captor.capture());
        Amenidad guardado = captor.getValue();

        assertThat(guardado.getNombre()).isEqualTo(a.getNombre());
        assertThat(creado).isSameAs(guardado);
    }

    @Test
    void listarTodas_retornaLista() {
        service.listarTodas();
        verify(repo).findAll();
    }

    @Test
    void obtenerPorId_existe_retornaOptional() {
        Amenidad a = new Amenidad();
        a.setId(1L);
        when(repo.findById(1L)).thenReturn(Optional.of(a));

        Optional<Amenidad> found = service.obtenerPorId(1L);

        assertThat(found).isPresent().contains(a);
        verify(repo).findById(1L);
    }

    @Test
    void eliminarAmenidad_llamaADelete() {
        service.eliminar(3L);
        verify(repo).deleteById(3L);
    }
}
