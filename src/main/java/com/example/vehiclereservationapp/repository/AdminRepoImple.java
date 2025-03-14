package com.example.vehiclereservationapp.repository;

import com.example.vehiclereservationapp.config.DBConfig;
import com.example.vehiclereservationapp.model.Admin;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminRepoImple implements AdminRepository {
    private PGSimpleDataSource dataSource;

    public AdminRepoImple() {
        this.dataSource = DBConfig.getDataSource();
    }

    //CRUD
    @Override
    public void save(Admin admin) {
        String query = "INSERT INTO public.\"Admin\" (\"Username\", \"PasswordHash\", \"Role\", \"Email\", \"IsVerified\", \"FailedAttempts\", \"IsLocked\", \"LastLogin\") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, admin.getUsername());
            stmt.setString(2, admin.getPasswordHash());
            stmt.setString(3, admin.getRole());
            stmt.setString(4, admin.getEmail());
            stmt.setBoolean(5, admin.isVerified());
            stmt.setInt(6, admin.getFailedAttempts());
            stmt.setBoolean(7, admin.isAccountLocked());
            stmt.setTimestamp(8, admin.getLastLogin());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Admin findById(int adminId) {
        String query = "SELECT * FROM public.\"Admin\" WHERE \"AdminID\" = ?";
        return getAdmin(query, adminId);
    }

    @Override
    public List<Admin> findAll() {
        List<Admin> admins = new ArrayList<>();
        String query = "SELECT * FROM public.\"Admin\"";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                admins.add(mapAdmin(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public void update(Admin admin) {
        String query = "UPDATE public.\"Admin\" SET \"Username\" = ?, \"PasswordHash\" = ?, \"Role\" = ?, \"Email\" = ?, \"IsVerified\" = ?, \"FailedAttempts\" = ?, \"IsLocked\" = ?, \"LastLogin\" = ? WHERE \"AdminID\" = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, admin.getUsername());
            stmt.setString(2, admin.getPasswordHash());
            stmt.setString(3, admin.getRole());
            stmt.setString(4, admin.getEmail());
            stmt.setBoolean(5, admin.isVerified());
            stmt.setInt(6, admin.getFailedAttempts());
            stmt.setBoolean(7, admin.isAccountLocked());
            stmt.setTimestamp(8, admin.getLastLogin());
            stmt.setInt(9, admin.getAdminID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int adminId) {
        String query = "DELETE FROM public.\"Admin\" WHERE \"AdminID\" = ?";
        executeUpdate(query, adminId);
    }





    @Override
    public void updateFailedAttempts(int adminId, int failedAttempts) {
        String query = "UPDATE public.\"Admin\" SET \"FailedAttempts\" = ? WHERE \"AdminID\" = ?";
        executeUpdate(query, failedAttempts, adminId);
    }

    @Override
    public void lockAccount(int adminId) {
        String query = "UPDATE public.\"Admin\" SET \"IsLocked\" = TRUE WHERE \"AdminID\" = ?";
        executeUpdate(query, adminId);
    }

    @Override
    public void unlockAccount(int adminId) {
        String query = "UPDATE public.\"Admin\" SET \"IsLocked\" = FALSE, \"FailedAttempts\" = 0 WHERE \"AdminID\" = ?";
        executeUpdate(query, adminId);
    }

    @Override
    public void setEmailVerified(int adminId, boolean isVerified) {
        String query = "UPDATE public.\"Admin\" SET \"IsVerified\" = ? WHERE \"AdminID\" = ?";
        executeUpdate(query, isVerified, adminId);
    }

    @Override
    public void updateLastLogin(int adminId) {
        String query = "UPDATE public.\"Admin\" SET \"LastLogin\" = ? WHERE \"AdminID\" = ?";
        executeUpdate(query, new Timestamp(System.currentTimeMillis()), adminId);
    }



    @Override
    public void resetFailedAttempts(String username) {
        String query = "UPDATE public.\"Admin\" SET \"FailedAttempts\" = 0 WHERE \"Username\" = ?";
        executeUpdate(query, username);
    }

    @Override
    public void updateVerifiedStatus(String username, boolean isVerified) {
        String query = "UPDATE public.\"Admin\" SET \"IsVerified\" = ? WHERE \"Username\" = ?";
        executeUpdate(query, isVerified, username);
    }

    private Admin mapAdmin(ResultSet rs) throws SQLException {
        return new Admin(
                rs.getInt("AdminID"),
                rs.getString("Username"),
                rs.getString("PasswordHash"),
                rs.getString("Role"),
                rs.getString("Email"),
                rs.getBoolean("IsVerified"),
                rs.getInt("FailedAttempts"),
                rs.getBoolean("IsLocked"),
                rs.getTimestamp("LastLogin")
        );
    }

    private Admin getAdmin(String query, Object param) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            if (param instanceof Integer) {
                stmt.setInt(1, (Integer) param);
            } else if (param instanceof String) {
                stmt.setString(1, (String) param);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapAdmin(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
