Spring Boot JWT example

Build & run:
mvn spring-boot:run

Endpoints:
POST /api/auth/register  { "username": "user", "password": "pass" }
POST /api/auth/login     { "username": "user", "password": "pass" } -> returns token
GET  /api/user/me        (Authorization: Bearer <token>)
H2 Console: /h2-console (jdbc:h2:mem:testdb)
# springboot_jwt
