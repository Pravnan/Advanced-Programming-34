<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Admin</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 50px;
            text-align: center;
        }
        form {
            display: inline-block;
            text-align: left;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        input, select, button {
            display: block;
            width: 100%;
            margin: 10px 0;
            padding: 10px;
        }
    </style>
</head>
<body>
<h2>Add Admin</h2>
<form action="admin?action=add" method="post">
    <label for="username">Username:</label>
    <input type="text" id="Username" name="Username" required>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required>

    <label for="password">Password:</label>
    <input type="password" id="Password" name="Password" required>

    <label for="role">Role:</label>
    <select id="role" name="role" required>
        <option value="Admin">Admin</option>
        <option value="SuperAdmin">Super Admin</option>
        <option value="Support">Super Admin</option>
    </select>

    <button type="submit">Add Admin</button>
</form>
</body>
</html>
