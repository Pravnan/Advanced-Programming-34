<%@ page import="java.util.List" %>
<%@ page import="com.example.vehiclereservationapp.model.Admin" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Admin> admins = (List<Admin>) request.getAttribute("admins");
    int totalEntries = admins != null ? admins.size() : 0; // Total number of admins
    int entriesPerPage = 10; // Number of entries per page
    int totalPages = (int) Math.ceil((double) totalEntries / entriesPerPage); // Total pages
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
    List<Admin> paginatedList = admins != null ? admins.subList(startIndex, endIndex) : new ArrayList<>();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin List</title>
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

    <h1>Admin List</h1>

    <div class="container">
        <div class="table-wrapper">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Role</th>
                        <th>Email</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (paginatedList != null && !paginatedList.isEmpty()) {
                            for (Admin admin : paginatedList) {
                    %>
                    <tr>
                        <td><%= admin.getAdminID() %></td>
                        <td><%= admin.getUsername() %></td>
                        <td><%= admin.getRole() %></td>
                        <td><%= admin.getEmail() %></td>
                        <td class="actions">
                            <a class="view" href="admin?action=view&id=<%= admin.getAdminID() %>">View</a>
                            <a class="edit" href="admin?action=edit&id=<%= admin.getAdminID() %>">Edit</a>
                            <a class="delete" href="admin?action=delete&id=<%= admin.getAdminID() %>">Delete</a>
                        </td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="5">No admins found.</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>

            <!-- Pagination -->
            <div class="pagination">
                <%
                    for (int i = 1; i <= totalPages; i++) {
                        if (i == currentPage) {
                %>
                <a class="active" href="admin?action=list&page=<%= i %>"><%= i %></a>
                <%
                } else {
                %>
                <a href="admin?action=list&page=<%= i %>"><%= i %></a>
                <%
                        }
                    }
                %>
            </div>

            <a class="add-button" href="admin?action=add">Add New Admin</a>
        </div>
    </div>

</body>
</html>