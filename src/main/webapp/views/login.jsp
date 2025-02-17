<%--
  Created by IntelliJ IDEA.
  User: Mirunan
  Date: 2025-01-31
  Time: 4:18 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<h1>Login</h1>

<c:if test="${not empty errorMessage}">
    <p style="color:red;">${errorMessage}</p> <!-- Display error message -->
</c:if>

<form action="login" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>

    <label for="rememberMe">Remember Me</label>
    <input type="checkbox" id="rememberMe" name="rememberMe"><br>

    <button type="submit">Login</button>
</form>

<p>Don't have an account? <a href="register">Register here</a></p>

</body>
</html>

