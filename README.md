---

# 🐣 Zimaku Spring Boot Backend

A robust, modular backend for managing hatchery, production, and sales operations, built with Spring Boot, JPA, and JWT security. Designed for scalability, maintainability, and real-world poultry business needs.

---

## 🚀 Features

- **User Authentication & Authorization** (JWT, Spring Security)
- **Sales Management** (Clients, Orders, Pricing)
- **Production Tracking** (Eggs, Chicks, Dispatch)
- **Auditing & Logging** (Spring Data Auditing)
- **RESTful API** (Spring Web)
- **PostgreSQL Integration** (Dockerized)
- **MapStruct DTO Mapping**
- **Lombok for Clean Code**

---

## 📦 Domain Model (UML)

![Editor _ Mermaid Chart-2025-06-24-135143](https://github.com/user-attachments/assets/83ef312b-6975-41de-b5b3-d46f46c66178)


---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3.4**
- **Spring Data JPA**
- **Spring Security (JWT)**
- **PostgreSQL**
- **MapStruct**
- **Lombok**
- **Docker Compose**

---

## 🐳 Quickstart (Local Dev)

1. **Clone the repo**
2. **Start PostgreSQL & PGAdmin:**
   ```sh
   docker compose up -d
   ```
   - DB: `localhost:5433`, user: `root`, pass: `root`, db: `zimaku`
   - PGAdmin: `localhost:5050`, login: `postgres@gmail.com` / `password`

3. **Run the app:**
   ```sh
   ./mvnw spring-boot:run
   ```

4. **API available at:** `http://localhost:8080`

---

## ⚙️ Configuration

See [`src/main/resources/application.properties`](src/main/resources/application.properties):

```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/zimaku
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=create-drop
```

---

## 📝 Testing

- JUnit 5, Spring Boot Test
- Run all tests:
  ```sh
  ./mvnw test
  ```

---

## 📂 Project Structure

- `domain/` - Business logic (auth, user, sales, production, hatchery)
- `mapper/` - DTO <-> Entity mappers (MapStruct)
- `security/` - JWT, user details, filters
- `exception/` - Global error handling
- `config/` - Spring & persistence config

---

## 💡 Why Zimaku?

- **Modular**: Clear separation of concerns
- **Secure**: JWT, role-based access
- **Audited**: Tracks who/when for all changes
- **Ready for Scale**: Docker, PostgreSQL, MapStruct
