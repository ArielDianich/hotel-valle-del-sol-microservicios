<<<<<<< HEAD
# Hotel Valle del Sol – Microservicios

Una plataforma de microservicios para gestionar carritos de reserva, inventario de amenidades y habitaciones, y administración de clientes del Hotel Valle del Sol.

---

## 📌 Objetivos de la Evaluación

1. **RA4**: Crear pruebas unitarias para cada microservicio (JUnit 5, Mockito).  
2. **RA5**: Documentar arquitectura y endpoints con OpenAPI/Swagger y HATEOAS.

---

## 🛠️ Tecnologías y Herramientas

- **Lenguaje**: Java 17  
- **Framework principal**: Spring Boot 3.3.0  
- **Dependencias clave**:  
  - Spring Web (`spring-boot-starter-web`)  
  - Spring Data JPA (`spring-boot-starter-data-jpa`)  
  - Spring Validation (`spring-boot-starter-validation`)  
  - Spring Actuator (`spring-boot-starter-actuator`)  
  - Spring HATEOAS (`spring-boot-starter-hateoas`)  
  - Springdoc OpenAPI (`springdoc-openapi-starter-webmvc-ui` v2.6.0)  
  - Springdoc HATEOAS integration (`springdoc-openapi-hateoas` v1.8.0)  
  - MySQL Connector/J v8.0.33  
  - Lombok (opcional para reducir boilerplate)  
- **Pruebas**:  
  - JUnit 5 (`spring-boot-starter-test`)  
  - Mockito JUnit Jupiter (v5.2.0)  
  - DataFaker para datos de prueba (v2.3.0)  
- **Calidad**:  
  - JaCoCo (cobertura de código)  
  - Maven Surefire plugin (v3.0.0-M8)  
- **Documentación**:  
  - Swagger UI (`/swagger-ui/index.html`)  
  - Especificación OpenAPI generada (`docs/openapi.json`)  
- **Construcción y empaquetado**: Maven (wrapper incl.)  
- **Contenerización**: Docker + Docker Compose  
- **IDE recomendado**: VS Code con extensiones de Java y Spring Boot  

---

## 📂 Estructura del Proyecto

hotel-valle-del-sol-microservicios/
├─ src/main/java/
│ └─ cl.hotelvalledelsol/
│ ├─ carrito/
│ │ ├─ controller/ ← CarritoReservasController (HATEOAS + OAS)
│ │ ├─ model/ ← Entidad CarritoReservas
│ │ ├─ repository/
│ │ └─ service/ ← Lógica de negocio + pruebas unitarias
│ ├─ inventario/
│ │ ├─ controller/ ← AmenidadController, HabitacionController, TipoHabitacionController
│ │ ├─ model/ ← Amenidad, Habitacion, TipoHabitacion
│ │ ├─ repository/
│ │ └─ service/ ← Lógica + tests
│ ├─ usuario/
│ │ ├─ controller/ ← ClienteController
│ │ ├─ model/ ← Cliente
│ │ ├─ repository/
│ │ └─ service/ ← Lógica + tests
│ ├─ config/ ← OpenApiConfig, HateoasConfig
│ └─ assembler/ ← ModelAssemblers para HATEOAS
├─ src/main/resources/
│ └─ application.properties ← Configuración DB, puerto, logging
├─ src/test/java/… ← Pruebas unitarias (25 tests)
├─ docs/
│ └─ openapi.json ← Especificación generada
├─ Dockerfile ← Imagen de la app
├─ docker-compose.yml ← App + MySQL
├─ pom.xml ← Dependencias y plugins
└─ README.md ← Esta documentación

## ⚙️ Instalación y Configuración

1. **Clonar repositorio**  
   ```bash
   git clone https://github.com/ArielDianich/hotel-valle-del-sol-microservicios.git
   cd hotel-valle-del-sol-microservicios


=======
\# Hotel Valle del Sol – Microservicios



Una plataforma de microservicios para gestionar carritos de reserva, inventario de amenidades y habitaciones, y administración de clientes del Hotel Valle del Sol.



---



\## 📌 Objetivos de la Evaluación



1\. \*\*RA4\*\*: Crear pruebas unitarias para cada microservicio (JUnit 5, Mockito).  

2\. \*\*RA5\*\*: Documentar arquitectura y endpoints con OpenAPI/Swagger y HATEOAS.



---



\## 🛠️ Tecnologías y Herramientas



\- \*\*Lenguaje\*\*: Java 17  

\- \*\*Framework principal\*\*: Spring Boot 3.3.0  

\- \*\*Dependencias clave\*\*:  

&nbsp; - Spring Web (`spring-boot-starter-web`)  

&nbsp; - Spring Data JPA (`spring-boot-starter-data-jpa`)  

&nbsp; - Spring Validation (`spring-boot-starter-validation`)  

&nbsp; - Spring Actuator (`spring-boot-starter-actuator`)  

&nbsp; - Spring HATEOAS (`spring-boot-starter-hateoas`)  

&nbsp; - Springdoc OpenAPI (`springdoc-openapi-starter-webmvc-ui` v2.6.0)  

&nbsp; - Springdoc HATEOAS integration (`springdoc-openapi-hateoas` v1.8.0)  

&nbsp; - MySQL Connector/J v8.0.33  

&nbsp; - Lombok (opcional para reducir boilerplate)  

\- \*\*Pruebas\*\*:  

&nbsp; - JUnit 5 (`spring-boot-starter-test`)  

&nbsp; - Mockito JUnit Jupiter (v5.2.0)  

&nbsp; - DataFaker para datos de prueba (v2.3.0)  

\- \*\*Calidad\*\*:  

&nbsp; - JaCoCo (cobertura de código)  

&nbsp; - Maven Surefire plugin (v3.0.0-M8)  

\- \*\*Documentación\*\*:  

&nbsp; - Swagger UI (`/swagger-ui/index.html`)  

&nbsp; - Especificación OpenAPI generada (`docs/openapi.json`)  

\- \*\*Construcción y empaquetado\*\*: Maven (wrapper incl.)  

\- \*\*Contenerización\*\*: Docker + Docker Compose  

\- \*\*IDE recomendado\*\*: VS Code con extensiones de Java y Spring Boot  



---



\## 📂 Estructura del Proyecto


hotel-valle-del-sol-microservicios/

├─ src/main/java/

│ └─ cl.hotelvalledelsol/

│ ├─ carrito/

│ │ ├─ controller/ ← CarritoReservasController (HATEOAS + OAS)

│ │ ├─ model/ ← Entidad CarritoReservas

│ │ ├─ repository/

│ │ └─ service/ ← Lógica de negocio + pruebas unitarias

│ ├─ inventario/

│ │ ├─ controller/ ← AmenidadController, HabitacionController, TipoHabitacionController

│ │ ├─ model/ ← Amenidad, Habitacion, TipoHabitacion

│ │ ├─ repository/

│ │ └─ service/ ← Lógica + tests

│ ├─ usuario/

│ │ ├─ controller/ ← ClienteController

│ │ ├─ model/ ← Cliente

│ │ ├─ repository/

│ │ └─ service/ ← Lógica + tests

│ ├─ config/ ← OpenApiConfig, HateoasConfig

│ └─ assembler/ ← ModelAssemblers para HATEOAS

├─ src/main/resources/

│ └─ application.properties ← Configuración DB, puerto, logging

├─ src/test/java/… ← Pruebas unitarias (25 tests)

├─ docs/

│ └─ openapi.json ← Especificación generada

├─ Dockerfile ← Imagen de la app

├─ docker-compose.yml ← App + MySQL

├─ pom.xml ← Dependencias y plugins

└─ README.md ← Esta documentación


## ⚙️ Instalación y Configuración



1\. \*\*Clonar repositorio\*\*  

&nbsp;  ```bash

&nbsp;  git clone https://github.com/ArielDianich/hotel-valle-del-sol-microservicios.git

&nbsp;  cd hotel-valle-del-sol-microservicios

>>>>>>> e2a0a85 (Actualizo README y controlores con HATEOAS y OpenAPI)
