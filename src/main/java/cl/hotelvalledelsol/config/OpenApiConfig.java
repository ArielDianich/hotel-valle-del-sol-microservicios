package cl.hotelvalledelsol.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public GroupedOpenApi hotelApi() {
        return GroupedOpenApi.builder()
            .group("hotel-api")
            .pathsToMatch("/api/**")
            .build();
    }
}