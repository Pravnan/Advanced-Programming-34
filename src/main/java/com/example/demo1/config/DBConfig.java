package com.example.demo1.config;

import org.postgresql.ds.PGSimpleDataSource;

public class DBConfig {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/VehicleReservationApp";
    private static final String DB_USER = "myuser";  // Replace with your username
    private static final String DB_PASSWORD = "mypassword";  // Replace with your password

    // Method to return a PGSimpleDataSource instance
    public static PGSimpleDataSource getDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(DB_URL);
        dataSource.setUser(DB_USER);
        dataSource.setPassword(DB_PASSWORD);
        return dataSource;
    }
}
