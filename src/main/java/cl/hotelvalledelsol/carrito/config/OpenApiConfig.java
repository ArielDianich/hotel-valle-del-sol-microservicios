package cl.hotelvalledelsol.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Hotel Valle del Sol API", version = "v1",
      description = "Documentación de los microservicios de carrito, inventario y usuario"))
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("hotel-api")
                .pathsToMatch(
                    "/api/**",
                    "/clientes/**",
                    "/reservas/**",
                    "/tipos-habitacion/**",
                    "/habitaciones/**",
                    "/amenidades/**"
                )
                .build();
    }
}
