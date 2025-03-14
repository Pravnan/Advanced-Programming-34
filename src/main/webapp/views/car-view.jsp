<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.vehiclereservationapp.model.Car" %>
<%
    Car car = (Car) request.getAttribute("car");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Car</title>
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

        .actions {
            display: flex;
            gap: 10px;
            margin-top: 20px;
        }

        .actions a {
            display: inline-block;
            padding: 12px 24px;
            background-color: #4CAF50;
            color: #fff;
            text-decoration: none;
            border-radius: 4px;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        .actions a:hover {
            background-color: #45a049;
        }

        @media (max-width: 768px) {
            .container {
                padding: 20px;
            }

            .details p {
                font-size: 14px;
            }

            .actions {
                flex-direction: column;
                gap: 10px;
            }

            .actions a {
                width: 100%;
                text-align: center;
            }
        }
    </style>
</head>
<body>

    <h2>Car Details</h2>

    <div class="container">
        <div class="details">
            <p><strong>ID:</strong> <%= car.getCarID() %></p>
            <p><strong>Registration Number:</strong> <%= car.getRegistrationNumber() %></p>
            <p><strong>Make:</strong> <%= car.getMake() %></p>
            <p><strong>Model:</strong> <%= car.getModel() %></p>
            <p><strong>Year:</strong> <%= car.getYear() %></p>
            <p><strong>Availability Status:</strong> <%= car.getAvailabilityStatus() %></p>
            <p><strong>Last Service Date:</strong> <%= car.getLastServiceDate() %></p>
            <p><strong>Current Mileage:</strong> <%= car.getCurrentMileage() %></p>
        </div>

        <div class="actions">
            <a href="car?action=edit&id=<%= car.getCarID() %>">Edit</a>
            <a href="car?action=list">Back to Car List</a>
        </div>
    </div>

</body>
</html>