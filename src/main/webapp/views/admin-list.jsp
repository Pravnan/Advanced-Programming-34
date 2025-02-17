<%@ page import="java.util.List" %>
<%@ page import="com.example.vehiclereservationapp.model.Admin" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Admin List</title>
</head>
<body>
<h1>Admin List</h1>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Username</th>
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
