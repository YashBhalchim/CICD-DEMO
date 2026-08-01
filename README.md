# Employee Service

A simple Spring Boot employee management service with CRUD operations, JPA persistence, H2 support, Docker support, and JUnit tests.

## Features

- Create employee
- Update employee
- Delete employee
- Get employee list
- Spring Boot 3.2
- Spring Data JPA
- H2 database (default)
- JUnit and Mockito tests
- Docker and Docker Compose support

## Project Structure

```text
employee-service
├── src
│   ├── main
│   │   └── java
│   └── test
├── pom.xml
├── Dockerfile
├── docker-compose.yml
└── .github/workflows/cicd.yml
```

## API Endpoints

### Create employee
- Method: POST
- URL: /api/employees
- Request body:

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "department": "Engineering",
  "role": "Developer",
  "salary": 75000
}
```

### Update employee
- Method: PUT
- URL: /api/employees/{id}
- Request body: same as create employee

### Delete employee
- Method: DELETE
- URL: /api/employees/{id}

### Get all employees
- Method: GET
- URL: /api/employees

## Run Locally

### Prerequisites
- Java 17+
- Maven 3.8+

### Build and run

```bash
mvn clean verify
mvn spring-boot:run
```

The service will start on port 8081.

### Run the packaged jar

```bash
mvn package
java -jar target/employee-service-1.0.0.jar
```

## Run with Docker

### Build image

```bash
docker build -t employee-service .
```

### Run container

```bash
docker run -p 8081:8081 employee-service
```

### Run with Docker Compose

```bash
docker-compose up --build
```

## Testing

Run tests:

```bash
mvn test
```

## CI/CD

A GitHub Actions workflow is included in [.github/workflows/cicd.yml](.github/workflows/cicd.yml) for build and test automation.
