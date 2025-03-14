<%@ page import="com.example.vehiclereservationapp.model.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Customer> customerList = (List<Customer>) request.getAttribute("customers");
    int totalEntries = customerList != null ? customerList.size() : 0;
    int entriesPerPage = 10; // Number of entries per page
    int totalPages = (int) Math.ceil((double) totalEntries / entriesPerPage); // Calculate total pages
    int currentPage = 1; // Default to page 1

    // Get the current page from the request parameter
    String pageParam = request.getParameter("page");
    if (pageParam != null && !pageParam.isEmpty()) {
        currentPage = Integer.parseInt(pageParam);
    }

    // Calculate start and end indices for the current page
    int startIndex = (currentPage - 1) * entriesPerPage;
    int endIndex = Math.min(startIndex + entriesPerPage, totalEntries);

    // Get the sublist for the current page
    List<Customer> paginatedList = customerList != null ? customerList.subList(startIndex, endIndex) : new ArrayList<>();
%>
<html>
<head>
    <title>Customer List</title>
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

        .action-buttons a {
            display: inline-block;
            margin-right: 8px;
            padding: 8px 14px;
            font-size: 14px;
            border-radius: 5px;
            text-decoration: none;
            color: #fff;
            transition: background-color 0.3s ease;
        }

        .action-buttons a.view {
            background-color: #3498db;
        }

        .action-buttons a.view:hover {
            background-color: #2980b9;
        }

        .action-buttons a.edit {
            background-color: #f39c12;
        }

        .action-buttons a.edit:hover {
            background-color: #d68910;
        }

        .action-buttons a.delete {
            background-color: #e74c3c;
        }

        .action-buttons a.delete:hover {
            background-color: #c0392b;
        }

        .add-button {
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

        .add-button:hover {
            background-color: #45a049;
        }

        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        .pagination a {
            margin: 0 5px;
            padding: 8px 12px;
            text-decoration: none;
            color: #4CAF50;
            border: 1px solid #4CAF50;
            border-radius: 4px;
            transition: background-color 0.3s ease, color 0.3s ease;
        }

        .pagination a:hover {
            background-color: #4CAF50;
            color: #fff;
        }

        .pagination a.active {
            background-color: #4CAF50;
            color: #fff;
        }

        @media (max-width: 768px) {
            table {
                min-width: 600px;
            }

            .add-button {
                width: 100%;
                text-align: center;
            }
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Customer List</h1>

    <div class="table-wrapper">
        <%
            if (paginatedList != null && !paginatedList.isEmpty()) {
        %>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Customer customer : paginatedList) {
            %>
            <tr>
                <td><%= customer.getCustomerID() %></td>
                <td><%= customer.getName() %></td>
                <td><%= customer.getEmail() %></td>
                <td><%= customer.getPhoneNumber() %></td>
                <td><%= customer.getAddress() %></td>
                <td class="action-buttons">
                    <a class="view" href="customer?action=view&id=<%= customer.getCustomerID() %>">View</a>
                    <a class="edit" href="customer?action=edit&id=<%= customer.getCustomerID() %>">Edit</a>
                    <a class="delete" href="customer?action=delete&id=<%= customer.getCustomerID() %>">Delete</a>
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
        <p>No customers found.</p>
        <%
            }
        %>

        <!-- Pagination -->
        <div class="pagination">
            <%
                for (int i = 1; i <= totalPages; i++) {
                    if (i == currentPage) {
            %>
            <a class="active" href="customer?action=list&page=<%= i %>"><%= i %></a>
            <%
            } else {
            %>
            <a href="customer?action=list&page=<%= i %>"><%= i %></a>
            <%
                    }
                }
            %>
        </div>

        <a class="add-button" href="customer?action=add">Add New Customer</a>
    </div>
</div>

</body>
</html>