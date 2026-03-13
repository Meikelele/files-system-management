<div align="center">

# Files System Management
Backend application for managing files and folders metadata, built with Java and Spring Boot.


</div>

---
# Tech Stack

![Java17](https://img.shields.io/badge/-Java%2017-ffffff?style=flat-square&logo=openjdk&logoColor=000000)
![SpringBoot](https://img.shields.io/badge/-Spring%20Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/-PostgreSQL-4169E1?style=flat-square&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/-Apache%20Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white)
![JUnit5](https://img.shields.io/badge/-JUnit%205-25A162?style=flat-square&logo=junit5&logoColor=white)
![Docker](https://img.shields.io/badge/-Docker-2496ED?style=flat-square&logo=docker&logoColor=white)
![GitHub](https://img.shields.io/badge/-GitHub-181717?style=flat-square&logo=github&logoColor=white)

Additional tools:

- **OpenAPI / Swagger UI** – API documentation
- **Spring Data JPA** – persistence layer
- **Hibernate** – ORM
- **Lombok** – boilerplate reduction

---

# Table of Contents

- [Project Goal](#project-goal)
- [Project Scope](#project-scope)
- [Architecture](#architecture)
- [API Documentation](#api-documentation)
- [Tech Stack](#tech-stack)
- [Running the Project](#running-the-project)
- [Database](#database)
- [Project Status](#project-status)
- [CI](#ci)


---

# Project Goal
The goal of this project is to build a backend application that manages a file system structure based on metadata.

The system simulates a simplified file system where users can organize files inside folders and build hierarchical structures.

The application exposes a REST API that allows interacting with the file system structure and generating statistics based on stored metadata.


---

# Project Scope

The application will support:
- creating and managing files,
- creating and managing folders,
- assigning files to folders,
- building nested folder structures,
- generating statistics and reports based on stored data.

### Domain assumptions

For simplicity of the model:

- **each file belongs to exactly one folder**
- folders may contain multiple files
- folders may contain multiple child folders

This allows building a hierarchical folder tree similar to a typical file system.

---
# Architecture

The application follows a typical **Spring Boot layered architecture**:
Controller -> Service -> Repository -> Database

### Layers

**Controller**

Handles HTTP requests and exposes REST endpoints.

**Service**

Contains business logic and orchestrates operations on the domain model.

**Repository**

Responsible for data access using Spring Data JPA.

**Model**

Represents domain entities stored in the database.

---

# API Documentation

The project uses **OpenAPI and Swagger UI** to automatically generate REST API documentation.

Swagger UI allows developers to:

- explore available endpoints
- inspect request/response schemas
- test API endpoints directly from the browser

### Swagger UI
```
http://localhost:8080/swagger-ui
```

### OpenAPI specification
```
http://localhost:8080/api-docs
```

--- 
# Running the Project

### 1 Start the database

The project uses Docker to run PostgreSQL locally.
```
docker compose up -d
```
### 2 Run the application

Using Maven:
```
mvn spring-boot:run
```
---
# Database

The project uses **PostgreSQL** as the primary database.

The database is started using Docker with the configuration defined in:
```
docker-compose.yml
```
### Main database settings:  
- **database**: `files_manager`  
- **user**: `postgres`  
- **password**: `postgres`  
- **port**: `5432`  

---
# Project Status

Current stage:

- project setup
- CI pipeline configured
- folder domain model implemented
- folder API implemented
- Swagger API documentation added

Next planned features:

- file domain model
- file management API
- file statistics
- reporting endpoints


---
# CI

The project uses **GitHub Actions** to automatically build the application and run Maven tests on every push and pull request.

The CI pipeline:

- builds the project
- starts a PostgreSQL service container
- runs Maven tests 

  Workflow file:
```
.github/workflows/ci.yml
```