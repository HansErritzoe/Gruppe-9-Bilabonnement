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

    public int getExpectedRevenue(){
        return rentalcontractRepository.getExpectedRevenue();
    }

    public int getHandleTotal(){
        return rentalcontractRepository.getHandleTotal();
    }
}
