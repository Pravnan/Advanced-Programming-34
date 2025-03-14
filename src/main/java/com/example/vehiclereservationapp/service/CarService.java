package com.example.vehiclereservationapp.service;

import com.example.vehiclereservationapp.model.Car;
import com.example.vehiclereservationapp.repository.CarRepoImple;
import com.example.vehiclereservationapp.repository.CarRepository;

import java.util.List;

public class CarService {

    private CarRepository carRepository;

    public CarService() {
        this.carRepository = new CarRepoImple();
    }



    // Example method to get all cars
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    // Example method to get a car by its ID
    public Car getCarById(int carID) {
        return carRepository.findById(carID);
    }

    // Method to add a new car
    public void addCar(Car car) {
        // You could add additional business logic here, like validation
        if (car.getMake() == null || car.getModel() == null || car.getRegistrationNumber() == null) {
            throw new IllegalArgumentException("Car make, model, and registration number are required.");
        }
        carRepository.save(car);
    }

    // Method to update an existing car
    public void updateCar(Car car) {
        // You could add additional checks or validation here
        if (car.getCarID() <= 0) {
            throw new IllegalArgumentException("Invalid car ID.");
        }
        carRepository.update(car);
    }

    // Method to delete a car by its ID
    public void deleteCar(int carID) {
        // Optional: Check if the car exists before attempting deletion
        Car car = carRepository.findById(carID);
        if (car != null) {
            carRepository.delete(car);
        } else {
            throw new IllegalArgumentException("Car with ID " + carID + " not found.");
        }
    }
}
