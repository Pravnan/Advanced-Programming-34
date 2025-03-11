<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.vehiclereservationapp.model.Admin" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<h1>Admin Dashboard</h1>

<%
    Admin admin = (Admin) session.getAttribute("loggedInAdmin");
    if (admin == null) {
        response.sendRedirect("views/login.jsp");
        return;
    }
%>

<h2>Welcome, <%= admin.getUsername() %>!</h2>

<ul>
    <li><a href="../customer?action=list">Manage Customers</a></li>
    <li><a href="../driver?action=list">Manage Drivers</a></li>
    <li><a href="../booking?action=list">Update Bookings</a></li>
    <li><a href="../booking?action=list">Manage Vehicles</a></li>
    <li><a href="../admin/reports">View Reports</a></li>
    <li><a href="admin?action=edit&id=<%= admin.getAdminID() %>">Update Profile</a></li>
    <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
</ul>

</body>
</html>
