# Task Manager API

A REST API for managing personal to-do tasks, built with Spring Boot. I made it to learn backend development in Java. It started as a plain console app and grew into an API with a database, login and JWT authentication.

**Live demo:** https://task-manager-api-4egm.onrender.com

It runs on a free host, so the first request after a quiet period can take about a minute while the server wakes up.

## What it does

- Sign up and log in. Passwords are hashed with BCrypt.
- Logging in returns a JWT, which you send with every request to the task endpoints.
- Create, view, update and delete tasks.
- Each user can only see and change their own tasks.
- Empty titles are rejected, and a missing task returns a clean 404.

## Built with

Java 21, Spring Boot, Spring Security, Spring Data JPA, PostgreSQL, JWT, Maven, JUnit, Mockito and Docker. Deployed on Render.

## Endpoints

| Method | Path | Needs login |
|---|---|---|
| POST | `/signup` | No |
| POST | `/login` | No |
| GET | `/tasks` | Yes |
| POST | `/tasks` | Yes |
| PUT | `/tasks/{id}` | Yes |
| DELETE | `/tasks/{id}` | Yes |

For the endpoints that need login, send the token from `/login` in a header:

```
Authorization: Bearer <your token>
```

Example:

```bash
curl -X POST https://task-manager-api-4egm.onrender.com/login \
  -H "Content-Type: application/json" \
  -d '{"username": "demo", "password": "your_password"}'
```

## Running it locally

You need JDK 21 and PostgreSQL.

1. Create a PostgreSQL database called `taskmanager`.
2. Set two environment variables with your database login: `DB_USERNAME` and `DB_PASSWORD`.
3. Start the app:

```bash
./mvnw spring-boot:run
```

It runs on `http://localhost:8080`, and the tables are created automatically.

There is also a Dockerfile:

```bash
docker build -t taskmanager .
docker run -p 8080:8080 \
  -e DB_USERNAME=postgres \
  -e DB_PASSWORD=your_password \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/taskmanager \
  taskmanager
```

## Notes

- The JWT signing key is generated when the app starts, so tokens stop working after a restart. In a real deployment I would load it from an environment variable.
- Tests are written with JUnit and Mockito and cover the Task class and the task controller.

## Author

Malavika Sajith - [GitHub](https://github.com/Malavika-sajith)