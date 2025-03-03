<%@ page import="com.example.vehiclereservationapp.model.Driver" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Driver List</title>
</head>
<body>
<h2>Driver List</h2>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>License Number</th>
        <th>Phone Number</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Driver> drivers = (List<Driver>) request.getAttribute("drivers");  // Retrieve the list of drivers from the request
        for (Driver driver : drivers) {  // Loop through each driver in the list
    %>
    <tr>
        <td><%= driver.getDriverID() %></td>
        <td><%= driver.getName() %></td>
        <td><%= driver.getLicenseNumber() %></td>
        <td><%= driver.getPhoneNumber() %></td>
        <td><%= driver.getStatus() %></td>
        <td>
            <a href="driver?action=view&id=<%= driver.getDriverID() %>">View</a> |
            <a href="driver?action=edit&id=<%= driver.getDriverID() %>">Edit</a> |
            <a href="driver?action=delete&id=<%= driver.getDriverID() %>">Delete</a>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

<br>
<a href="driver?action=add">Add New Driver</a>
</body>
</html>
