# Java Exercise - Spring Boot with PostgreSQL

This is a simple Spring Boot application demonstrating CRUD operations with PostgreSQL database.

## Prerequisites

- Java 17
- Maven
- PostgreSQL (running on port 5433)

## Database Setup

1. Make sure PostgreSQL is running on port 5433
2. Create a database named `postgres` (if not exists)
3. Update the database credentials in `src/main/resources/application.properties` if needed

## Building and Running the Application

1. Build the project:
```bash
mvn clean install
```

2. Run the application:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### Users API

- GET `/api/users` - Get all users
- GET `/api/users/{id}` - Get user by ID
- POST `/api/users` - Create a new user
- PUT `/api/users/{id}` - Update an existing user
- DELETE `/api/users/{id}` - Delete a user

### Sample User JSON

```json
{
    "name": "John Doe",
    "email": "john@example.com",
    "password": "password123"
}
```
