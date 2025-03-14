package com.example.vehiclereservationapp.controller;

import com.example.vehiclereservationapp.model.Car;
import com.example.vehiclereservationapp.service.BookingService;
import com.example.vehiclereservationapp.service.CarService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/car")  // URL pattern for CarController
public class CarController extends HttpServlet {

    private CarService carService;

    @Override
    public void init() throws ServletException {
        carService = new CarService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "list":
                    listCars(request, response);
                    break;
                case "view":
                    viewCar(request, response);
                    break;
                case "edit":
                    editCar(request, response);
                    break;
                case "delete":
                    deleteCar(request, response);
                    break;
                case "add":
                    addCarPage(request, response);
                    break;
                default:
                    listCars(request, response);  // Default action is to show the car list
            }
        } else {
            listCars(request, response);  // Default action if no specific action is given
        }
    }

    // Display the list of cars
    private void listCars(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Car> cars = carService.getAllCars();  // Get all cars from the service
        request.setAttribute("cars", cars);
        request.getRequestDispatcher("/views/car-list.jsp").forward(request, response);  // Forward to the car list page
    }

    // View details of a single car
    private void viewCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int carId = Integer.parseInt(request.getParameter("id"));
        Car car = carService.getCarById(carId);  // Get car details by ID
        if (car != null) {
            request.setAttribute("car", car);
            request.getRequestDispatcher("/views/car-view.jsp").forward(request, response);  // Forward to the car view page
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Car not found");
        }
    }

    // Edit a car (e.g., show a form with current details)
    private void editCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int carId = Integer.parseInt(request.getParameter("id"));
        Car car = carService.getCarById(carId);  // Get the car to be edited
        if (car != null) {
            request.setAttribute("car", car);
            request.getRequestDispatcher("/views/car-edit.jsp").forward(request, response);  // Forward to the car edit form
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Car not found");
        }
    }

    // Delete a car from the database
    private void deleteCar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int carId = Integer.parseInt(request.getParameter("id"));
        carService.deleteCar(carId);  // Call the service to delete the car
        response.sendRedirect("car?action=list");  // Redirect to the car list after deletion
    }

    // Show the page to add a new car
    private void addCarPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/car-add.jsp").forward(request, response);  // Forward to the add car page
    }

    // Handle POST requests (e.g., for adding or updating a car)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addCar(request, response);  // Add a new car
        } else if ("edit".equals(action)) {
            updateCar(request, response);  // Update an existing car
        }
    }

    // Add a new car to the database
    private void addCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String registrationNumber = request.getParameter("registrationNumber");
        String make = request.getParameter("make");
        String model = request.getParameter("model");
        int year = Integer.parseInt(request.getParameter("year"));
        String availabilityStatus = request.getParameter("availabilityStatus");
        String lastServiceDate = request.getParameter("lastServiceDate");  // Parse date from string if needed
        int currentMileage = Integer.parseInt(request.getParameter("currentMileage"));

        Car car = new Car();
        car.setRegistrationNumber(registrationNumber);
        car.setMake(make);
        car.setModel(model);
        car.setYear(year);
        car.setAvailabilityStatus(availabilityStatus);
        // You can parse the date from the string or handle it as a Date object
        car.setLastServiceDate(java.sql.Date.valueOf(lastServiceDate));
        car.setCurrentMileage(currentMileage);

        carService.addCar(car);  // Add the car using the service
        response.sendRedirect("car?action=list");  // Redirect to the car list
    }

    // Update car details in the database
    private void updateCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int carId = Integer.parseInt(request.getParameter("id"));
        String registrationNumber = request.getParameter("registrationNumber");
        String make = request.getParameter("make");
        String model = request.getParameter("model");
        int year = Integer.parseInt(request.getParameter("year"));
        String availabilityStatus = request.getParameter("availabilityStatus");
        String lastServiceDate = request.getParameter("lastServiceDate");
        int currentMileage = Integer.parseInt(request.getParameter("currentMileage"));

        Car car = new Car();
        car.setCarID(carId);
        car.setRegistrationNumber(registrationNumber);
        car.setMake(make);
        car.setModel(model);
        car.setYear(year);
        car.setAvailabilityStatus(availabilityStatus);
        car.setLastServiceDate(java.sql.Date.valueOf(lastServiceDate));
        car.setCurrentMileage(currentMileage);

        carService.updateCar(car);  // Update the car using the service
        response.sendRedirect("car?action=list");  // Redirect to the car list
    }
}
