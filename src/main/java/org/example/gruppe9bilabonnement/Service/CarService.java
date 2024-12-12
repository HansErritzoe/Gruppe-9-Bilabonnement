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

    public int getUnavailableTotal(){ return carRepository.getUnavailableTotal();}

    public int getAvailableTotal() { return carRepository.getAvailableTotal();}

    /**
     * Method to call repository layer to add car to database, returns false if failed to add, true if car added to DB
     * @param car - car to be added to DB
     * @return boolean - true if car added successfully, false if failed to add
     * @Author Hans Erritzøe
     */
    public boolean addCar(Car car) {
        return carRepository.addCar(car);
    }

    /**
     * Method to call repository layer to check if a car with a specific id exists
     * @param id - id of the car to be checked if exists
     * @return boolean - returns true if car exists, false if not
     * @Author Hans Erritzøe
     */
    public boolean doesCarExistWithId(int id) {
        return carRepository.doesCarExistWithID(id);
    }

    /**
     * Method to call repository layer to return a car based on an id
     * @param id - id of the car to be returned (int)
     * @return Car - car object returned with values retrieved from the database
     * @Author - Hans Erritzøe
     */
    public Car getCarByID(int id) {
        return carRepository.getCarByID(id);
    }

    /**
     * Method to call repository layer to update a car based on a passed Car object
     * @param car - Car Object with values to be inserted in the database
     * @return boolean - true if successful, false if failed to add
     * @Author - Hans Erritzøe
     */
    public boolean updateCar(Car car) {
        return carRepository.updateCar(car);
    }
}
