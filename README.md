
# Warehouses Management Monolithic

This project is a system designed to manage warehouses and products using a monolithic architecture. It serves as a foundational implementation for warehouse management operations.

## Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Features](#features)
- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Overview

The Warehouses Management Monolithic project provides a comprehensive solution for managing warehouse operations and product inventories within a single, unified application. This monolithic architecture ensures centralized management and straightforward deployment, making it suitable for small to medium-sized enterprises.

## Architecture

In a monolithic architecture, all components of the application are interconnected and managed as a single codebase. This design simplifies development and deployment but can present challenges in scalability and maintenance as the application grows.

## Getting Started

To set up the project locally, follow the instructions below.

### Prerequisites

Ensure you have the following installed:

- Java Development Kit (JDK) 11 or higher
- Maven
- MySQL or another relational database

### Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/wiktornazaruk/warehouses_management_monolithic.git
   ```

2. **Navigate to the project directory**:

   ```bash
   cd warehouses_management_monolithic
   ```

3. **Configure the database**:

   - Set up a MySQL database.
   - Update the database configuration in `src/main/resources/application.properties` with your database credentials.

4. **Build the application using Maven**:

   ```bash
   mvn clean install
   ```

5. **Run the application**:

   ```bash
   java -jar target/warehouses_management_monolithic.jar
   ```

### Usage

Once the application is running, you can access it at `http://localhost:8080`. Use tools like Postman or a web browser to interact with the available endpoints for managing products, warehouses, and orders.

## Contributing

Contributions are welcome! Please fork the repository and create a new branch for your feature or bug fix. Submit a pull request with a detailed description of your changes.

## License

This project is licensed under the MIT License.

---

This monolithic architecture provides a solid foundation for warehouse and product management, suitable for organizations seeking a centralized and straightforward solution.
