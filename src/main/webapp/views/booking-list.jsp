<%@ page import="com.example.vehiclereservationapp.model.Booking" %>
<%@ page import="com.example.vehiclereservationapp.model.Admin" %> <!-- Import Admin class -->
<%@ page import="com.example.vehiclereservationapp.model.Customer" %> <!-- Import Customer class -->
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking List</title>
    <link rel="stylesheet" href="/styles.css">

    <!-- Updated inline style to match the provided example and make it responsive -->
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            color: #333;
        }

        h1 {
            text-align: center;
            margin: 30px 0 10px;
            color: #5a5a5a;
        }

        .container {
            max-width: 2000px;
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
    <h1>Bookings</h1>

    <div class="table-wrapper">
        <%
            List<Booking> bookingList = (List<Booking>) request.getAttribute("bookings");
            Admin loggedInAdmin = (Admin) session.getAttribute("loggedInAdmin");
            Customer loggedInCustomer = (Customer) session.getAttribute("loggedInCustomer");

            if (bookingList != null && !bookingList.isEmpty()) {
        %>
        <table>
            <thead>
                <tr>
                    <th>Booking ID</th>
                    <th>Customer ID</th>
                    <th>Destination</th>
                    <th>Booking Date</th>
                    <th>Requested Time</th>
                    <th>Total Amount</th>
                    <th>Payment Status</th>
                    <th>Pickup Location</th>
                    <th>Dropoff Location</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Booking booking : bookingList) {
                %>
                <tr>
                    <td data-label="Booking ID"><%= booking.getBookingID() %></td>
                    <td data-label="Customer ID"><%= booking.getCustomerID() %></td>
                    <td data-label="Destination"><%= booking.getDestination() %></td>
                    <td data-label="Booking Date"><%= booking.getBookingDate() %></td>
                    <td data-label="Requested Time"><%= booking.getRequestedTime() %></td>
                    <td data-label="Total Amount"><%= booking.getTotalAmount() %></td>
                    <td data-label="Payment Status"><%= booking.getPaymentStatus() %></td>
                    <td data-label="Pickup Location"><%= booking.getPickupLocation() %></td>
                    <td data-label="Dropoff Location"><%= booking.getDropoffLocation() %></td>
                    <td class="actions" data-label="Actions">
                        <% if (loggedInAdmin != null) { %>
                            <a class="view" href="booking?action=bAview&id=<%= booking.getBookingID() %>">View</a>
                            <a class="edit" href="booking?action=bAedit&id=<%= booking.getBookingID() %>">Edit</a>
                            <a class="delete" href="booking?action=bAdelete&id=<%= booking.getBookingID() %>">Delete</a>
                        <% } else if (loggedInCustomer != null && booking.getCustomerID() == loggedInCustomer.getCustomerID()) { %>
                            <a class="view" href="booking?action=view&id=<%= booking.getBookingID() %>">View</a>
                            <a class="edit" href="booking?action=edit&id=<%= booking.getBookingID() %>">Edit</a>
                            <a class="delete" href="booking?action=delete&id=<%= booking.getBookingID() %>">Delete</a>
                        <% } %>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
            } else {
        %>
        <p>No bookings found.</p>
        <%
            }
        %>

        <a href="booking?action=add" class="btn-add">Add New Booking</a>

    </div>
</div>

</body>
</html>