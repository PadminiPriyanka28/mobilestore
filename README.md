
# Mobile Store Application

This is a Spring Boot-based mobile store application that allows users to browse mobile products, add items to the cart, view the cart, and complete purchases. The application supports user authentication and provides a simple interface for managing the shopping cart and checkout process.

## Features

- User authentication (login/logout)
- View mobile products in a catalog
- Add items to the shopping cart
- View and manage the shopping cart
- Checkout functionality with basic payment form
- Responsive design for use on desktop and mobile devices

## Technologies Used

- **Backend**: Spring Boot, Spring Data JPA, Spring Security
- **Frontend**: HTML, CSS, JavaScript (with AJAX for dynamic content)
- **Database**: H2 Database (for development)
- **Build Tool**: Maven

## Setup Instructions

Follow these steps to set up the application locally.

### Prerequisites

- Java 11 or later
- Maven 3.6 or later
- IDE like IntelliJ IDEA or Eclipse (optional)

### 1. Clone the repository

```bash
git clone https://github.com/yourusername/springboot-mobile-store.git
cd springboot-mobile-store
```

### 2. Build the project

Run the following Maven command to build the project:

```bash
mvn clean install
```

### 3. Run the application

You can run the application using Maven:

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080` by default.

Alternatively, you can run the application from your IDE by running the `MobileStoreApplication.java` class.

### 4. Access the application

Once the application is running, you can access it by navigating to the following URL in your web browser:

```
http://localhost:8080
```

### 5. Database Configuration

The application uses an in-memory H2 database by default, so there’s no need to configure a database unless you wish to use a different one. The database will be populated with some initial data when the application starts.

## User Authentication

- You can log in using the following default credentials:

  - **Username**: `user`
  - **Password**: `password`

## Application Endpoints

- `GET /`: Displays the homepage with featured products.
- `GET /cart`: Displays the user's cart.
- `POST /cart/add-to-cart/{productId}`: Adds a product to the cart.
- `GET /checkout`: Displays the checkout page.
- `POST /checkout/submit`: Submits the order (for demonstration purposes, the order is not processed).

## Directory Structure

- **/src/main/java**: Contains all Java source code files (Spring Boot controllers, services, repositories).
- **/src/main/resources**: Contains configuration files and static resources (HTML, CSS, JS).
- **/src/main/resources/application.properties**: Configuration for database, server port, etc.
- **/src/test/java**: Contains unit tests for the application.

## Future Enhancements

- Integration with a real payment gateway.
- Implement user registration and profile management.
- Add product search functionality.
- Integrate with a real database for production use.
- Add more sophisticated cart management (e.g., quantities, product removal).
  


## Acknowledgments

- Spring Boot for providing a robust framework for developing backend services.
- H2 Database for the in-memory database used during development.
- JavaScript and CSS libraries used for the frontend design and interactivity.

```
