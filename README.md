# RecipeNest — Recipe Sharing Platform

RecipeNest is a Java-based web application where users can explore recipes and view their ingredients and cooking instructions.

## Features

- Display recipes stored in a MySQL database
- View recipe details, ingredients, and instructions
- Navigate between the recipe list and individual recipe pages
- Modular Java code using Servlets, JSP, JDBC, and DAO classes

## Technology Stack

- Java 21
- JSP and Jakarta Servlets
- JDBC
- MySQL
- Apache Maven
- Apache Tomcat 10.1
- HTML and CSS

## Requirements

- JDK 21
- Apache Maven
- Apache Tomcat 10.1
- MySQL Server 8.0 or compatible
- Visual Studio Code or another Java IDE

## Database Setup

1. Start MySQL Server.
2. Create a database named `project_db`.
3. Create the `users`, `recipes`, `ratings`, and `comments` tables.
4. Add sample recipes to the `recipes` table.

The database tables use foreign keys to connect users, recipes, ratings, and comments.

## Configuration

Set the `RECIPENEST_DB_PASSWORD` environment variable to your local MySQL password before running the application.

Do not commit database passwords or other secrets to GitHub.

## Run the Project

1. Configure MySQL and create the database tables.
2. Set the `RECIPENEST_DB_PASSWORD` environment variable.
3. Build the project:

   ```bash
   mvn clean package
   ```

4. Deploy `target/recipenest.war` to Apache Tomcat's `webapps` directory.
5. Start Tomcat.
6. Open `http://localhost:8080/recipenest/` in your browser.

## Project Structure

```text
RecipeNest/
├── src/
│   └── main/
│       ├── java/com/recipenest/
│       │   ├── dao/
│       │   ├── model/
│       │   ├── servlet/
│       │   └── util/
│       └── webapp/
│           ├── index.jsp
│           ├── recipe-details.jsp
│           └── WEB-INF/web.xml
├── .gitignore
├── README.md
└── pom.xml
```

## Current Status

The homepage displays recipes from MySQL, and users can open individual recipe pages to view ingredients and cooking instructions.

## Author

RecipeNest — Academic Java Web Development Project