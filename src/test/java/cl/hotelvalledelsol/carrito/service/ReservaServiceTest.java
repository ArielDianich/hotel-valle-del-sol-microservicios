package cl.hotelvalledelsol.carrito.service;

import cl.hotelvalledelsol.carrito.model.Reserva;
import cl.hotelvalledelsol.carrito.repository.ReservaRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ReservaServiceTest {

    private ReservaRepository repo;
    private ReservaService service;
    private Faker faker;

    @BeforeEach
    void setUp() {
        repo = mock(ReservaRepository.class);
        service = new ReservaService(repo);
        faker = new Faker();
    }

    @Test
    void crearReserva_guardaYDevuelve() {
        Reserva r = new Reserva();
        r.setClienteId(1L);
        r.setHabitacionId(2L);
        r.setFechaInicio(LocalDate.now().plusDays(faker.number().numberBetween(1,10)));
        r.setFechaFin(r.getFechaInicio().plusDays(faker.number().numberBetween(1,5)));

        when(repo.save(any(Reserva.class))).thenAnswer(inv -> inv.getArgument(0));

        Reserva creado = service.crear(r);

        ArgumentCaptor<Reserva> captor = ArgumentCaptor.forClass(Reserva.class);
        verify(repo).save(captor.capture());
        Reserva guardado = captor.getValue();

        assertThat(guardado.getClienteId()).isEqualTo(r.getClienteId());
        assertThat(creado).isSameAs(guardado);
    }

    @Test
    void listarTodas_retornaLista() {
        service.listarTodas();
        verify(repo).findAll();
    }

    @Test
    void obtenerPorId_existe_retornaOptional() {
        Reserva r = new Reserva();
        r.setId(1L);
        when(repo.findById(1L)).thenReturn(Optional.of(r));

        Optional<Reserva> found = service.obtenerPorId(1L);

        assertThat(found).isPresent().contains(r);
        verify(repo).findById(1L);
    }

    @Test
    void eliminarReserva_llamaADelete() {
        service.eliminar(9L);
        verify(repo).deleteById(9L);
    }
}
