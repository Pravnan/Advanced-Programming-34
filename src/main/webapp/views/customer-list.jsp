<%@ page import="com.example.vehiclereservationapp.model.Customer" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer List</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<h1>Customer List</h1>

<%
    List<Customer> customerList = (List<Customer>) request.getAttribute("customers");
    if (customerList != null && !customerList.isEmpty()) {
%>
<h2>Customers:</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Address</th>
        <th>Actions</th> <!-- Added column for actions -->
    </tr>
    <%
        for (Customer customer : customerList) {
    %>
    <tr>
        <td><%= customer.getCustomerID() %></td>
        <td><%= customer.getName() %></td>
        <td><%= customer.getEmail() %></td>
        <td><%= customer.getPhoneNumber() %></td>
        <td><%= customer.getAddress() %></td>
        <td>
            <a href="customer?action=view&id=<%= customer.getCustomerID() %>">View</a>
            <a href="customer?action=edit&id=<%= customer.getCustomerID() %>">Edit</a>
            <a href="customer?action=delete&id=<%= customer.getCustomerID() %>">Delete</a>
        </td>
    </tr>
    <%
        }
    %>
</table>
<%
} else {
%>
<p>No customers found.</p>
<%
    }
%>

<a href="customer?action=add">Add New Customer</a>
</body>
</html>
