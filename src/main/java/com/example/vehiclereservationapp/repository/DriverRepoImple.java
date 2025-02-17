package com.example.vehiclereservationapp.repository;

import com.example.vehiclereservationapp.config.DBConfig;
import com.example.vehiclereservationapp.model.Driver;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverRepoImple implements DriverRepository {
    private PGSimpleDataSource dataSource;

    public DriverRepoImple() {
        this.dataSource = DBConfig.getDataSource();
    }


    //CRUD
    @Override
    public void save(Driver driver) {
        String query = "INSERT INTO public.\"Driver\" (\"Name\", \"LicenseNumber\", \"PhoneNumber\", \"AssignedCarID\", \"Status\", \"Username\", \"PasswordHash\", \"Role\", \"FailedAttempts\", \"IsVerified\") " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set values for the PreparedStatement
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getLicenseNumber());
            stmt.setString(3, driver.getPhoneNumber());
            stmt.setObject(4, driver.getAssignedCarID());  // This can be null
            stmt.setString(5, driver.getStatus());
            stmt.setString(6, driver.getUsername());
            stmt.setString(7, driver.getPasswordHash());
            stmt.setString(8, driver.getRole());
            stmt.setInt(9, driver.getFailedAttempts());
            stmt.setBoolean(10, driver.isVerified());

            // Execute the insert
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Driver findById(int driverId) {
        String query = "SELECT * FROM public.\"Driver\" WHERE \"DriverID\" = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, driverId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Driver(
                        rs.getInt("DriverID"),
                        rs.getString("Name"),
                        rs.getString("LicenseNumber"),
                        rs.getString("PhoneNumber"),
                        rs.getInt("AssignedCarID"),
                        rs.getString("Status"),
                        rs.getString("Username"),
                        rs.getString("PasswordHash"),
                        rs.getString("Role"),
                        rs.getInt("FailedAttempts"),
                        rs.getBoolean("IsVerified"),
                        rs.getBoolean("IsLocked"),
                        rs.getTimestamp("IsVerified")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Driver> findAll() {
        List<Driver> drivers = new ArrayList<>();
        String query = "SELECT * FROM public.\"Driver\"";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                drivers.add(new Driver(
                        rs.getInt("DriverID"),
                        rs.getString("Name"),
                        rs.getString("LicenseNumber"),
                        rs.getString("PhoneNumber"),
                        rs.getInt("AssignedCarID"),
                        rs.getString("Status"),
                        rs.getString("Username"),
                        rs.getString("PasswordHash"),
                        rs.getString("Role"),
                        rs.getInt("FailedAttempts"),
                        rs.getBoolean("IsVerified"),
                        rs.getBoolean("IsLocked"),
                        rs.getTimestamp("LastLogin")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }

    @Override
    public void update(Driver driver) {
        String query = "UPDATE public.\"Driver\" SET \"Name\" = ?, \"LicenseNumber\" = ?, \"PhoneNumber\" = ?, \"AssignedCarID\" = ?, \"Status\" = ?, \"Username\" = ?, \"PasswordHash\" = ?, \"Role\" = ?, \"FailedAttempts\" = ?, \"IsVerified\" = ? " +
                "WHERE \"DriverID\" = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getLicenseNumber());
            stmt.setString(3, driver.getPhoneNumber());
            stmt.setObject(4, driver.getAssignedCarID());  // This can be null
            stmt.setString(5, driver.getStatus());
            stmt.setString(6, driver.getUsername());
            stmt.setString(7, driver.getPasswordHash());
            stmt.setString(8, driver.getRole());
            stmt.setInt(9, driver.getFailedAttempts());
            stmt.setBoolean(10, driver.isVerified());
            stmt.setInt(11, driver.getDriverID());

            int affectedRows = stmt.executeUpdate();
            System.out.println("Updated Rows: " + affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int driverId) {
        String query = "DELETE FROM public.\"Driver\" WHERE \"DriverID\" = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, driverId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }






    @Override
    public void lockAccount(int driverId) {
        String query = "UPDATE public.\"Driver\" SET \"LastLogin\" = ? WHERE \"DriverID\" = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the current timestamp for last login
            stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            stmt.setInt(2, driverId);

            // Execute the update query
            int affectedRows = stmt.executeUpdate();
            System.out.println("Updated Rows: " + affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unlockAccount(int driverId) {
        String query = "UPDATE public.\"Driver\" SET \"IsLocked\" = FALSE WHERE \"DriverID\" = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, driverId);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Driver account unlocked successfully.");
            } else {
                System.out.println("No driver found with the given ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setEmailVerified(int driverId, boolean isVerified) {
        String query = "UPDATE public.\"Driver\" SET \"IsVerified\" = ? WHERE \"DriverID\" = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the value of IsVerified (true or false)
            stmt.setBoolean(1, isVerified);
            stmt.setInt(2, driverId);

            // Execute the update query
            int affectedRows = stmt.executeUpdate();
            System.out.println("Updated Rows: " + affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateLastLogin(int driverId) {
        String query = "UPDATE public.\"Driver\" SET \"LastLogin\" = ? WHERE \"DriverID\" = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the current timestamp for last login
            stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            stmt.setInt(2, driverId);

            // Execute the update query
            int affectedRows = stmt.executeUpdate();
            System.out.println("Updated Rows: " + affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Driver findByUsername(String username) {
        String query = "SELECT * FROM public.\"Driver\" WHERE \"Username\" = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Driver(
                        rs.getInt("DriverID"),
                        rs.getString("Name"),
                        rs.getString("LicenseNumber"),
                        rs.getString("PhoneNumber"),
                        rs.getInt("AssignedCarID"),
                        rs.getString("Status"),
                        rs.getString("Username"),
                        rs.getString("PasswordHash"),
                        rs.getString("Role"),
                        rs.getInt("FailedAttempts"),
                        rs.getBoolean("IsVerified"),
                        rs.getBoolean("IsLocked"),
                        rs.getTimestamp("IsVerified")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateFailedAttempts(String username, int failedAttempts) {
        String query = "UPDATE public.\"Driver\" SET \"FailedAttempts\" = ? WHERE \"Username\" = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, failedAttempts);
            stmt.setString(2, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void resetFailedAttempts(String username) {
        String query = "UPDATE public.\"Driver\" SET \"FailedAttempts\" = 0 WHERE \"Username\" = ?";

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
        String query = "UPDATE public.\"Driver\" SET \"IsVerified\" = ? WHERE \"Username\" = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setBoolean(1, isVerified);
            stmt.setString(2, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void executeUpdate(String query, Object... params) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for (int i = 0; i < params.length; i++) {
                if (params[i] instanceof Integer) {
                    stmt.setInt(i + 1, (Integer) params[i]);
                } else if (params[i] instanceof Boolean) {
                    stmt.setBoolean(i + 1, (Boolean) params[i]);
                } else if (params[i] instanceof Timestamp) {
                    stmt.setTimestamp(i + 1, (Timestamp) params[i]);
                }
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
