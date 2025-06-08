🟢 How to Run the Application
✅ Prerequisites
Java 17+ must be installed and available in your PATH.

Make sure the JAVA_HOME environment variable is set correctly.
For example:

export JAVA_HOME=/path/to/your/java
export PATH=$JAVA_HOME/bin:$PATH
You can verify installation by running:

java -version

🚀 Running the Application
No special setup is required to run the application.

For the purpose of this demo, an embedded H2 database is used in both dev and test profiles.
The prod profile is intentionally excluded, as production usage is out of scope.
Creating an external database is also outside the scope due to the nature of the provided JSON files.

You can start the application by running the main class annotated with @SpringBootApplication:

From your IDE (e.g. IntelliJ: right-click on the main class → Run).

Or from the terminal using Gradle:

bash
./gradlew bootRun

Once started, the API will be available at:
http://localhost:8080


Application Properties Overview
The application.properties file contains configuration details related to the embedded H2 database and the project setup:

properties

spring.application.name=assessment
spring.datasource.url=jdbc:h2:file:C:/Users/rarue/test;AUTO_SERVER=TRUE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

spring.main.banner-mode=console
logging.level.root=INFO

The H2 database is configured to persist data to a file on the local filesystem (C:/Users/rarue/test).

The H2 web console is enabled and accessible at /h2-console for interactive database access.

Important: To access the H2 console locally, you might need to adjust the JDBC URL (spring.datasource.url) to match your local environment path.

🚀 A postman collection has been included assessment.postman_collection.json

🚀User Payments API

Description
This API reads data from three JSON files (products.json, orders.json, payments.json) and exposes endpoints to:

Get the total amount paid by each user.

Get the total amount each user still owes.

Create new users via a POST endpoint. (This was created because I wanted to include it).

🧱 Clean Architecture Overview
This project follows the principles of Clean Architecture, which promotes separation of concerns and makes the codebase easier to maintain, test, and evolve. The key layers used are:

1. Domain
   Contains the core business logic and models.

Independent from any external framework or technology.

Pure Java classes, highly testable and reusable.

2. Application / Service
   Orchestrates business rules using domain entities.

Contains use cases and business workflows.

Depends only on the domain layer.

3. Infrastructure
   Contains technical implementations (e.g., database access, mappers).

Provides adapters for data persistence, resource loading, etc.

Implements interfaces defined in the domain or service layer.

4. Interfaces / Controller
   Responsible for handling HTTP requests and responses (REST API).

Converts incoming DTOs to domain models and vice versa using mappers.

Acts as the entry point for external communication (e.g., frontend or other systems).

✅ Why Use Clean Architecture?
Testability: Business logic is isolated and can be tested without involving controllers or databases.

Decoupling: Changes in one layer (e.g., switching from H2 to PostgreSQL) don’t affect others.

Maintainability: Clear separation allows easier debugging and future enhancements.

Flexibility: External interfaces (like REST or persistence) can be replaced without modifying the core logic.

🧪 Testing
This project includes both unit tests and integration tests to ensure correctness and reliability:

✅ Unit Tests
Use mocking (e.g., with Mockito) to isolate components and test business logic independently.

Fast and focused on service-level behavior.

./gradlew test

✅ Integration Tests
Annotated with @SpringBootTest, @AutoConfigureMockMvc, and @ActiveProfiles("test").

Test the full Spring context, including controllers and real HTTP requests via MockMvc.

Use JSON test data files located under src/test/resources/db/ to simulate real-world scenarios.

These tests help validate both isolated components and complete application flows.

📦 Initialization with JSON Files
Three JSON files were provided for initialization purposes:

orders.json

payments.json

products.json

A package named adapter.starter was created exclusively to ensure these files are properly loaded at application startup.

⚠️ This module is not intended to reflect a real-world production solution.
Its only purpose is to simulate a working scenario with the provided data and is out of scope for testing or production-level architecture.