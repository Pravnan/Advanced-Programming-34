<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .login-container {
            background-color: #fff;
            padding: 40px 30px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
            box-sizing: border-box;
            text-align: center;
        }

        h2 {
            margin-bottom: 20px;
            color: #333;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            text-align: left;
            font-weight: bold;
            margin-bottom: 5px;
            color: #555;
        }

        input[type="text"],
        input[type="password"] {
            padding: 12px 15px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            box-sizing: border-box;
            transition: border-color 0.3s ease, box-shadow 0.3s ease;
        }

        input[type="text"]:focus,
        input[type="password"]:focus {
            border-color: #007bff;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
            outline: none;
        }

        .checkbox-container {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
            font-size: 14px;
            color: #555;
        }

        .checkbox-container input[type="checkbox"] {
            margin-right: 8px;
        }

        button[type="submit"] {
            padding: 14px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            font-size: 16px;
            cursor: pointer;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }

        button[type="submit"]:hover {
            background-color: #0056b3;
        }

        .error-message {
            color: #ff3333;
            font-weight: bold;
            margin-bottom: 20px;
        }

        p {
            margin-top: 20px;
            font-size: 14px;
            color: #555;
        }

        p a {
            color: #007bff;
            text-decoration: none;
        }

        p a:hover {
            text-decoration: underline;
        }

        @media (max-width: 480px) {
            .login-container {
                padding: 30px 20px;
            }

            button[type="submit"] {
                font-size: 14px;
                padding: 12px 16px;
            }
        }
    </style>
</head>
<body>

    <div class="login-container">

        <h2>Login</h2>

        <c:if test="${not empty errorMessage}">
            <p class="error-message">${errorMessage}</p>
        </c:if>

        <form action="login" method="post">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>

            <div class="checkbox-container">
                <input type="checkbox" id="rememberMe" name="rememberMe">
                <label for="rememberMe">Remember Me</label>
            </div>

            <button type="submit">Login</button>
        </form>

        <p>Don't have an account? <a href="customer?action=add">Register here</a></p>

    </div>

</body>
</html>