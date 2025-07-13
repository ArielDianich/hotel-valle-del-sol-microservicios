# Etapa de construcción
FROM maven:3.9.1-eclipse-temurin-17 AS build
WORKDIR /app

# Copiamos pom y descargamos dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiamos el código fuente y compilamos
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copiamos el jar desde la etapa build
COPY --from=build /app/target/hotel-valle-del-sol-1.0.0-SNAPSHOT.jar app.jar

# Puerto expuesto y comando de arranque
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]

RUN addgroup -S app && adduser -S app -G app
USER app

HEALTHCHECK --interval=30s --timeout=5s \
  CMD curl -f http://localhost:8080/actuator/health || exit 1
