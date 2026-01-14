# Pricing Service

Servicio REST para consultar el precio aplicable de un producto para una cadena (brand) en una fecha concreta.

La selección del precio se realiza:
- filtrando por `brandId` + `productId`
- aplicando la fecha `applicationDate` dentro del rango `[startDate, endDate]`
- si existen varios precios aplicables, se escoge el de **mayor prioridad**.

## Tech stack
- Java 17
- Spring Boot
- Spring Web (MVC)
- Spring Data JPA
- H2 (in-memory)
- Flyway (migraciones)
- JUnit 5 + MockMvc (tests)

## Arquitectura
Arquitectura Hexagonal (DDD):
- `domain`: modelo de negocio y puertos (sin Spring/JPA)
- `application`: casos de uso y DTOs
- `infrastructure`: adaptadores de entrada (REST) y salida (JPA)

## Cómo ejecutar
Requisitos: Java 17 y Maven.

```bash
mvn spring-boot:run
