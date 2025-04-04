<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Driver</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            margin-top: 30px;
            color: #5a5a5a;
        }

        .container {
            max-width: 750px;
            margin: 30px auto;
            background-color: #fff;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }

        label {
            font-weight: bold;
            margin-bottom: 5px;
            color: #555;
        }

        input[type="text"],
        input[type="number"],
        select {
            padding: 12px 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            width: 100%;
            box-sizing: border-box;
            transition: border-color 0.3s ease, box-shadow 0.3s ease;
        }

        input[type="text"]:focus,
        input[type="number"]:focus,
        select:focus {
            border-color: #4CAF50;
            box-shadow: 0 0 5px rgba(76, 175, 80, 0.5);
            outline: none;
        }

        button[type="submit"] {
            padding: 14px 20px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            font-size: 16px;
            cursor: pointer;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }

        button[type="submit"]:hover {
            background-color: #45a049;
        }

        .back-button {
            display: inline-block;
            margin-top: 20px;
            padding: 12px 24px;
            background-color: #ccc;
            color: #333;
            text-decoration: none;
            border-radius: 4px;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        .back-button:hover {
            background-color: #bbb;
        }

        @media (max-width: 768px) {
            .container {
                padding: 20px;
            }

            input[type="text"],
            input[type="number"],
            select {
                font-size: 14px;
            }
        }
    </style>
</head>
<body>

    <h1>Add Driver</h1>

    <div class="container">
        <form action="driver?action=add" method="post">
            <div>
                <label for="Name">Name:</label>
                <input type="text" id="Name" name="Name" required>
            </div>

            <div>
                <label for="LicenseNumber">License Number:</label>
                <input type="text" id="LicenseNumber" name="LicenseNumber" required>
            </div>

            <div>
                <label for="PhoneNumber">Phone Number:</label>
                <input type="text" id="PhoneNumber" name="PhoneNumber" required>
            </div>

            <div>
                <label for="AssignedCarID">Assigned Car ID:</label>
                <input type="number" id="AssignedCarID" name="AssignedCarID">
            </div>

            <div>
                <label for="Status">Status:</label>
                <select id="Status" name="Status">
                    <option value="Active">Active</option>
                    <option value="Inactive">Inactive</option>
                </select>
            </div>

            <div>
                <label for="Username">Username:</label>
                <input type="text" id="Username" name="Username">
            </div>

            <div>
                <label for="PasswordHash">Password:</label>
                <input type="text" id="PasswordHash" name="PasswordHash">
            </div>

            <div>
                <label for="Role">Role:</label>
                <input type="text" id="Role" name="Role">
            </div>

            <button type="submit">Add Driver</button>
        </form>

        <a class="back-button" href="driver?action=list">Back to List</a>
    </div>

</body>
</html>