<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Driver Details</title>
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

        .details {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }

        .details p {
            margin: 0;
            font-size: 16px;
            color: #555;
        }

        .details strong {
            color: #333;
            font-weight: bold;
        }

        .back-button {
            display: inline-block;
            margin-top: 20px;
            padding: 12px 24px;
            background-color: #4CAF50;
            color: #fff;
            text-decoration: none;
            border-radius: 4px;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        .back-button:hover {
            background-color: #45a049;
        }

        @media (max-width: 768px) {
            .container {
                padding: 20px;
            }

            .details p {
                font-size: 14px;
            }
        }
    </style>
</head>
<body>

    <h1>Driver Details</h1>

    <div class="container">
        <div class="details">
            <p><strong>ID:</strong> ${driver.driverID}</p>
            <p><strong>Name:</strong> ${driver.name}</p>
            <p><strong>License Number:</strong> ${driver.licenseNumber}</p>
            <p><strong>Phone Number:</strong> ${driver.phoneNumber}</p>
            <p><strong>Assigned Car ID:</strong> ${driver.assignedCarID}</p>
            <p><strong>Status:</strong> ${driver.status}</p>
        </div>

        <a class="back-button" href="driver?action=list">Back to List</a>
    </div>

</body>
</html>