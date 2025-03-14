package com.example.vehiclereservationapp.repository;

import com.example.vehiclereservationapp.config.DBConfig;
import com.example.vehiclereservationapp.model.Car;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarRepoImple implements CarRepository {

    private PGSimpleDataSource dataSource;

    public CarRepoImple() {
        this.dataSource = DBConfig.getDataSource();
    }

    @Override
    public Car findById(int id) {
        String sql = "SELECT * FROM \"Car\" WHERE \"CarID\" = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Car(
                            rs.getInt("CarID"),
                            rs.getString("RegistrationNumber"),
                            rs.getString("Make"),
                            rs.getString("Model"),
                            rs.getInt("Year"),
                            rs.getString("AvailabilityStatus"),
                            rs.getDate("LastServiceDate"),
                            rs.getInt("CurrentMileage")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Car> findAll() {
        ArrayList<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM \"Car\"";
        try (Connection connection = dataSource.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                cars.add(new Car(
                        rs.getInt("CarID"),
                        rs.getString("RegistrationNumber"),
                        rs.getString("Make"),
                        rs.getString("Model"),
                        rs.getInt("Year"),
                        rs.getString("AvailabilityStatus"),
                        rs.getDate("LastServiceDate"),
                        rs.getInt("CurrentMileage")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    @Override
    public void save(Car car) {
        String query = "INSERT INTO public.\"Car\" (\"RegistrationNumber\", \"Make\", \"Model\", \"Year\", \"AvailabilityStatus\", \"LastServiceDate\", \"CurrentMileage\") " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, car.getRegistrationNumber());
            stmt.setString(2, car.getMake());
            stmt.setString(3, car.getModel());
            stmt.setInt(4, car.getYear());
            stmt.setString(5, car.getAvailabilityStatus());
            stmt.setDate(6, new java.sql.Date(car.getLastServiceDate().getTime()));
            stmt.setInt(7, car.getCurrentMileage());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Car car) {
        String sql = "DELETE FROM \"Car\" WHERE \"CarID\" = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, car.getCarID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Car car) {
        String sql = "UPDATE \"Car\" SET \"RegistrationNumber\" = ?, \"Make\" = ?, \"Model\" = ?, \"Year\" = ?, \"AvailabilityStatus\" = ?, " +
                "\"LastServiceDate\" = ?, \"CurrentMileage\" = ? WHERE \"CarID\" = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, car.getRegistrationNumber());
            stmt.setString(2, car.getMake());
            stmt.setString(3, car.getModel());
            stmt.setInt(4, car.getYear());
            stmt.setString(5, car.getAvailabilityStatus());
            stmt.setDate(6, new java.sql.Date(car.getLastServiceDate().getTime()));
            stmt.setInt(7, car.getCurrentMileage());
            stmt.setInt(8, car.getCarID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
