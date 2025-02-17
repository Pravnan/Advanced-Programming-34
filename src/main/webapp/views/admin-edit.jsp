<%--
  Created by IntelliJ IDEA.
  User: Mirunan
  Date: 2025-01-29
  Time: 2:39 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Admin</title>
</head>
<body>
<h1>Edit Admin</h1>

<c:if test="${not empty admin}">
    <form action="admin?action=update" method="post">
        <input type="hidden" name="adminID" value="${admin.adminID}">

        <label>Username:</label>
        <input type="text" name="username" value="${admin.username}" required><br><br>

        <label>Password:</label>
        <input type="password" name="passwordHash" value="${admin.passwordHash}" required><br><br>

        <label>Role:</label>
        <select name="role">
            <option value="Admin" ${admin.role == 'Admin' ? 'selected' : ''}>Admin</option>
            <option value="Manager" ${admin.role == 'Manager' ? 'selected' : ''}>Manager</option>
        </select><br><br>

        <button type="submit">Update Admin</button>
    </form>
</c:if>

<br>
<a href="admin?action=list">Back to Admin List</a>
</body>
</html>