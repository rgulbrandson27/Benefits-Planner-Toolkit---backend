# Benefits Planner Toolkit – Backend

RESTful API built with Spring Boot for SSDI work incentives planning and benefits calculations.

**Frontend Repository:** https://github.com/yourname/benefits-planner-frontend

## Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

## Features
- Multi-tenant organization / client / scenario data model
- SSDI work incentives calculation endpoints
- Timeline-based data persistence (month-by-month facts)
- Derived rule computation (TWP, SGA, EPE, cessation/grace)
- DTO-based API contracts

## Architecture Notes
This backend treats **timeline month records as the source of truth**.  
Derived SSDI states (TWP sequence, SGA status, EPE months) are computed from timeline data and returned to the frontend.  
No derived business meaning is stored in the database.

## API Design
Follows REST principles with DTOs for clean separation between API contracts and persistence entities.