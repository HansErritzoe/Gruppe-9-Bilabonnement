package org.example.gruppe9bilabonnement.Service;

import org.example.gruppe9bilabonnement.Model.Rental_contract;
import org.example.gruppe9bilabonnement.Repository.RentalcontractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalContractService {


    @Autowired
    RentalcontractRepository rentalcontractRepository;

    /**
     * Method to call Rentalcontract Repository
     * @return List<Rental_contract> - a list of all rental contracts in the Database
     * @Author Jonas Jakobsen
     */
    public List<Rental_contract> getAllRentalcontracts() {
        return rentalcontractRepository.getAllRentalcontracts();
    }


    /**
     * Calls repository layer to add car to database, returns false if failed to add, true if car added to DB
     * @param rental_contract - rental contract to be added to the database
     * @return boolean - true if the rental contract was added successfully to the database, false if it failed to add
     * @Author Jonas Jakobsen
     */
    public boolean addRental_contract(Rental_contract rental_contract) {
        return rentalcontractRepository.addRental_contract(rental_contract);
    }

    /**
     * Calls repository layer to return a list containing just 1 or 0 Rental contracts based on ID
     * @param query - (String) user's query in the search bar
     * @return List<Rental_contract> - returns a List object containing the Rental_Contract object
     * List<> format because html expects a list to be displayed in the table
     * @Author - Hans Erritzøe
     */
    public List<Rental_contract> getRentalContractByID(String query) {
        return rentalcontractRepository.getRentalContractByID(query);
    }

    /**
     * Calls repository layer to return a Rental_contract object based on integer id
     * @param id - integer id of the contract to be fetched
     * @return Rental_contract - object returned from the repository layer
     * @Author - Hans Erritzøe
     */
    public Rental_contract getRentalContractByID(int id){
        return rentalcontractRepository.getRentalContractByID(id);
    }

    /**
     * Calls repository layer to update a rental contract in the database based on a Rental_contract object
     * @param rentalContract - Object containing values to be updated in the DB
     * @return boolean - true if rental contract was updated in DB, false if failed to update
     * @Author - Hans Erritzøe
     */
    public boolean updateRentalContract(Rental_contract rentalContract) {
        return rentalcontractRepository.updateRentalContract(rentalContract);
    }

    /**
     * Calls repository layer to get a int containing the expected revenue for the month
     * @return int - containing the sum of all price_per_month values in the database
     * @Author - Jonas Jakobsen
     */
    public int getExpectedRevenue(){
        return rentalcontractRepository.getExpectedRevenue();
    }

    /**
     * Calls repository layer to get a int containing all rental contracts that needs handling
     * @return int - containing all rental_contracts with status 'pending review'
     * @Author - Jonas Jakobsen
     */
    public int getHandleTotal(){
        return rentalcontractRepository.getHandleTotal();
    }
}
