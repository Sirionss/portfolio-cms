# portfolio-cms

Headless CMS for my personal portfolio. Exposes a REST API for the frontend and a JWT-protected admin surface for content editing.

Live: https://portfolio-cms-production-2d01.up.railway.app

## Stack

Java 17, Spring Boot 4.0.6, Spring Data JPA, PostgreSQL 16, Spring Security + JJWT 0.12, JUnit 5 + Mockito, Docker.

## API

All resources support the standard `GET /`, `GET /{id}`, `POST /`, `PUT /{id}`, `DELETE /{id}`. `GET` is public; write operations require `ROLE_ADMIN`.

| Resource       | Path                |
|----------------|---------------------|
| About          | `/api/about`        |
| Skills         | `/api/skills`       |
| Projects       | `/api/projects`     |
| Education      | `/api/education`    |
| Contact links  | `/api/contacts`     |
| Auth           | `POST /api/auth/login` |

`POST /api/auth/login` accepts `{ "username": "...", "password": "..." }` and returns `{ "token": "<jwt>" }`. Send it as `Authorization: Bearer <jwt>` on write requests.

## Run with Docker

```bash
cp .env.example .env   # fill in the values below
docker compose up --build
```

App: `http://localhost:8080`, Postgres: `localhost:5433` (host) → `5432` (container).

## Run locally (without Docker)

Requires a local Postgres on `5432` with a `portfolio` database.

```bash
./mvnw spring-boot:run
```

On Windows/PowerShell, set env vars first:

```powershell
$env:DB_PASSWORD="..."; $env:JWT_SECRET_TOKEN="..."; $env:ADMIN_PASSWORD="..."
./mvnw.cmd spring-boot:run
```

## Environment variables

| Name                 | Purpose                                      |
|----------------------|----------------------------------------------|
| `DB_USERNAME`        | Postgres user (default `postgres`)           |
| `DB_PASSWORD`        | Postgres password                            |
| `JWT_SECRET_TOKEN`   | HMAC secret for signing JWTs (Base64, 32+ B) |
| `ADMIN_USERNAME`     | Seeded admin username (default `admin`)      |
| `ADMIN_PASSWORD`     | Seeded admin password                        |

The admin user is created on first startup if the `users` table is empty.

## CORS

Whitelisted origins are hardcoded in `SecurityConfig`: `http://localhost:3000`, `http://localhost:5173`, `https://sirionss.github.io`. Extend that list when adding the Vercel frontend.

## Tests

```bash
./mvnw test
```

Service-layer tests are in `src/test/java/com/portfolio/portfolio_cms/service`.

## Structure

```
src/main/java/com/portfolio/portfolio_cms/
  controller/   # REST controllers
  service/      # Business logic
  repository/   # Spring Data JPA repositories
  model/        # JPA entities
  dto/          # Request/response DTOs
  security/     # JWT filter, SecurityConfig, UserDetailsService
  exception/    # ResourceNotFoundException + global handler
```