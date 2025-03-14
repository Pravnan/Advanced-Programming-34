package com.example.vehiclereservationapp.repository;

import com.example.vehiclereservationapp.config.DBConfig;
import com.example.vehiclereservationapp.model.Payment;
import org.postgresql.ds.PGSimpleDataSource;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepoImple implements PaymentRepository {
    private PGSimpleDataSource dataSource;

    public PaymentRepoImple(){
        dataSource = DBConfig.getDataSource();
    }

    @Override
    public Payment findById(int id) {
        String sql = "SELECT * FROM \"Payment\" WHERE \"PaymentID\" = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToPayment(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Payment> findAll() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM \"Payment\" ORDER BY \"PaymentID\" ASC";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                payments.add(mapResultSetToPayment(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    @Override
    public void save(Payment payment) {
        String insertPaymentSQL = "INSERT INTO \"Payment\" (\"BookingID\", \"PaymentDate\", \"Amount\", \"PaymentMethod\", \"PaymentReference\") VALUES (?, ?, ?, ?, ?)";
        String updateBookingSQL = "UPDATE \"Booking\" SET \"PaymentStatus\" = 'PAID' WHERE \"BookingID\" = ?";

        try (Connection conn = dataSource.getConnection()) {
            // Enable transaction handling
            conn.setAutoCommit(false);

            try (PreparedStatement paymentStmt = conn.prepareStatement(insertPaymentSQL, Statement.RETURN_GENERATED_KEYS)) {
                // Insert Payment
                paymentStmt.setInt(1, payment.getBookingID());
                paymentStmt.setTimestamp(2, payment.getPaymentDate());
                paymentStmt.setBigDecimal(3, payment.getAmount());
                paymentStmt.setString(4, payment.getPaymentMethod());
                paymentStmt.setString(5, payment.getPaymentReference());

                int rowsAffected = paymentStmt.executeUpdate();

                if (rowsAffected > 0) {
                    ResultSet rs = paymentStmt.getGeneratedKeys();
                    if (rs.next()) {
                        payment.setPaymentID(rs.getInt(1));
                    }

                    // Update Booking Status to 'PAID'
                    try (PreparedStatement bookingStmt = conn.prepareStatement(updateBookingSQL)) {
                        bookingStmt.setInt(1, payment.getBookingID());
                        bookingStmt.executeUpdate();
                    }
                }

                // Commit the transaction
                conn.commit();
            } catch (SQLException e) {
                conn.rollback(); // Rollback in case of error
                e.printStackTrace();
            } finally {
                conn.setAutoCommit(true); // Reset auto-commit
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Payment payment) {
        String sql = "UPDATE \"Payment\" SET \"BookingID\" = ?, \"PaymentDate\" = ?, \"Amount\" = ?, \"PaymentMethod\" = ?, \"PaymentReference\" = ? WHERE \"PaymentID\" = ?";

        try (Connection conn =  dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, payment.getBookingID());
            stmt.setTimestamp(2, payment.getPaymentDate());
            stmt.setBigDecimal(3, payment.getAmount());
            stmt.setString(4, payment.getPaymentMethod());
            stmt.setString(5, payment.getPaymentReference());
            stmt.setInt(6, payment.getPaymentID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Payment payment) {
        String sql = "DELETE FROM \"Payment\" WHERE \"PaymentID\" = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, payment.getPaymentID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Payment mapResultSetToPayment(ResultSet rs) throws SQLException {
        return new Payment(
                rs.getInt("paymentID"),
                rs.getInt("bookingID"),
                rs.getTimestamp("paymentDate"),
                rs.getBigDecimal("amount"),
                rs.getString("paymentMethod"),
                rs.getString("paymentReference")
        );
    }
}
