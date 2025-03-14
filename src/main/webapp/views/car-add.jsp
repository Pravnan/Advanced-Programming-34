<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Car</title>
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
        input[type="date"] {
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
        input[type="date"]:focus {
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
            input[type="date"] {
                font-size: 14px;
            }
        }
    </style>
</head>
<body>

    <h2>Add a New Car</h2>

    <div class="container">
        <form action="car" method="post">
            <input type="hidden" name="action" value="add">

            <div>
                <label>Registration Number:</label>
                <input type="text" name="registrationNumber" required>
            </div>

            <div>
                <label>Make:</label>
                <input type="text" name="make" required>
            </div>

            <div>
                <label>Model:</label>
                <input type="text" name="model" required>
            </div>

            <div>
                <label>Year:</label>
                <input type="number" name="year" required>
            </div>

            <div>
                <label>Availability Status:</label>
                <input type="text" name="availabilityStatus" required>
            </div>

            <div>
                <label>Last Service Date (YYYY-MM-DD):</label>
                <input type="date" name="lastServiceDate" required>
            </div>

            <div>
                <label>Current Mileage:</label>
                <input type="number" name="currentMileage" required>
            </div>

            <button type="submit">Add Car</button>
        </form>

        <a class="back-button" href="car?action=list">Back to Car List</a>
    </div>

</body>
</html>