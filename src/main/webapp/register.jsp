
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Account - RecipeNest</title>

    <style>
        * { box-sizing: border-box; }

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #faf8f3;
            color: #26352d;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        header {
            background: #244c3b;
            color: white;
            padding: 18px 8%;
        }

        .logo { font-size: 25px; font-weight: bold; }
        .logo span { color: #f1c27d; }

        main {
            width: 100%;
            max-width: 480px;
            margin: auto;
            padding: 35px 20px;
        }

        .register-card {
            background: white;
            border: 1px solid #e5e5dc;
            border-radius: 10px;
            padding: 30px;
        }

        h1 {
            color: #244c3b;
            margin-top: 0;
        }

        .subtitle { color: #59665e; }

        label {
            display: block;
            margin-top: 16px;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 12px;
            margin-top: 7px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 16px;
        }

        button {
            width: 100%;
            margin-top: 22px;
            padding: 13px;
            background: #244c3b;
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
        }

        button:hover { background: #356b52; }

        .error {
            background: #fff0ed;
            color: #a12b1d;
            border: 1px solid #e8b5ac;
            padding: 10px;
            border-radius: 6px;
        }

        .login-link {
            margin-top: 20px;
            text-align: center;
        }

        a { color: #244c3b; }

        footer {
            background: #244c3b;
            color: white;
            text-align: center;
            padding: 18px;
            margin-top: 30px;
        }

        @media (max-width: 500px) {
            .register-card { padding: 22px; }
        }
    </style>
</head>
<body>

<header>
    <div class="logo">Recipe<span>Nest</span></div>
</header>

<main>
    <section class="register-card">
        <h1>Create your account</h1>
        <p class="subtitle">Join RecipeNest and share your favourite recipes.</p>

        <% if (request.getAttribute("error") != null) { %>
            <p class="error">
                <%= org.apache.taglibs.standard.functions.Functions
                        .escapeXml(request.getAttribute("error").toString()) %>
            </p>
        <% } %>

        <form action="${pageContext.request.contextPath}/register"
              method="post">

            <label for="name">Full name</label>
            <input type="text" id="name" name="name"
                   maxlength="100" autocomplete="name" required>

            <label for="email">Email address</label>
            <input type="email" id="email" name="email"
                   maxlength="100" autocomplete="email" required>

            <label for="password">Password</label>
            <input type="password" id="password" name="password"
                   minlength="8" maxlength="72"
                   autocomplete="new-password" required>

            <button type="submit">Create Account</button>
        </form>

        <p class="login-link">
            Already have an account?
            <a href="${pageContext.request.contextPath}/login">Log in</a>
        </p>
    </section>
</main>

<footer>
    <p>&copy; 2026 RecipeNest - Online Recipe Sharing Platform</p>
</footer>

</body>
</html>
