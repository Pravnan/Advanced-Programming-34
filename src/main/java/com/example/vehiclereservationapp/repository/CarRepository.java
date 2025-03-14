package com.example.vehiclereservationapp.repository;

import com.example.vehiclereservationapp.model.Car;

import java.util.List;

public interface CarRepository {

    //CRUD
    Car findById(int id);
    List<Car> findAll();
    void save(Car car);
    void delete(Car car);
    void update(Car car);


}
