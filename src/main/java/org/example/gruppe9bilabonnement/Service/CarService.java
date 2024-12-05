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
     * Author - Hans Erritzøe
     */
    public List<Car> getAllCars(){
        return carRepository.getAllCars();
    }

}
