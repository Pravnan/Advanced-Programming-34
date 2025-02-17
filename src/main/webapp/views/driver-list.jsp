<%--
  Created by IntelliJ IDEA.
  User: Mirunan
  Date: 2025-01-31
  Time: 4:13 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link rel="stylesheet" href="/styles.css">
</head>
<body>

<!-- Debugging: Print out the driver object directly -->
<c:if test="${not empty drivers}">
    <h2>Drivers data passed:</h2>
    <pre>${drivers}</pre> <!-- Directly display the driver object -->
</c:if>

<a href="driver?action=add">Add New Driver</a>
</body>
</html>


<%@ page import="java.util.List" %>
<%@ page import="com.example.vehiclereservationapp.model.Admin" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Driver List</title>
</head>
<body>
<h1>Driver List</h1>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>PhoneNumber</th>
        <th>Role</th>
        <th>Email</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Admin> admins = (List<Admin>) request.getAttribute("admins");
        if (admins != null) {
            for (Admin admin : admins) {
    %>
    <tr>
        <td><%= admin.getAdminID() %></td>
        <td><%= admin.getUsername() %></td>
        <td><%= admin.getRole() %></td>
        <td><%= admin.getEmail() %></td>
        <td>
            <a href="admin?action=view&id=<%= admin.getAdminID() %>">View</a>
            <a href="admin?action=edit&id=<%= admin.getAdminID() %>">Edit</a>
            <a href="admin?action=delete&id=<%= admin.getAdminID() %>">Delete</a>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="5">No admins found.</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<a href="admin?action=add">Add New Admin</a>

</body>
</html>
