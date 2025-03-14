<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.vehiclereservationapp.model.Car" %>
<%
  List<Car> cars = (List<Car>) request.getAttribute("cars");
%>
<!DOCTYPE html>
<html>
<head>
  <title>Car List</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f9;
      margin: 0;
      padding: 0;
      color: #333;
    }

    h2 {
      text-align: center;
      margin: 30px 0 10px;
      color: #5a5a5a;
    }

    .container {
      max-width: 1440px;
      margin: 20px auto;
      padding: 20px;
    }

    .table-wrapper {
      background-color: #fff;
      padding: 30px;
      border-radius: 8px;
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
      overflow-x: auto;
    }

    table {
      width: 100%;
      border-collapse: collapse;
      min-width: 800px;
    }

    table thead {
      background-color: #4CAF50;
      color: #fff;
    }

    table th,
    table td {
      padding: 15px 12px;
      text-align: left;
    }

    table tbody tr {
      border-bottom: 1px solid #ddd;
      transition: background-color 0.2s ease;
    }

    table tbody tr:hover {
      background-color: #f1f1f1;
    }

    .actions a {
      display: inline-block;
      margin-right: 8px;
      padding: 8px 14px;
      font-size: 14px;
      border-radius: 5px;
      text-decoration: none;
      color: #fff;
      transition: background-color 0.3s ease;
    }

    .actions a.view {
      background-color: #3498db;
    }

    .actions a.view:hover {
      background-color: #2980b9;
    }

    .actions a.edit {
      background-color: #f39c12;
    }

    .actions a.edit:hover {
      background-color: #d68910;
    }

    .actions a.delete {
      background-color: #e74c3c;
    }

    .actions a.delete:hover {
      background-color: #c0392b;
    }

    .btn-add {
      display: inline-block;
      margin-top: 20px;
      padding: 12px 20px;
      background-color: #4CAF50;
      color: #fff;
      font-size: 16px;
      border-radius: 5px;
      text-decoration: none;
      transition: background-color 0.3s ease;
    }

    .btn-add:hover {
      background-color: #45a049;
    }

    /* Responsive Styles */
    @media (max-width: 768px) {
      table {
        min-width: 100%;
        display: block;
        overflow-x: auto;
      }

      table thead {
        display: none; /* Hide the table header on small screens */
      }

      table tbody tr {
        display: block;
        margin-bottom: 15px;
        border: 1px solid #ddd;
        border-radius: 8px;
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
      }

      table tbody td {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 10px;
        text-align: right;
        border-bottom: 1px solid #ddd;
      }

      table tbody td::before {
        content: attr(data-label); /* Use data-label attribute for column names */
        font-weight: bold;
        text-align: left;
        margin-right: 10px;
        color: #333;
      }

      table tbody td:last-child {
        border-bottom: none;
      }

      .actions {
        display: flex;
        justify-content: flex-end;
        gap: 8px;
      }

      .btn-add {
        width: 100%;
        text-align: center;
      }
    }
  </style>
</head>
<body>
<div class="container">
  <h2>List of Cars</h2>
  <div class="table-wrapper">
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Registration Number</th>
          <th>Make</th>
          <th>Model</th>
          <th>Year</th>
          <th>Availability</th>
          <th>Last Service Date</th>
          <th>Current Mileage</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <%
          if (cars != null) {
            for (Car car : cars) {
        %>
        <tr>
          <td data-label="ID"><%= car.getCarID() %></td>
          <td data-label="Registration Number"><%= car.getRegistrationNumber() %></td>
          <td data-label="Make"><%= car.getMake() %></td>
          <td data-label="Model"><%= car.getModel() %></td>
          <td data-label="Year"><%= car.getYear() %></td>
          <td data-label="Availability"><%= car.getAvailabilityStatus() %></td>
          <td data-label="Last Service Date"><%= car.getLastServiceDate() %></td>
          <td data-label="Current Mileage"><%= car.getCurrentMileage() %></td>
          <td class="actions" data-label="Actions">
            <a class="view" href="car?action=view&id=<%= car.getCarID() %>">View</a>
            <a class="edit" href="car?action=edit&id=<%= car.getCarID() %>">Edit</a>
            <a class="delete" href="car?action=delete&id=<%= car.getCarID() %>" onclick="return confirm('Are you sure?')">Delete</a>
          </td>
        </tr>
        <%
            }
          }
        %>
      </tbody>
    </table>
    <br>
    <a href="car?action=add" class="btn-add">Add New Car</a>
  </div>
</div>
</body>
</html>