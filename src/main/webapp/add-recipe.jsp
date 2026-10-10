<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Add a Recipe - RecipeNest</title>

    <style>
        body {
            margin: 0;
            padding: 30px 15px;
            font-family: Arial, sans-serif;
            background-color: #faf8f3;
            color: #26352d;
        }

        .container {
            max-width: 600px;
            margin: 20px auto;
            padding: 30px;
            background: white;
            border: 1px solid #e5e5dc;
            border-radius: 8px;
        }

        h1 {
            color: #244c3b;
            margin-top: 0;
        }

        label {
            display: block;
            margin-top: 18px;
            margin-bottom: 6px;
            font-weight: bold;
        }

        input, textarea, select {
            width: 100%;
            padding: 11px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-family: Arial, sans-serif;
            font-size: 14px;
            box-sizing: border-box;
        }

        textarea {
            min-height: 100px;
            resize: vertical;
        }

        button {
            margin-top: 22px;
            padding: 12px 20px;
            border: none;
            border-radius: 5px;
            background: #244c3b;
            color: white;
            font-size: 15px;
            cursor: pointer;
        }

        button:hover {
            background: #356b52;
        }

        .back-link {
            display: inline-block;
            margin-top: 18px;
            color: #244c3b;
        }
    </style>
</head>

<body>
    <div class="container">
        <h1>Share Your Recipe</h1>
        <p>Add your favourite dish to the RecipeNest collection.</p>

        <form action="${pageContext.request.contextPath}/add-recipe"
              method="post">

            <label for="title">Recipe Name *</label>
            <input type="text" id="title" name="title"
                   maxlength="150" required>

            <label for="description">Description</label>
            <textarea id="description" name="description"
                      maxlength="500"></textarea>

            <label for="category">Category</label>
            <select id="category" name="category">
                <option value="Main Course">Main Course</option>
                <option value="Rice">Rice</option>
                <option value="Dessert">Dessert</option>
                <option value="Snacks">Snacks</option>
                <option value="Beverages">Beverages</option>
                <option value="Other">Other</option>
            </select>

            <label for="ingredients">Ingredients *</label>
            <textarea id="ingredients" name="ingredients"
                      required></textarea>

            <label for="instructions">Cooking Instructions *</label>
            <textarea id="instructions" name="instructions"
                      required></textarea>

            <button type="submit">Save Recipe</button>
        </form>

        <a class="back-link"
           href="${pageContext.request.contextPath}/">
            ← Back to recipes
        </a>
    </div>
</body>
</html>