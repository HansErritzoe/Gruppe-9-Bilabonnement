package org.example.gruppe9bilabonnement.Repository;

import org.example.gruppe9bilabonnement.Model.Car;
import org.example.gruppe9bilabonnement.Model.Damage_report;
import org.example.gruppe9bilabonnement.Model.Rental_contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RentalcontractRepository {

    @Autowired
    JdbcTemplate template;


    /**
     * Method for returning a list of all rentalContracts from the DB
     * @return List<Rentalcontract> - list of all rentalcars
     * @Author Jonas Jakobsen
     */
    public List<Rental_contract> getAllRentalcontracts(){
        String sql = "SELECT * FROM rental_contract";
        RowMapper<Rental_contract> rowMapper = new BeanPropertyRowMapper<>(Rental_contract.class);
        return template.query(sql,rowMapper);
    }

    /**
     * Method for adding the car to the database, returns false if failure, true if not
     * @param rental_contract - rental to be added to the database
     * @return boolean - false if failed to add rentalcontract to db, true if added successfully
     * @Author Jonas Jakobsen
     */
    public boolean addRental_contract(Rental_contract rental_contract){
        String sql = """
                    INSERT INTO rental_contract (customer_id, car_vehicle_id, start_date, end_date, status, irk_code, leasing_code, km_pr_month, price_per_month, payment_status, pickup_location, return_location, id_damage_report)
                     VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)
                    """;
        try {
            int rowsAffected = template.update(sql, rental_contract.getCustomer_id(),rental_contract.getCar_vehicle_id(),rental_contract.getStart_date(), rental_contract.getEnd_date(), rental_contract.getStatus(), rental_contract.getIrk_code(),rental_contract.getLeasing_code(),rental_contract.getKm_pr_month(), rental_contract.getPrice_per_month(),rental_contract.getPayment_status(), rental_contract.getPickup_location(), rental_contract.getReturn_location(), rental_contract.getId_damage_report());
            return rowsAffected > 0;
        } catch (DataAccessException errorMessage){
            return false;
        }
    }

    public int getExpectedRevenue(){
        String sql = "SELECT SUM(price_per_month) FROM rental_contract";
        Integer total = template.queryForObject(sql, Integer.class);
        if (total != null){
            return total;
        }
        else return 0;
    }

    public int getHandleTotal() {
        String sql = "SELECT COUNT(*) FROM rental_contract WHERE status = 'Pending Review'";
        return template.queryForObject(sql, Integer.class);
    }

    /**
     * Method to return a List of Rental contracts based on a search query containing an ID
     * List format even though should only be 1 or 0 results, because html code expects a list to display in a table
     * @param query - User's search query in the search bar
     * @return List<Rental_contract> - List containing the Rental_contract object from the search result
     */
    public List<Rental_contract> getRentalContractByID(String query) {
        String sql = "SELECT * FROM rental_contract WHERE id_rental_contract = ?";
        RowMapper<Rental_contract> rowMapper = new BeanPropertyRowMapper<>(Rental_contract.class);
        return template.query(sql, rowMapper, query);
    }

    /**
     * Method to return a Rental_contract object from the database based on integer id
     * @param id - integer id of the rental contract to be returned from DB
     * @return Rental_contract - object containing the values of a rental contract from the DB
     */
    public Rental_contract getRentalContractByID(int id){
        String sql = "SELECT * FROM rental_contract WHERE id_rental_contract = ?";
        RowMapper<Rental_contract> rowMapper = new BeanPropertyRowMapper<>(Rental_contract.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    /**
     * Method to update a rental contract in the database based on the values of a Rental_contact object
     * @param rentalContract - Rental_contract object containing the values to be updated in the DB
     * @return boolean - true if successfully updated, false if failed to update
     */
    public boolean updateRentalContract(Rental_contract rentalContract) {
        String sql = "UPDATE rental_contract SET customer_id = ?, car_vehicle_id = ?, start_date = ?, end_date = ?, status = ?, irk_code = ?, leasing_code = ?, km_pr_month = ?, price_per_month = ?, payment_status = ?, pickup_location = ?, return_location = ?, id_damage_report = ? WHERE id_rental_contract = ?";
        int affectedRows = template.update(sql, rentalContract.getCustomer_id(), rentalContract.getCar_vehicle_id(), rentalContract.getStart_date(), rentalContract.getEnd_date(), rentalContract.getStatus(), rentalContract.getIrk_code(), rentalContract.getLeasing_code(), rentalContract.getKm_pr_month(), rentalContract.getPrice_per_month(), rentalContract.getPayment_status(), rentalContract.getPickup_location(), rentalContract.getReturn_location(), rentalContract.getId_damage_report(), rentalContract.getId_rental_contract());
        return affectedRows > 0;
    }
}
