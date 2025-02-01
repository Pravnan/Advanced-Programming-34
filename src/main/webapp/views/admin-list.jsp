<%--
  Created by IntelliJ IDEA.
  User: Mirunan
  Date: 2025-01-29
  Time: 2:40 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Admin List</h1>

    <!-- Debugging: Print out the customers object directly -->
    <c:if test="${not empty admins}">
        <h2>Customers data passed:</h2>
        <pre>${admins}</pre> <!-- Directly display the customer object -->
    </c:if>
</body>
</html>
