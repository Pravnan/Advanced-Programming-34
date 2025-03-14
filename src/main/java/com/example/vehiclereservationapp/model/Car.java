package com.example.vehiclereservationapp.model;
import java.util.Date;

public class Car {

    public Car(int carID, String registrationNumber, String make, String model, int year, String availabilityStatus, Date lastServiceDate, int currentMileage) {
        this.carID = carID;
        this.registrationNumber = registrationNumber;
        this.make = make;
        this.model = model;
        this.year = year;
        this.availabilityStatus = availabilityStatus;
        this.lastServiceDate = lastServiceDate;
        this.currentMileage = currentMileage;
    }


    private int carID;
    private String registrationNumber;

    public Car() {

    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public Date getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(Date lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }

    public int getCurrentMileage() {
        return currentMileage;
    }

    public void setCurrentMileage(int currentMileage) {
        this.currentMileage = currentMileage;
    }

    private String make;
    private String model;
    private int year;
    private String availabilityStatus;
    private Date lastServiceDate;
    private int currentMileage;
}
