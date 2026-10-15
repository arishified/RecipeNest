# 🍲 RecipeNest — Online Recipe Sharing Platform

RecipeNest is a Java-based web application that allows users to discover recipes, share their own dishes, and interact with a food-loving community. The application uses a MySQL database to store user accounts, recipes, ratings, and comments.

## 🌐 Live Demo

- **Live Website:** https://recipenest-production-8167.up.railway.app/
- **GitHub Repository:** https://github.com/arishified/RecipeNest

## 📌 Project Overview

Finding recipes across different websites and personal notes can be inconvenient. RecipeNest provides a centralized platform where users can explore recipes, read cooking instructions, and contribute their own recipes.

The project demonstrates the development of a database-driven web application using Java, JSP, Servlets, JDBC, and MySQL.

## ✨ Features

- **User Registration:** Create a new account.
- **User Login and Logout:** Authenticate users and manage their sessions.
- **Browse Recipes:** View recipes available on the platform.
- **Search and Filter:** Search recipes and filter them by category.
- **Recipe Details:** View descriptions, ingredients, and cooking instructions.
- **Add Recipes:** Submit recipes with titles, descriptions, ingredients, instructions, and categories.
- **Ratings and Comments:** Interact with recipes through ratings and comments.
- **Database Integration:** Store and retrieve application data using MySQL.
- **Responsive Interface:** A clean interface with a green-and-cream colour scheme.

## 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| Java 21 | Backend programming |
| JSP | Dynamic web pages |
| Jakarta Servlets | Handle HTTP requests and application logic |
| JDBC | Connect Java to MySQL |
| MySQL 8 | Relational database |
| Maven | Dependency management and project building |
| Apache Tomcat 10.1 | Java web application server |
| HTML and CSS | User interface |
| Railway | Cloud deployment and database hosting |
| Git and GitHub | Version control and source code hosting |

## 🏗️ System Architecture

RecipeNest follows a layered architecture to separate application responsibilities.

1. **Presentation Layer:** JSP pages display the interface and recipe information.
2. **Servlet Layer:** Servlets process user requests, validate inputs, and coordinate application operations.
3. **DAO Layer:** Data Access Objects execute SQL queries and manage database operations.
4. **Model Layer:** Java classes represent application entities such as users and recipes.
5. **Database Layer:** MySQL stores users, recipes, ratings, and comments.

### Request Flow

`Browser → JSP / Servlet → DAO → JDBC → MySQL`

The result is returned to the user through the web interface.

## 🗄️ Database Design

The application uses four main relational tables.

| Table | Purpose |
|---|---|
| `users` | Stores user accounts and roles |
| `recipes` | Stores recipe details and the associated user |
| `ratings` | Stores user ratings for recipes |
| `comments` | Stores comments associated with recipes and users |

**Database relationships:**

- One user can submit multiple recipes.
- Users can rate recipes, with a unique constraint preventing duplicate ratings by the same user for a recipe.
- Users can comment on recipes.
- Foreign keys connect recipes, ratings, and comments to their related records.

## ⚙️ Prerequisites

To run the project locally, install:

- JDK 21
- Apache Maven 3.9 or later
- MySQL Server 8.0 or later
- Apache Tomcat 10.1
- Git

## 🚀 Run the Project Locally

### 1. Clone the repository

```bash
git clone https://github.com/arishified/RecipeNest.git
cd RecipeNest
```

### 2. Create the database

Open MySQL Workbench and execute:

```sql
CREATE DATABASE project_db;
USE project_db;
```

Create the following tables:

```sql
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) DEFAULT 'USER'
);

CREATE TABLE recipes (
    recipe_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    title VARCHAR(150) NOT NULL,
    description VARCHAR(500),
    ingredients TEXT NOT NULL,
    instructions TEXT NOT NULL,
    category VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE ratings (
    rating_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    recipe_id INT NOT NULL,
    rating INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id),
    UNIQUE (user_id, recipe_id)
);

CREATE TABLE comments (
    comment_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    recipe_id INT NOT NULL,
    comment_text VARCHAR(500) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id)
);
```

### 3. Configure database credentials

For local development, set the `RECIPENEST_DB_PASSWORD` environment variable to your local MySQL password.

In Windows PowerShell, for the current terminal session:

```powershell
$env:RECIPENEST_DB_PASSWORD = "YOUR_LOCAL_MYSQL_PASSWORD"
```

Replace the placeholder with your own local database password. Never commit actual passwords to GitHub.

The application is configured to use the local `project_db` database when Railway's MySQL environment variables are not present.

### 4. Build the application

From the project directory, run:

```bash
mvn clean package
```

Maven generates the WAR file at:

`target/recipenest.war`

### 5. Deploy to Apache Tomcat

Copy `target/recipenest.war` into the Tomcat `webapps` directory and start Tomcat.

Open the application at:

http://localhost:8080/recipenest/

## ☁️ Cloud Deployment

RecipeNest is deployed on Railway with a MySQL database.

The hosted application uses environment variables for its database connection settings, including:

- `MYSQLHOST`
- `MYSQLPORT`
- `MYSQLDATABASE`
- `MYSQLUSER`
- `MYSQLPASSWORD`

Database credentials should be configured through the hosting platform's environment variables rather than stored in source code.

## 🔐 Security Considerations

- Passwords are hashed during registration using BCrypt.
- Authenticated actions use session-based user identification.
- User-provided inputs are validated before database operations.
- Database credentials are managed through environment variables.
- Database relationships are enforced using primary keys, foreign keys, and unique constraints.

## 🧪 Testing

The following application workflows have been tested:

- User registration and login
- User logout and session handling
- Adding recipes
- Viewing the recipe listing and details
- Saving comments and ratings
- Connecting the application to the hosted MySQL database
- Accessing the deployed application through its public URL

## 🔮 Future Enhancements

- Edit and delete recipes
- Recipe image uploads
- User profiles and personal recipe collections
- Improved recipe discovery and recommendations
- Additional input validation and security hardening
- Automated testing and deployment workflows

## 🎓 Academic Project

RecipeNest was developed as a Java web development project to demonstrate object-oriented programming, web application development, database connectivity, SQL operations, and cloud deployment.

**Developer:** Aarish Alam

**GitHub:** [arishified](https://github.com/arishified)

---

*RecipeNest — Discover Recipes. Share Your Passion.*
