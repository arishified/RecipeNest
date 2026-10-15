
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - RecipeNest</title>

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

        .login-card {
            background: white;
            border: 1px solid #e5e5dc;
            border-radius: 10px;
            padding: 30px;
        }

        h1 { color: #244c3b; margin-top: 0; }
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

        .error, .message {
            padding: 10px;
            border-radius: 6px;
        }

        .error {
            background: #fff0ed;
            color: #a12b1d;
            border: 1px solid #e8b5ac;
        }

        .message {
            background: #edf7ef;
            color: #245b36;
            border: 1px solid #b8d9bf;
        }

        .register-link {
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
            .login-card { padding: 22px; }
        }
    </style>
</head>
<body>

<header>
    <div class="logo">Recipe<span>Nest</span></div>
</header>

<main>
    <section class="login-card">
        <h1>Welcome back</h1>
        <p class="subtitle">Log in to share and discover recipes.</p>

        <% if (request.getAttribute("message") != null) { %>
            <p class="message">
                <%= org.apache.taglibs.standard.functions.Functions
                        .escapeXml(request.getAttribute("message").toString()) %>
            </p>
        <% } %>

        <% if (request.getAttribute("error") != null) { %>
            <p class="error">
                <%= org.apache.taglibs.standard.functions.Functions
                        .escapeXml(request.getAttribute("error").toString()) %>
            </p>
        <% } %>

        <form action="${pageContext.request.contextPath}/login"
              method="post">

            <label for="email">Email address</label>
            <input type="email" id="email" name="email"
                   maxlength="100" autocomplete="email" required>

            <label for="password">Password</label>
            <input type="password" id="password" name="password"
                   autocomplete="current-password" required>

            <button type="submit">Log In</button>
        </form>

        <p class="register-link">
            Don't have an account?
            <a href="${pageContext.request.contextPath}/register">
                Create one
            </a>
        </p>
    </section>
</main>

<footer>
    <p>&copy; 2026 RecipeNest - Online Recipe Sharing Platform</p>
</footer>

</body>
</html>
