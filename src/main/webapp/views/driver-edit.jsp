
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Driver</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<h1>Edit Driver</h1>
<form action="driver?action=update" method="post">
    <input type="hidden" name="id" value="${driver.driverID}">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${driver.name}" required><br>

    <label for="licenseNumber">License Number:</label>
    <input type="text" id="licenseNumber" name="licenseNumber" value="${driver.licenseNumber}" required><br>

    <label for="phoneNumber">Phone Number:</label>
    <input type="text" id="phoneNumber" name="phoneNumber" value="${driver.phoneNumber}" required><br>

    <label for="assignedCarID">Assigned Car ID:</label>
    <input type="number" id="assignedCarID" name="assignedCarID" value="${driver.assignedCarID}"><br>

    <label for="status">Status:</label>
    <select id="status" name="status">
        <option value="Active" ${driver.status == 'Active' ? 'selected' : ''}>Active</option>
        <option value="Inactive" ${driver.status == 'Inactive' ? 'selected' : ''}>Inactive</option>
    </select><br>

    <button type="submit">Update Driver</button>
</form>
<a href="driver?action=list">Back to List</a>
</body>
</html>

