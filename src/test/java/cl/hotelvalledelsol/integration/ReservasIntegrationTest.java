package cl.hotelvalledelsol.integration;

import cl.hotelvalledelsol.carrito.model.Reserva;
import cl.hotelvalledelsol.carrito.repository.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
public class ReservasIntegrationTest {

  @Container
  static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.33")
    .withDatabaseName("testdb")
    .withUsername("test")
    .withPassword("test");

  @DynamicPropertySource
  static void registerDatasource(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", mysql::getJdbcUrl);
    registry.add("spring.datasource.username", mysql::getUsername);
    registry.add("spring.datasource.password", mysql::getPassword);
  }

  @Autowired
  private ReservaRepository reservaRepo;

  @Test
  void whenSaveReserva_thenFindsById() {
    Reserva r = new Reserva();
    r.setClienteId(1L);
    r.setHabitacionId(2L);
    r.setFechaInicio(LocalDate.parse("2025-08-01"));
    r.setFechaFin(LocalDate.parse("2025-08-05"));
    Reserva saved = reservaRepo.save(r);

    assertThat(reservaRepo.findById(saved.getId()))
      .isPresent()
      .get()
      .extracting(Reserva::getClienteId)
      .isEqualTo(1L);
  }
}

