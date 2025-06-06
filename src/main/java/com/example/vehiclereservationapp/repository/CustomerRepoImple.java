package com.example.vehiclereservationapp.repository;

import com.example.vehiclereservationapp.config.DBConfig;
import com.example.vehiclereservationapp.model.Customer;
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

    //CRUD

    @Override
    public void save(Customer customer) {
        String query = "INSERT INTO public.\"Customer\" (\"Name\", \"Email\", \"Address\", \"NIC\", \"PhoneNumber\", \"username\", \"passwordhash\", \"role\", \"isverified\", \"failedattempts\", \"islocked\") " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getNic());
            stmt.setString(5, customer.getPhoneNumber());
            stmt.setString(6, customer.getUsername());
            stmt.setString(7, customer.getPasswordHash());
            stmt.setString(8, customer.getRole());
            stmt.setBoolean(9, customer.isVerified());
            stmt.setInt(10, customer.getFailedAttempts());
            stmt.setBoolean(11, customer.isAccountLocked());

            stmt.executeUpdate();
            System.out.println(stmt);
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
                return mapResultSetToCustomer(rs);
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
                customers.add(mapResultSetToCustomer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }


    @Override
    public void update(Customer customer) {
        String query = "UPDATE public.\"Customer\" SET \"Name\" = ?, \"Email\" = ?, \"Address\" = ?, \"NIC\" = ?, \"PhoneNumber\" = ?, \"username\" = ?, \"role\" = ?, \"isverified\" = ?, \"failedattempts\" = ?, \"islocked\" = ? " +
                "WHERE \"CustomerID\" = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getNic());
            stmt.setString(5, customer.getPhoneNumber());
            stmt.setString(6, customer.getUsername());
            stmt.setString(7, customer.getRole());
            stmt.setBoolean(8, customer.isVerified());
            stmt.setInt(9, customer.getFailedAttempts());
            stmt.setBoolean(10, customer.isAccountLocked());
            stmt.setInt(11, customer.getCustomerID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int customerId) {
        String query = "DELETE FROM public.\"Customer\" WHERE \"CustomerID\" = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, customerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer findByUsername(String username) {
        String query = "SELECT * FROM public.\"Customer\" WHERE \"username\" = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToCustomer(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void resetFailedAttempts(String username) {
        String query = "UPDATE public.\"Customer\" SET \"failedattempts\" = 0 WHERE \"username\" = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateVerifiedStatus(String username, boolean isVerified) {
        String query = "UPDATE public.\"Customer\" SET \"isverified\" = ? WHERE \"username\" = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setBoolean(1, isVerified);
            stmt.setString(2, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFailedAttempts(int customerId, int failedAttempts) {
        String query = "UPDATE public.\"Customer\" SET \"failedattempts\" = ? WHERE \"CustomerID\" = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, failedAttempts);
            stmt.setInt(2, customerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lockAccount(int customerId) {
        String query = "UPDATE public.\"Customer\" SET \"islocked\" = true WHERE \"CustomerID\" = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, customerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unlockAccount(int customerId) {
        String query = "UPDATE public.\"Customer\" SET \"islocked\" = false WHERE \"CustomerID\" = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, customerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setEmailVerified(int customerId, boolean isVerified) {
        String query = "UPDATE public.\"Customer\" SET \"isverified\" = ? WHERE \"CustomerID\" = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setBoolean(1, isVerified);  // Set the new verification status (TRUE or FALSE)
            stmt.setInt(2, customerId);  // Use the customerId to find the specific customer
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateLastLogin(int customerId) {
        String query = "UPDATE public.\"Customer\" SET \"lastlogin\" = CURRENT_TIMESTAMP WHERE \"CustomerID\" = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, customerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Customer mapResultSetToCustomer(ResultSet rs) throws SQLException {
        return new Customer(
                rs.getInt("CustomerID"),
                rs.getString("Name"),
                rs.getString("Email"),
                rs.getString("Address"),
                rs.getString("NIC"),
                rs.getString("PhoneNumber"),
                rs.getTimestamp("CreatedAt"),
                rs.getString("username"),
                rs.getString("passwordhash"),
                rs.getString("role"),
                rs.getBoolean("isverified"),
                rs.getInt("failedattempts"),
                rs.getBoolean("islocked"),
                rs.getTimestamp("lastlogin")
        );
    }

}
