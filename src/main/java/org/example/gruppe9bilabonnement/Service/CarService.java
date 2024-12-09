package org.example.gruppe9bilabonnement.Service;


import org.example.gruppe9bilabonnement.Model.Car;
import org.example.gruppe9bilabonnement.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    /**
     * Method to call carRepository for a list of all cars in the DB
     * @return List<Car> - a list of all cars in the DB in <List> format
     * @Author Hans Erritzøe
     */
    public List<Car> getAllCars() {
        return carRepository.getAllCars();
    }

    /**
     * Method to return a list of cars, where the query matches exactly the vehicle_id or all partial VIN matches
     * @param query - vehicle_id or VIN to be searched for
     * @return List<Car> - list of cars matching either exactly vehicle_id or all partial VIN matches in DB
     * @Author Hans Erritzøe
     */
    public List<Car> getCarsByIdOrVIN(String query) {
        return carRepository.getCarsByIdOrVin(query);
    }


    /**
     * Calls repository layer to add car to database, returns false if failed to add, true if car added to DB
     * @param car - car to be added to DB
     * @return boolean - true if car added successfully, false if failed to add
     * @Author Hans Erritzøe
     */
    public boolean addCar(Car car) {
        return carRepository.addCar(car);
    }

    //TODO
    public boolean doesCarExistWithId(int id) {
        return carRepository.doesCarExistWithID(id);
    }
}
