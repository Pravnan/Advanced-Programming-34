<%@ page import="com.example.demo1.model.Customer" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer List</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<h1>Customer List</h1>

<!-- Debugging: Print out the customers object directly -->
<c:if test="${not empty customers}">
    <h2>Customers data passed:</h2>
    <pre>${customers}</pre> <!-- Directly display the customer object -->
</c:if>

<a href="customer?action=add">Add New Customer</a>
</body>
</html>
