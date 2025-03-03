package com.example.vehiclereservationapp.repository;

import com.example.vehiclereservationapp.config.DBConfig;
import com.example.vehiclereservationapp.model.Booking;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingRepoImpl implements BookingRepository {

    private PGSimpleDataSource dataSource;

    public BookingRepoImpl() {
        this.dataSource = DBConfig.getDataSource();
    }

    @Override
    public void save(Booking booking) {
        String sql = "INSERT INTO public.\"Booking\" (\"CustomerID\", \"Destination\", \"BookingDate\", " +
                "\"TotalAmount\", \"PaymentStatus\", \"PickupLocation\", \"DropoffLocation\", \"RequestedTime\") VALUES (?, ?,  CURRENT_TIMESTAMP, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, booking.getCustomerID());  // CustomerID
            stmt.setString(2, booking.getDestination());  // Destination
            stmt.setBigDecimal(3, booking.getTotalAmount());  // TotalAmount
            stmt.setString(4, booking.getPaymentStatus());  // PaymentStatus
            stmt.setString(5, booking.getPickupLocation());  // PickupLocation
            stmt.setString(6, booking.getDropoffLocation());  // DropoffLocation
            stmt.setTimestamp(7, booking.getRequestedTime());  // RequestedTime

            stmt.executeUpdate();

            // Retrieve generated BookingID
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    booking.setBookingID(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Booking findById (int bookingID) {
        String sql = "SELECT * FROM \"Booking\" WHERE \"BookingID\" = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookingID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapBooking(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Booking> findAll() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM public.\"Booking\"";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Mapping the ResultSet to Booking object
                Booking booking = mapBooking(rs);
                bookings.add(booking);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return bookings;
    }

    @Override
    public void update(Booking booking) {
        String sql = "UPDATE \"Booking\" SET \"Destination\" = ?, \"PickupLocation\" = ?, \"DropoffLocation\" = ?, \"RequestedTime\" = ? WHERE \"BookingID\" = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, booking.getDestination());
            stmt.setString(2, booking.getPickupLocation());
            stmt.setString(3, booking.getDropoffLocation());
            stmt.setTimestamp(4, booking.getRequestedTime());
            stmt.setInt(5, booking.getBookingID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int bookingID) {
        String sql = "DELETE FROM \"Booking\" WHERE \"BookingID\" = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookingID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Booking> getBookingslistByCustomerID(int customerID) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM \"Booking\" WHERE \"CustomerID\" = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                bookings.add(mapBooking(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public List<Booking> getUnassignedBookings() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM \"Booking\" b WHERE NOT EXISTS (SELECT 1 FROM \"BookingCar\" bc WHERE bc.\"BookingID\" = b.\"BookingID\")";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                bookings.add(mapBooking(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public boolean assignCarAndDriver(int bookingID, int carID, int driverID) {
        String sqlBookingCar = "INSERT INTO \"BookingCar\" (\"BookingID\", \"CarID\") VALUES (?, ?)";
        String sqlDriver = "UPDATE \"Driver\" SET \"AssignedCarID\" = ?, \"Status\" = 'Assigned' WHERE \"DriverID\" = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmtBookingCar = conn.prepareStatement(sqlBookingCar);
             PreparedStatement stmtDriver = conn.prepareStatement(sqlDriver)) {

            conn.setAutoCommit(false);  // Start transaction

            stmtBookingCar.setInt(1, bookingID);
            stmtBookingCar.setInt(2, carID);
            int rowsAffectedBookingCar = stmtBookingCar.executeUpdate();

            stmtDriver.setInt(1, carID);
            stmtDriver.setInt(2, driverID);
            int rowsAffectedDriver = stmtDriver.executeUpdate();

            if (rowsAffectedBookingCar > 0 && rowsAffectedDriver > 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Booking mapBooking(ResultSet rs) throws SQLException {
        Booking booking = new Booking();
        booking.setBookingID(rs.getInt("BookingID"));
        booking.setCustomerID(rs.getInt("CustomerID"));
        booking.setDestination(rs.getString("Destination"));
        booking.setBookingDate(rs.getTimestamp("BookingDate"));
        booking.setTotalAmount(rs.getBigDecimal("TotalAmount"));
        booking.setPaymentStatus(rs.getString("PaymentStatus"));
        booking.setPickupLocation(rs.getString("PickupLocation"));
        booking.setDropoffLocation(rs.getString("DropoffLocation"));
        booking.setRequestedTime(rs.getTimestamp("RequestedTime"));
        return booking;
    }
}
