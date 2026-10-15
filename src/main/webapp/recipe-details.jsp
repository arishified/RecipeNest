
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

        .recipe-card, .rating-card, .comment-card {
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

        .rating-card, .comment-card { margin-top: 25px; }

        .average-rating {
            font-size: 20px;
            font-weight: bold;
            color: #244c3b;
        }

        label {
            display: block;
            margin-top: 12px;
            font-weight: bold;
        }

        select, textarea {
            display: block;
            width: 100%;
            padding: 12px;
            margin: 8px 0 14px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font: inherit;
            background: white;
        }

        button {
            background: #244c3b;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 15px;
        }

        button:hover { background: #356b52; }

        .comment {
            background: #faf8f3;
            border: 1px solid #e5e5dc;
            border-radius: 8px;
            padding: 16px;
            margin-bottom: 12px;
            overflow-wrap: anywhere;
        }

        footer {
            background: #244c3b;
            color: white;
            text-align: center;
            padding: 18px;
            margin-top: 35px;
        }

        @media (max-width: 600px) {
            .recipe-card, .rating-card, .comment-card {
                padding: 20px;
            }
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

    <!-- Recipe ratings -->
    <section class="rating-card">
        <h2>Recipe Rating</h2>

        
<p class="average-rating">
    <c:choose>
        <c:when test="${ratingCount > 0}">
            <c:out value="${String.format('%.2f', averageRating)}" />
            / 5 ⭐
            (<c:out value="${ratingCount}" />
            <c:choose>
                <c:when test="${ratingCount == 1}">rating</c:when>
                <c:otherwise>ratings</c:otherwise>
            </c:choose>)
        </c:when>
        <c:otherwise>
            Not rated yet
        </c:otherwise>
    </c:choose>
</p>


        <p>Enjoyed this recipe? Give it a rating!</p>

        <form action="${pageContext.request.contextPath}/rate"
              method="post">

            <input type="hidden" name="recipeId"
                   value="${recipe.recipeId}">

            <label for="rating">Choose your rating</label>
            <select id="rating" name="rating" required>
                <option value="">Select stars</option>
                <option value="5">5 - Excellent ⭐⭐⭐⭐⭐</option>
                <option value="4">4 - Very good ⭐⭐⭐⭐</option>
                <option value="3">3 - Good ⭐⭐⭐</option>
                <option value="2">2 - Fair ⭐⭐</option>
                <option value="1">1 - Needs improvement ⭐</option>
            </select>

            <button type="submit">Submit Rating</button>
        </form>
    </section>

    <!-- Existing comments feature -->
    <section class="comment-card">
        <h2>Comments</h2>

        <c:if test="${empty comments}">
            <p>Be the first to comment on this recipe!</p>
        </c:if>

        <c:forEach var="comment" items="${comments}">
            <div class="comment">
                <p><c:out value="${comment.commentText}" /></p>
            </div>
        </c:forEach>

        <form action="${pageContext.request.contextPath}/comment"
              method="post">

            <input type="hidden" name="recipeId"
                   value="${recipe.recipeId}">

            <label for="commentText">Write a comment</label>
            <textarea id="commentText" name="commentText"
                      rows="4" maxlength="500" required></textarea>

            <button type="submit">Post Comment</button>
        </form>
    </section>
</main>

<footer>
    <p>&copy; 2026 RecipeNest - Online Recipe Sharing Platform</p>
</footer>

</body>
</html>
