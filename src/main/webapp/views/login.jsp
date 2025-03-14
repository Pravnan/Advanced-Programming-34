<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);
            background-size: 400% 400%;
            animation: gradientBG 15s ease infinite;
            overflow: hidden;
        }

        @keyframes gradientBG {
            0% {
                background-position: 0% 50%;
            }
            50% {
                background-position: 100% 50%;
            }
            100% {
                background-position: 0% 50%;
            }
        }

        .login-container {
            background-color: rgba(255, 255, 255, 0.9);
            padding: 40px 30px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
            box-sizing: border-box;
            text-align: center;
            position: relative;
            z-index: 1;
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
            background-color: #4CAF50;
            color: #fff;
            border: none;
            font-size: 16px;
            cursor: pointer;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }

        button[type="submit"]:hover {
            background-color: #3b783d;
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
            color: #4CAF50;
            text-decoration: none;
        }

        p a:hover {
            text-decoration: underline;
        }

        .welcome-text {
            position: absolute;
            top: 100px; /* Adjusted to bring it lower */
            left: 50%;
            transform: translateX(-50%);
            font-size: 48px; /* Increased font size */
            font-weight: bold;
            color: #fff;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
            z-index: 0;
            white-space: nowrap; /* Prevents text from wrapping */
        }

        @media (max-width: 768px) {
            .welcome-text {
                font-size: 36px; /* Adjusted for smaller screens */
                top: 80px; /* Adjusted for smaller screens */
            }
        }

        @media (max-width: 480px) {
            .login-container {
                padding: 30px 20px;
            }

            button[type="submit"] {
                font-size: 14px;
                padding: 12px 16px;
            }

            .welcome-text {
                font-size: 28px; /* Adjusted for very small screens */
                top: 60px; /* Adjusted for very small screens */
            }
        }
    </style>
</head>
<body>

    <!-- Welcome Text -->
    <div class="welcome-text">Welcome to Mega City Cab</div>

    <!-- Login Container -->
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

        <p> Don't have an account? <a href="customer?action=add">Register here</a></p>

    </div>

</body>
</html>