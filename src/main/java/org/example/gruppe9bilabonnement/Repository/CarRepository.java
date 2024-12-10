package org.example.gruppe9bilabonnement.Repository;

import org.example.gruppe9bilabonnement.Model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepository {

    @Autowired
    JdbcTemplate template;


    /**
     * Method for returning a list of all cars from the DB
     * @return List<Car> - list of all cars in List format
     * @Author Hans Erritzøe
     */
    public List<Car> getAllCars(){
        String sql = "SELECT * FROM car";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return template.query(sql,rowMapper);
    }

    /**
     * Method for returning a list of cars from the DB matching either exactly id_vehicle or all partial VIN matches (%query%)
     * The second select statement in the union is only run if the "not exists" sub-query evaluates to true
     * @param query - the users search query
     * @return List<Car> - List of car objects matching either the exact id_vehicle or all partial VIN matches
     * @Author Hans Erritzøe
     */
    public List<Car> getCarsByIdOrVin(String query) {
        String sql = """
                (SELECT * FROM car WHERE id_vehicle = ? LIMIT 1)
                UNION
                (SELECT * FROM car WHERE VIN LIKE ? AND NOT EXISTS(SELECT * FROM car WHERE id_vehicle = ?))
                """;
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return template.query(sql,rowMapper, query, "%"+query+"%", query);
    }

    /**
     * Method for adding the car to the database, returns false if failure, true if not
     * @param car - car to be added to the database
     * @return boolean - false if failed to add car to db, true if added successfully
     * @Author Hans Erritzøe
     */
    public boolean addCar(Car car){
        String sql = """
                    INSERT INTO car (VIN, brand, model, year, owner, km_driven, km_price, monthly_price, available)
                     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;
        try {
            int rowsAffected = template.update(sql, car.getVIN(), car.getBrand(), car.getModel(), car.getYear(), car.getOwner(),
                    car.getKm_driven(), car.getKm_price(), car.getMonthly_price(), car.isAvailable());
            return rowsAffected > 0;
        } catch (DataAccessException errorMessage){
            //could add logging the error here
            return false;
        }

    }

    /**
     * Method used to check if an car exists in the database with a specific id
     * @param id - id (int) to be checked if exists in the database
     * @return boolean - returns true if car exists, false if not
     */
    public boolean doesCarExistWithID(int id){
        String sql = "SELECT COUNT(*) FROM car WHERE id_vehicle = ?";
        int count = template.queryForObject(sql, Integer.class, id);
        return count > 0;
    }

    /**
     * Method to return a car object from the DB based on an ID
     * @param id - id (int) of the car to be retrieved from the database
     * @return Car - Car object returned from the database based on the ID
     * @Author - Hans Erritzøe
     */
    public Car getCarByID(int id) {
        String sql = "SELECT * FROM car WHERE id_vehicle = ?";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    /**
     * Method to update a car in the database based on the values of a passed Car object
     * @param car - Car object with values and id of the car to be updated in the DB
     * @return boolean - true if successful, false if failed to add
     * @Author - Hans Erritzøe
     */
    public boolean updateCar(Car car) {
        String sql = "UPDATE car SET VIN = ?, brand = ?, model = ?, year = ?, owner = ?, km_driven = ?, km_price = ?, monthly_price = ?, available = ? WHERE id_vehicle = ?";
        int affectedRows = template.update(sql, car.getVIN(), car.getBrand(), car.getModel(), car.getYear(), car.getOwner(), car.getKm_driven(), car.getKm_price(), car.getMonthly_price(), car.isAvailable(), car.getId_vehicle());
        return affectedRows > 0;
    }
}
