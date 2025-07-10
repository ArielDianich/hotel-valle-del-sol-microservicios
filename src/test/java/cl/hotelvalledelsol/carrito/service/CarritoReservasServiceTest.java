package cl.hotelvalledelsol.carrito.service;

import cl.hotelvalledelsol.carrito.model.CarritoReservas;
import cl.hotelvalledelsol.carrito.repository.CarritoReservasRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CarritoReservasServiceTest {

    private CarritoReservasRepository repo;
    private CarritoReservasService service;
    private Faker faker;

    @BeforeEach
    void setUp() {
        repo = mock(CarritoReservasRepository.class);
        service = new CarritoReservasService(repo);
        faker = new Faker();
    }

    @Test
    void crearCarritoReservas_guardaYDevuelve() {
        CarritoReservas c = new CarritoReservas();
        c.setClienteId(faker.number().numberBetween(1L, 10L));
        c.setReservaId(faker.number().numberBetween(1L, 10L));

        when(repo.save(any(CarritoReservas.class))).thenAnswer(inv -> inv.getArgument(0));

        CarritoReservas creado = service.crear(c);

        ArgumentCaptor<CarritoReservas> captor = ArgumentCaptor.forClass(CarritoReservas.class);
        verify(repo).save(captor.capture());
        CarritoReservas guardado = captor.getValue();

        assertThat(guardado.getClienteId()).isEqualTo(c.getClienteId());
        assertThat(creado).isSameAs(guardado);
    }

    @Test
    void listarTodos_retornaLista() {
        service.listarTodos();
        verify(repo).findAll();
    }

    @Test
    void obtenerPorId_existe_retornaOptional() {
        CarritoReservas c = new CarritoReservas();
        c.setId(1L);
        when(repo.findById(1L)).thenReturn(Optional.of(c));

        Optional<CarritoReservas> found = service.obtenerPorId(1L);

        assertThat(found).isPresent().contains(c);
        verify(repo).findById(1L);
    }

    @Test
    void eliminarCarritoReservas_llamaADelete() {
        service.eliminar(5L);
        verify(repo).deleteById(5L);
    }
}
