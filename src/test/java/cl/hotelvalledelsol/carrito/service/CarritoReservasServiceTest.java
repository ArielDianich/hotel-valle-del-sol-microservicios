package cl.hotelvalledelsol.carrito.service;

import cl.hotelvalledelsol.carrito.model.CarritoReservas;
import cl.hotelvalledelsol.carrito.repository.CarritoReservasRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarritoReservasServiceTest {

    @Mock
    private CarritoReservasRepository repo;

    @InjectMocks
    private CarritoReservasService service;

    private Faker faker;

    @BeforeEach
    void setUp() {
        faker = new Faker();
    }

    @Test
    void crearCarritoReservas_guardaYDevuelve() {
        // Dado
        CarritoReservas input = new CarritoReservas();
        input.setClienteId(faker.number().numberBetween(1L, 10L));
        input.setReservaId(faker.number().numberBetween(1L, 10L));
        when(repo.save(any(CarritoReservas.class))).thenAnswer(inv -> inv.getArgument(0));

        // Cuando
        CarritoReservas result = service.crear(input);

        // Entonces
        ArgumentCaptor<CarritoReservas> captor = ArgumentCaptor.forClass(CarritoReservas.class);
        verify(repo).save(captor.capture());
        CarritoReservas saved = captor.getValue();
        assertThat(saved.getClienteId()).isEqualTo(input.getClienteId());
        assertThat(saved.getReservaId()).isEqualTo(input.getReservaId());
        assertThat(result).isSameAs(saved);
    }

    @Test
    void listarTodos_retornaLista() {
        service.listarTodos();
        verify(repo).findAll();
    }

    @Test
    void obtenerPorId_existe_retornaOptional() {
        // Dado
        CarritoReservas c = new CarritoReservas();
        c.setId(1L);
        when(repo.findById(1L)).thenReturn(Optional.of(c));

        // Cuando
        Optional<CarritoReservas> found = service.obtenerPorId(1L);

        // Entonces
        assertThat(found).isPresent().contains(c);
        verify(repo).findById(1L);
    }

    @Test
    void obtenerPorId_noExiste_retornaEmpty() {
        when(repo.findById(42L)).thenReturn(Optional.empty());

        Optional<CarritoReservas> found = service.obtenerPorId(42L);

        assertThat(found).isNotPresent();
        verify(repo).findById(42L);
    }

    @Test
    void eliminarCarritoReservas_llamaADelete() {
        service.eliminar(5L);
        verify(repo).deleteById(5L);
    }
}
