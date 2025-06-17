# 🏨 Hotel Valle del Sol - Sistema basado en Microservicios

Este proyecto es una propuesta de migración de un sistema monolítico a una arquitectura de microservicios para el Hotel Valle del Sol. Permite gestionar usuarios, inventario de productos y carritos de compra de forma independiente, escalable y segura.

---

## 🚀 Tecnologías utilizadas

- **Backend:** Spring Boot 3.5
- **Base de datos:** Oracle Autonomous (via JDBC y wallet)
- **Contenerización:** Docker (opcional para producción)
- **Postman:** para pruebas de API REST
- **Validación:** Jakarta Bean Validation
- **Documentación y control:** Git + GitHub

---

## 🧱 Microservicios incluidos

| Servicio     | Puerto | Descripción                                       |
|--------------|--------|---------------------------------------------------|
| `usuario`    | 8081   | Registro, consulta y administración de usuarios  |
| `inventario` | 8080   | Gestión de productos y stock                      |
| `carrito`    | 8080   | Carrito de compra con cantidad de productos      |

Cada uno tiene su propio `pom.xml`, `Controller`, `Model`, `Repository` y configuración de conexión.

---

## 🧪 Pruebas

Todos los endpoints CRUD fueron probados con Postman. Se incluye:

- ✅ Archivo de colección: `hotel-valle-microservicios.postman_collection.json`
- ✅ Excel con ejemplos de peticiones: `hotel-valle-endpoints.xlsx`

---

## ⚙️ Cómo ejecutar

1. Clona este repositorio:
   ```bash
   git clone https://github.com/tu-usuario/hotel-valle-microservicios.git
