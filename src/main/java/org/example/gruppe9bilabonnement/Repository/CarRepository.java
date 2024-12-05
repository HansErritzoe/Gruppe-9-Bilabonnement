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
     * Author - Hans Erritzøe - TODO Spørg cay om de her metoder som er inspireret/næsten 100% taget fra hans undervisningsmateriale skal beskrives som "taget fra..." eller om de er så boilerplate at der ingen vej udenom at de ligner
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
     * Author - Hans Erritzøe
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
     * TODO write when completed
     * @param car
     * @return
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
}
