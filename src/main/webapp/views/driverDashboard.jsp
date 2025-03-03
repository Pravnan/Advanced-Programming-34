<%@ page import="com.example.vehiclereservationapp.model.Driver" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Driver Dashboard</title>
    <link rel="stylesheet" href="../styles/dashboard.css">
</head>
<body>
<header>
    <h1>Welcome, <%= ((Driver) session.getAttribute("loggedInDriver")).getName() %></h1>
    <nav>
        <ul>
            <li><a href="updateProfile.jsp">Update Profile</a></li>
            <li><a href="assignedCar.jsp">Assigned Car</a></li>
            <li><a href="logout">Logout</a></li>
        </ul>
    </nav>
</header>
<main>
    <section>
        <h2>Driver Information</h2>
        <p><strong>Name:</strong> <%= ((Driver) session.getAttribute("loggedInDriver")).getName() %></p>
        <p><strong>License Number:</strong> <%= ((Driver) session.getAttribute("loggedInDriver")).getLicenseNumber() %></p>
        <p><strong>Phone:</strong> <%= ((Driver) session.getAttribute("loggedInDriver")).getPhoneNumber() %></p>
    </section>

    <section>
        <h2>Assigned Car</h2>
        <% if (((Driver) session.getAttribute("loggedInDriver")).getAssignedCarID() != 0) { %>
        <p>You have an assigned car. Check details <a href="assignedCar.jsp">here</a>.</p>
        <% } else { %>
        <p>No car assigned yet.</p>
        <% } %>
    </section>
</main>
</body>
</html>
