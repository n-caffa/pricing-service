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

## Api
Obtener precio aplicable

GET /api/prices/applicable
Query params
- brandId (long, > 0)
- productId (long, > 0)
- applicationDate (ISO-8601, por ejemplo: 2020-06-14T16:00:00)

curl "http://localhost:8080/api/prices/applicable?brandId=1&productId=35455&applicationDate=2020-06-14T16:00:00"

Respuesta (200 OK)

{
 "productId": 35455,
 "brandId": 1,
 "priceList": 2,
 "startDate": "2020-06-14T15:00:00",
 "endDate": "2020-06-14T18:30:00",
 "price": 25.45,
 "currency": "EUR"
}

## Notas de implementación

- La lógica de selección prioriza el registro con priority más alto en caso de solape.
- La capa de persistencia (JPA) está aislada en infraestructura mediante adaptadores que implementan puertos del dominio.

Se incluyen tests:
-  Unitarios (casos de uso y validaciones de dominio/DTO)
-  Integración (repositorio JPA con H2/Flyway)
-  Sistema (endpoint REST con MockMvc y casuísticas del enunciado)