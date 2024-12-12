package org.example.gruppe9bilabonnement.Repository;

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
}
