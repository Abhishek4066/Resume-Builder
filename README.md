# Resume Builder Project

Welcome to the Resume Builder project! This application is designed to help users create professional resumes by entering their details and customizing the resume theme. The project utilizes Thymeleaf for the frontend, Spring Boot for the backend, MySQL for the database, and Spring Security for authentication.

## Table of Contents
1. [Features](#features)
2. [Prerequisites](#prerequisites)
3. [Installation](#installation)
4. [Configuration](#configuration)
5. [Usage](#usage)
6. [Themes](#themes)
7. [Contributing](#contributing)
8. [License](#license)

## Features<a name="features"></a>
- User authentication and authorization using Spring Security.
- User-friendly web interface powered by Thymeleaf.
- Persistent storage of user data in a MySQL database.
- Dynamic resume generation based on user-entered details.
- Multiple resume themes to choose from.

## Prerequisites<a name="prerequisites"></a>
Before you begin, ensure you have the following installed:
- Java Development Kit (JDK) 8 or higher
- Maven
- MySQL Database
- Your favorite IDE (IntelliJ IDEA, Eclipse, etc.)

## Installation<a name="installation"></a>
1. Clone the repository to your local machine:
    ```bash
    git clone https://github.com/your-username/resume-builder.git
    ```

2. Open the project in your IDE.

3. Build the project using Maven:
    ```bash
    mvn clean install
    ```

## Configuration<a name="configuration"></a>
1. Configure the MySQL database settings in `src/main/resources/application.properties`:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/resume_builder
    spring.datasource.username=your-username
    spring.datasource.password=your-password
    ```

2. Adjust any other application settings if necessary.

## Usage<a name="usage"></a>
1. Run the application:
    ```bash
    java -jar target/resume-builder-1.0.0.jar
    ```

2. Access the application at [http://localhost:8080](http://localhost:8080) in your web browser.

3. Register an account and start building your resume.

## Themes<a name="themes"></a>
Choose from a variety of resume themes to customize the look of your resume. Themes can be selected during the resume creation process.


