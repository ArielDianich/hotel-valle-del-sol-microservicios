# Hotel Valle del Sol – Microservicios

Aplicación de microservicios para la gestión de reservas, inventario y clientes del Hotel Valle del Sol.  
Desarrollado con Spring Boot, HATEOAS y documentado con OpenAPI/Swagger.

---

## 🎯 Objetivos de la Evaluación

1. **RA4:** Implementar pruebas unitarias (JUnit 5, Mockito) para garantizar la calidad del código.  
2. **RA5:** Documentar la arquitectura, endpoints y flujos con OpenAPI/Swagger y HATEOAS.

---

## 🛠 Tecnologías y Herramientas

- **Java 17**  
- **Spring Boot 3.3.0**  
  - Web, Data JPA, Validation, Actuator, HATEOAS  
- **OpenAPI / Swagger UI**  
  - `springdoc-openapi-starter-webmvc-ui` (v2.6.0)  
  - `springdoc-openapi-hateoas` (v1.8.0)  
- **MySQL 8** (conector 8.0.33)  
- **Pruebas**: JUnit 5, Mockito JUnit Jupiter, DataFaker  
- **Cobertura**: JaCoCo (0.8.10)  
- **Construcción**: Maven (wrapper incluido)  
- **Docker + Docker Compose**  
- **IDE recomendado**: VS Code con extensiones Java/Spring

---

## 📂 Estructura del Proyecto

hotel-valle-del-sol-microservicios/
├── src/main/java/cl/hotelvalledelsol/
│ ├── carrito/ ← CarritoReservasController, etc.
│ ├── inventario/ ← Amenidad, Habitacion, TipoHabitacion
│ ├── usuario/ ← ClienteController
│ ├── config/ ← OpenApiConfig, HateoasConfig
│ └── assembler/ ← ModelAssemblers HATEOAS
├── src/main/resources/
│ └── application.properties
├── src/test/java/… ← Tests unitarios (25 tests)
├── docs/
│ └── openapi.json ← Spec OpenAPI generada
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md

yaml
Copiar código

---

## ⚙️ Instalación y Configuración

1. Clona el repo:  
   ```bash
   git clone https://github.com/ArielDianich/hotel-valle-del-sol-microservicios.git
   cd hotel-valle-del-sol-microservicios
Configura MySQL en src/main/resources/application.properties:

properties
Copiar código
spring.datasource.url=jdbc:mysql://localhost:3306/hotel
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
spring.jpa.hibernate.ddl-auto=update
▶️ Ejecución
bash
Copiar código
./mvnw clean package
java -jar target/hotel-valle-del-sol-1.0.0-SNAPSHOT.jar
Accede a Swagger UI en http://localhost:8080/swagger-ui/index.html.

✅ Pruebas
bash
Copiar código
./mvnw clean test
25 tests unitarios pasan con cobertura JaCoCo.

🐳 Docker
bash
Copiar código
docker-compose up --build
La app estará en http://localhost:8080, MySQL en el contenedor db.

📝 Documentación Adicional
docs/openapi.json

Plan de pruebas y diagramas en docs/

