
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RecipeNest - Recipe Sharing Platform</title>

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #faf8f3;
            color: #26352d;
            line-height: 1.6;
        }

        header {
            background-color: #244c3b;
            color: white;
            padding: 18px 8%;
        }

        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            flex-wrap: wrap;
            gap: 12px;
        }

        .logo {
            font-size: 25px;
            font-weight: bold;
        }

        .logo span {
            color: #f1c27d;
        }

        nav a {
            color: white;
            text-decoration: none;
            margin-left: 18px;
        }

        nav a:hover {
            color: #f1c27d;
        }

        .hero {
            background-color: #e8efe7;
            padding: 55px 8%;
        }

        .hero h1 {
            max-width: 650px;
            font-size: 42px;
            line-height: 1.2;
            margin-top: 0;
        }

        .hero p {
            max-width: 600px;
            color: #59665e;
            margin-bottom: 24px;
        }

        .button {
            display: inline-block;
            background-color: #244c3b;
            color: white;
            padding: 10px 18px;
            border-radius: 5px;
            text-decoration: none;
        }

        .button:hover {
            background-color: #356b52;
        }

        main {
            padding: 40px 8%;
        }

        .recipe-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
            gap: 20px;
        }

        .recipe-card {
            background-color: white;
            padding: 20px;
            border: 1px solid #e5e5dc;
            border-radius: 8px;
        }

        .category {
            color: #356b52;
            font-size: 13px;
            font-weight: bold;
        }

        .recipe-card h3 {
            margin-bottom: 8px;
        }

        .recipe-card p {
            color: #59665e;
            overflow-wrap: anywhere;
        }

        footer {
            background-color: #244c3b;
            color: white;
            text-align: center;
            padding: 18px;
            margin-top: 20px;
        }

        @media (max-width: 600px) {
            .hero h1 {
                font-size: 32px;
            }

            nav a {
                margin-left: 0;
                margin-right: 12px;
            }
        }
    </style>
</head>

<body>

    <header>
        <div class="navbar">
            <div class="logo">Recipe<span>Nest</span></div>

            <nav>
                <a href="${pageContext.request.contextPath}/">Home</a>
                <a href="#recipes">Recipes</a>
                <a href="#about">About</a>
                <a href="${pageContext.request.contextPath}/add-recipe.jsp">Add Recipe</a>
            </nav>
        </div>
    </header>

    <section class="hero">
        <h1>Discover Recipes. Share Your Passion.</h1>

        <p>
            Explore homemade favourites, discover new dishes,
            and share your recipes with a community that loves food.
        </p>

        <a class="button" href="#recipes">Explore Recipes</a>
    </section>

    <main>
        <section id="recipes">
            <h2>Explore Our Recipes</h2>
            <p>Find inspiration for your next meal.</p>
            
            <div style="margin: 20px 0; display: flex; gap: 10px; flex-wrap: wrap;">
    <input
        type="text"
        id="recipeSearch"
        placeholder="Search recipes..."
        style="padding: 12px; width: 250px; max-width: 100%; border: 1px solid #ccc; border-radius: 5px;"
    >

    <select
        id="categoryFilter"
        style="padding: 12px; border: 1px solid #ccc; border-radius: 5px;"
    >
        <option value="">All Categories</option>
    </select>
</div>

            <div class="recipe-grid">

                <c:forEach var="recipe" items="${recipes}">
                    <article class="recipe-card">

                        <div class="category">
                            <c:out value="${recipe.category}" />
                        </div>

   <h3>
    <c:out value="${recipe.title}" />
</h3>

<a href="${pageContext.request.contextPath}/recipe?id=${recipe.recipeId}"
   style="display: inline-block; margin-bottom: 16px; color: #176b50; text-decoration: underline; cursor: pointer;">
    View Recipe →
</a>

                        <p>
                            <c:out value="${recipe.description}" />
                        </p>

                    </article>
                </c:forEach>

            </div>

            <c:if test="${empty recipes}">
                <p>No recipes are available yet.</p>
            </c:if>
        </section>

        <section id="about" style="margin-top: 40px;">
            <h2>About RecipeNest</h2>
            <p>
                RecipeNest brings food lovers together to discover,
                create, and share recipes.
            </p>
        </section>
    </main>

    <footer>
        <p>&copy; 2026 RecipeNest - Online Recipe Sharing Platform</p>
    </footer>

    <script>
    const searchInput = document.getElementById("recipeSearch");
    const categoryFilter = document.getElementById("categoryFilter");
    const recipeCards = document.querySelectorAll(".recipe-card");

    // Add categories automatically from the displayed recipes
    recipeCards.forEach(function(card) {
        const categoryElement = card.querySelector(".category");

        if (categoryElement) {
            const category = categoryElement.textContent.trim();

            if (category !== "") {
                const exists = Array.from(categoryFilter.options).some(
                    option => option.value.toLowerCase() === category.toLowerCase()
                );

                if (!exists) {
                    const option = document.createElement("option");
                    option.value = category;
                    option.textContent = category;
                    categoryFilter.appendChild(option);
                }
            }
        }
    });

    function filterRecipes() {
        const searchText = searchInput.value.toLowerCase().trim();
        const selectedCategory = categoryFilter.value.toLowerCase();

        recipeCards.forEach(function(card) {
            const title = card.querySelector("h3").textContent.toLowerCase();
            const description = card.querySelector("p").textContent.toLowerCase();
            const category = card.querySelector(".category").textContent.toLowerCase();

            const matchesSearch =
                title.includes(searchText) ||
                description.includes(searchText) ||
                category.includes(searchText);

            const matchesCategory =
                selectedCategory === "" ||
                category.trim() === selectedCategory;

            card.style.display =
                matchesSearch && matchesCategory ? "" : "none";
        });
    }

    searchInput.addEventListener("input", filterRecipes);
    categoryFilter.addEventListener("change", filterRecipes);
</script>
    
</body>
</html>
