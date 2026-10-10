
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recipe Details - RecipeNest</title>

    <style>
        * { box-sizing: border-box; }

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #faf8f3;
            color: #26352d;
            line-height: 1.7;
        }

        header {
            background: #244c3b;
            color: white;
            padding: 18px 8%;
        }

        .logo { font-size: 25px; font-weight: bold; }
        .logo span { color: #f1c27d; }

        main {
            max-width: 900px;
            margin: 35px auto;
            padding: 0 20px;
        }

        .recipe-card {
            background: white;
            border: 1px solid #e5e5dc;
            border-radius: 10px;
            padding: 28px;
        }

        .category {
            color: #356b52;
            font-weight: bold;
            font-size: 14px;
            text-transform: uppercase;
        }

        h1 { margin-bottom: 8px; line-height: 1.3; }
        h2 { color: #244c3b; margin-top: 28px; }

        .description { color: #59665e; }

        .content {
            white-space: pre-line;
            overflow-wrap: anywhere;
        }

        .back-link {
            display: inline-block;
            margin-bottom: 18px;
            color: #244c3b;
            text-decoration: none;
            font-weight: bold;
        }

        .back-link:hover { text-decoration: underline; }

        footer {
            background: #244c3b;
            color: white;
            text-align: center;
            padding: 18px;
            margin-top: 35px;
        }

        @media (max-width: 600px) {
            .recipe-card { padding: 20px; }
            h1 { font-size: 28px; }
        }
    </style>
</head>
<body>

<header>
    <div class="logo">Recipe<span>Nest</span></div>
</header>

<main>
    <a class="back-link"
       href="${pageContext.request.contextPath}/">
        &larr; Back to recipes
    </a>

    <article class="recipe-card">
        <div class="category">
            <c:out value="${recipe.category}" />
        </div>

        <h1><c:out value="${recipe.title}" /></h1>

        <p class="description">
            <c:out value="${recipe.description}" />
        </p>

        <h2>Ingredients</h2>
        <div class="content"><c:out value="${recipe.ingredients}" /></div>

        <h2>Instructions</h2>
        <div class="content"><c:out value="${recipe.instructions}" /></div>
    </article>
</main>

<footer>
    <p>&copy; 2026 RecipeNest - Online Recipe Sharing Platform</p>
</footer>

</body>
</html>
