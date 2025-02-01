package com.example.demo1.repository;

import com.example.demo1.model.Customer;
import com.example.demo1.config.DBConfig;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepoImple implements CustomerRepository {
    private PGSimpleDataSource dataSource;

    // Constructor to initialize the DataSource
    public CustomerRepoImple() {
        this.dataSource = DBConfig.getDataSource();  // Use DBConfig to get the DataSource
    }

    @Override
    public void save(Customer customer) {
        String query = "INSERT INTO public.\"Customer\" (\"Name\", \"Email\", \"Address\", \"NIC\", \"PhoneNumber\") " +
                "VALUES (?, ?, ?, ?, ?)";


        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Setting values for the PreparedStatement
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getNic());
            stmt.setString(5, customer.getPhoneNumber());

            // Executing the insert
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer findById(int customerId) {
        String query = "SELECT * FROM public.\"Customer\" WHERE \"CustomerID\" = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Address"),
                        rs.getString("NIC"),
                        rs.getString("PhoneNumber"),
                        rs.getTimestamp("CreatedAt")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM public.\"Customer\"";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Address"),
                        rs.getString("NIC"),
                        rs.getString("PhoneNumber"),
                        rs.getTimestamp("CreatedAt")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Customers fetched: " + customers.size());  // Log the number of customers
        return customers;
    }


    @Override
    public void update(Customer customer) {
        String query = "UPDATE public.\"Customer\" SET \"Name\" = ?, \"Email\" = ?, \"Address\" = ?, \"NIC\" = ?, \"PhoneNumber\" = ? " +
                "WHERE \"CustomerID\" = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getNic());
            stmt.setString(5, customer.getPhoneNumber());
            stmt.setInt(6, customer.getCustomerID());

            int affectedRows = stmt.executeUpdate();  // Execute the update
            System.out.println("Updated Rows: " + affectedRows);  // Debug output
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int customerId) {
        String query = "DELETE FROM public.\"Customer\" WHERE CustomerID = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, customerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
