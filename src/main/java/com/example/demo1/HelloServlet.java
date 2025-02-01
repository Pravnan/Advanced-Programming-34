package com.example.demo1;

import java.io.*;
import java.sql.*;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.postgresql.ds.PGSimpleDataSource;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    // Database connection details
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/VehicleReservationApp";
    private static final String DB_USER = "myuser";  // Replace with your username
    private static final String DB_PASSWORD = "mypassword";  // Replace with your password

    public void init() {
        message = "Hello World!";
    }

    // Initialize PostgreSQL DataSource directly
    private PGSimpleDataSource createDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(DB_URL);
        dataSource.setUser(DB_USER);
        dataSource.setPassword(DB_PASSWORD);
        return dataSource;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Get the database connection and check if it's working
        String dbMessage = testDbConnection();

        // Output the message to the browser
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<p>" + dbMessage + "</p>");
        out.println("</body></html>");
    }

    // Test the database connection
    private String testDbConnection() {
        // Create DataSource instance
        PGSimpleDataSource dataSource = createDataSource();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT 1")) {

            if (resultSet.next()) {
                return "Database connection successful!";
            } else {
                return "Database connection successful, but no data found.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Database connection failed: " + e.getMessage();
        }
    }

    public void destroy() {
        // Cleanup code (if any)
    }
}
