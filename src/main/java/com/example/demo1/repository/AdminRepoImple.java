package com.example.demo1.repository;

import com.example.demo1.config.DBConfig;
import com.example.demo1.model.Admin;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminRepoImple implements AdminRepository {
    private PGSimpleDataSource dataSource;

    // Constructor to initialize the DataSource
    public AdminRepoImple() {
        this.dataSource = DBConfig.getDataSource();  // Use DBConfig to get the DataSource
    }

    @Override
    public void save(Admin admin) {
        String query = "INSERT INTO public.\"Admin\" (\"Username\", \"PasswordHash\", \"Role\") VALUES (?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, admin.getUsername());
            stmt.setString(2, admin.getPasswordHash());
            stmt.setString(3, admin.getRole());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Admin findById(int adminId) {
        String query = "SELECT * FROM public.\"Admin\" WHERE \"AdminID\" = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, adminId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Admin(
                        rs.getInt("AdminID"),
                        rs.getString("Username"),
                        rs.getString("PasswordHash"),
                        rs.getString("Role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Admin> findAll() {
        List<Admin> admins = new ArrayList<>();
        String query = "SELECT * FROM public.\"Admin\"";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                admins.add(new Admin(
                        rs.getInt("AdminID"),
                        rs.getString("Username"),
                        rs.getString("PasswordHash"),
                        rs.getString("Role")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public void update(Admin admin) {
        String query = "UPDATE public.\"Admin\" SET \"Username\" = ?, \"PasswordHash\" = ?, \"Role\" = ? WHERE \"AdminID\" = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, admin.getUsername());
            stmt.setString(2, admin.getPasswordHash());
            stmt.setString(3, admin.getRole());
            stmt.setInt(4, admin.getAdminID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int adminId) {
        String query = "DELETE FROM public.\"Admin\" WHERE \"AdminID\" = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, adminId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}