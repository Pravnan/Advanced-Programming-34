<%--
  Created by IntelliJ IDEA.
  User: Mirunan
  Date: 2025-01-27
  Time: 12:17 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Customer</title>
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
        input[type="email"] {
            padding: 12px 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            width: 100%;
            box-sizing: border-box;
            transition: border-color 0.3s ease, box-shadow 0.3s ease;
        }

        input[type="text"]:focus,
        input[type="email"]:focus {
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
            input[type="email"] {
                font-size: 14px;
            }
        }
    </style>
</head>
<body>

    <h1>Edit Customer</h1>

    <div class="container">

        <form action="customer?action=update" method="post">
            <input type="hidden" name="id" value="${customer.customerID}">

            <div>
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" value="${customer.name}" required>
            </div>

            <div>
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${customer.email}" required>
            </div>

            <div>
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" value="${customer.address}" required>
            </div>

            <div>
                <label for="nic">NIC:</label>
                <input type="text" id="nic" name="nic" value="${customer.nic}" required>
            </div>

            <div>
                <label for="phoneNumber">Phone Number:</label>
                <input type="text" id="phoneNumber" name="phoneNumber" value="${customer.phoneNumber}" required>
            </div>

            <button type="submit">Update Customer</button>
        </form>

        <a class="back-button" href="customer?action=list">Back to List</a>

    </div>

</body>
</html>

