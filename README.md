# URL Shortener

A backend URL shortening service built with Java and Spring Boot.

The application accepts long URLs, stores URL mappings in a relational database, and generates compact Base62 short codes using database-generated identifiers. A stored short code can later be resolved to its original URL.

## About the Project

This project was built to explore backend API development with Spring Boot and understand how URL shortening systems can generate compact and unique identifiers.

Instead of generating random strings, the application uses the database-generated identifier of a URL mapping and converts it to Base62. This provides a deterministic and compact short code while avoiding repeated random-code generation attempts.

The project follows a layered Spring Boot architecture to separate API handling, business logic, and persistence responsibilities.

## Features

* Create shortened URLs from original URLs
* Generate compact short codes using Base62 encoding
* Resolve short codes to their original URLs
* Persist URL mappings using a relational database
* RESTful API design
* Global exception handling
* Request validation
* Configurable CORS support
* Environment-based application configuration
* Layered backend architecture

## Tech Stack

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* Relational Database
* Maven
* Lombok

### Supported Databases

The persistence layer is implemented using Spring Data JPA and Hibernate.

The application can be configured to use relational databases such as:

* MySQL
* PostgreSQL

The appropriate JDBC driver must be included in the Maven configuration and the datasource environment variables must be configured for the selected database.

## Project Architecture

```text
src/main/java/com/desync/url_shortener
├── config
├── controller
├── dto
├── exception
├── model
├── repository
├── service
└── UrlShortenerApplication.java
```

### Layer Responsibilities

* **Controller** — Handles HTTP requests and responses.
* **DTO** — Defines API request and response data structures.
* **Service** — Contains URL shortening and URL resolution business logic.
* **Repository** — Provides database access using Spring Data JPA.
* **Model** — Represents URL mapping database entities.
* **Exception** — Contains custom exceptions and centralized exception handling.
* **Config** — Contains application configuration such as CORS settings.

## How URL Shortening Works

1. The client submits an original URL.
2. A URL mapping is created and persisted in the database.
3. The database generates a unique numeric identifier for the mapping.
4. The identifier is converted to Base62.
5. The generated Base62 value is stored as the short code.
6. The application returns the shortened URL.
7. When the short code is requested, the application retrieves the associated original URL.

### Base62 Character Set

```text
0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ
```

Base62 uses digits, lowercase letters, and uppercase letters to represent numeric identifiers using fewer characters.

## Why Base62?

Base62 is suitable for URL short codes because it uses:

* `0-9`
* `a-z`
* `A-Z`

This allows numeric database identifiers to be represented using compact, URL-friendly strings.

For example, a large numeric identifier can be encoded using fewer characters than its decimal representation.

## Prerequisites

Before running the project, install:

* Java
* Git
* A supported relational database such as MySQL or PostgreSQL

Maven does not need to be installed separately because the project includes the Maven Wrapper.

## Getting Started

### 1. Clone the Repository

Using the GitHub CLI:

```bash
gh repo clone vincent-silveira/url-shortner
cd url-shortner
```

Alternatively, using Git:

```bash
git clone https://github.com/vincent-silveira/url-shortner.git
cd url-shortner
```

### 2. Create the Database

Create a database using your preferred supported relational database.

#### MySQL

```sql
CREATE DATABASE url_shortener;
```

#### PostgreSQL

```sql
CREATE DATABASE url_shortener;
```

The database name can be changed as long as the configured datasource URL points to the correct database.

### 3. Configure Environment Variables

The application reads database and application configuration from environment variables.

The following environment variables are required:

```env
SERVER_PORT=8080

DB_URL=jdbc:mysql://localhost:3306/url_shortener
DB_USERNAME=root
DB_PASSWORD=your_database_password

FRONTEND_URL=http://localhost:3000
```

Replace the values with your local database and application configuration.

> Do not commit database credentials or `.env` files containing sensitive values to Git.

### MySQL Configuration Example

```env
SERVER_PORT=8080

DB_URL=jdbc:mysql://localhost:3306/url_shortener
DB_USERNAME=root
DB_PASSWORD=your_mysql_password

FRONTEND_URL=http://localhost:3000
```

### PostgreSQL Configuration Example

```env
SERVER_PORT=8080

DB_URL=jdbc:postgresql://localhost:5432/url_shortener
DB_USERNAME=postgres
DB_PASSWORD=your_postgresql_password

FRONTEND_URL=http://localhost:3000
```

When changing the database provider, ensure that the appropriate JDBC driver is included in `pom.xml`.

For MySQL:

```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
```

For PostgreSQL:

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```

## Running the Application

### Using IntelliJ IDEA

1. Open IntelliJ IDEA.
2. Select **Open** and open the cloned `url-shortner` directory.
3. Allow IntelliJ IDEA to load and synchronize the Maven project.
4. Open the application run configuration.
5. Add the required environment variables:

```text
SERVER_PORT=8080
DB_URL=jdbc:mysql://localhost:3306/url_shortener
DB_USERNAME=root
DB_PASSWORD=your_database_password
FRONTEND_URL=http://localhost:3000
```

6. Ensure the configured database server is running.
7. Open `UrlShortenerApplication.java`.
8. Run the `main` method.

If you use a `.env` file through an IntelliJ IDEA plugin or environment configuration, ensure the `.env` file is loaded by the application run configuration.

> Spring Boot does not automatically load `.env` files. Environment variables must be supplied through the operating system, IDE run configuration, or another environment-loading mechanism.

### Using the Maven Wrapper

On Windows:

```bash
mvnw.cmd spring-boot:run
```

On Linux or macOS:

```bash
./mvnw spring-boot:run
```

The required environment variables must be available in the terminal environment before starting the application.

### Using Maven

If Maven is installed globally:

```bash
mvn spring-boot:run
```

The application will start on the configured server port.

For example:

```text
http://localhost:8080
```

## API Usage

The application provides operations for creating shortened URLs and resolving existing short codes.

| Operation   | Method | Description                               |
| ----------- | ------ | ----------------------------------------- |
| Shorten URL | `POST` | Creates a short URL mapping               |
| Resolve URL | `GET`  | Resolves a short code to its original URL |

### Example Shorten Request

```json
{
  "originalUrl": "https://example.com/some/very/long/url"
}
```

### Example Response

```json
{
  "shortUrl": "http://localhost:8080/1Z"
}
```

## Error Handling

The application uses centralized exception handling to provide structured API error responses.

For example, requesting an unknown short code results in a URL mapping not found response instead of exposing an internal application exception.

## CORS Configuration

CORS configuration allows a configured frontend application to access the backend API.

The frontend origin is supplied through application configuration, allowing different frontend URLs to be used across development and production environments.

## Security Notes

* Do not commit database credentials.
* Do not commit `.env` files containing sensitive configuration.
* Use environment variables for environment-specific configuration.
* Use separate configuration values for development and production deployments.
* Validate URLs before storing mappings.
* Production deployments should use HTTPS.

## Future Improvements

Possible improvements for the project include:

* URL expiration
* Custom short codes
* Click analytics
* Access count tracking
* QR code generation
* User accounts and authentication
* Per-user URL management
* Rate limiting
* Redis caching for frequently accessed URLs
* Docker support
* Automated tests
* API documentation with OpenAPI/Swagger
* Production deployment configuration

## Author

**Vincent Silveira**

Backend project developed using Java and Spring Boot.


