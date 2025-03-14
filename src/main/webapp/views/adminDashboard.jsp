<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.vehiclereservationapp.model.Admin" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    Admin admin = (Admin) session.getAttribute("loggedInAdmin");
    if (admin == null) {
        response.sendRedirect("views/login.jsp");
        return;
    }
%>

<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 0;
        }

        h2 {
            text-align: center;
            margin-top: 30px;
            color: #5a5a5a;
        }

        .container {
            max-width: 1440px;
            margin: 30px auto;
            background-color: #fff;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }

        p {
            margin: 15px 0;
            line-height: 1.5;
            font-size: 16px;
        }

        strong {
            color: #5a5a5a;
        }

        .card-container {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
            margin-top: 40px;
        }

        .card {
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
            text-align: center;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            cursor: pointer;
            text-decoration: none;
            color: #333;
        }

        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.15);
        }

        .card h3 {
            margin-bottom: 10px;
            color: #4CAF50;
            font-size: 20px;
        }

        .card p {
            font-size: 14px;
            color: #777;
            margin-bottom: 15px;
        }

        .logout-card {
            border-top: 4px solid #e74c3c;
        }

        .logout-card h3 {
            color: #e74c3c;
        }
    </style>
</head>
<body>

    <h2>Welcome, <%= admin.getUsername() %>!</h2>

    <div class="container">
        <p><strong>Admin ID:</strong> <%= admin.getAdminID() %></p>
        <p><strong>Email:</strong> <%= admin.getEmail() != null ? admin.getEmail() : "N/A" %></p>

        <div class="card-container">
            <a class="card" href="<%= request.getContextPath() %>/customer?action=list">
                <h3>Manage Customers</h3>
                <p>View and manage customer accounts.</p>
            </a>

            <a class="card" href="<%= request.getContextPath() %>/driver?action=list">
                <h3>Manage Drivers</h3>
                <p>View and manage driver profiles and statuses.</p>
            </a>

            <a class="card" href="<%= request.getContextPath() %>/booking?action=list">
                <h3>Update Bookings</h3>
                <p>Review and manage all booking requests.</p>
            </a>

            <a class="card" href="<%= request.getContextPath() %>/car?action=list">
                <h3>Manage Vehicles</h3>
                <p>Update and manage vehicle inventory.</p>
            </a>

            <a class="card" href="<%= request.getContextPath() %>/admin/reports">
                <h3>View Reports</h3>
                <p>Access detailed reports and analytics.</p>
            </a>

            <a class="card" href="<%= request.getContextPath() %>/admin?action=edit&id=<%= admin.getAdminID() %>">
                <h3>Update Profile</h3>
                <p>Modify your profile and account settings.</p>
            </a>

            <a class="card logout-card" href="<%= request.getContextPath() %>/logout">
                <h3>Logout</h3>
                <p>Sign out of your administrator account securely.</p>
            </a>
        </div>
    </div>

</body>
</html>
