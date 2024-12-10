package org.example.gruppe9bilabonnement.Service;

import org.example.gruppe9bilabonnement.Model.Damage;
import org.example.gruppe9bilabonnement.Repository.DamageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DamageService {

    @Autowired
    DamageRepository damageRepository;

    /**
     * Method to call repository layer to fetch a list of all damages belonging to a Damage
     * report based on that damage report's ID
     * @param id - int id of the damage report from which to fetch the list of damages
     * @return List<Damage> - returns a list of the damages with that damage_report_id
     * @Author Hans Erritzøe
     */
    public List<Damage> getAllDamagesForDamageReport(int id) {
        return damageRepository.getAllDamagesForDamageRapport(id);
    }

    /**
     * Method to call repository layer to add a damage to the damage table in the DB
     * @param damage - the Damage object with values to be inserted into the damage table in the DB
     * @return boolean - true if successfully added to DB, false if not
     * @Author Hans Erritzøe
     */
    public boolean addDamage(Damage damage) {
        return damageRepository.addDamage(damage);
    }

    /**
     * Method to call repository layer to delete a damage row in the DB based on id
     * @param id - id of the damage row to be deleted
     * @return boolean - true if successfully deleted, false if failed to delete
     * @Author Hans Erritzøe
     */
    public boolean deleteDamageByID(int id) {
        return damageRepository.deleteDamageByID(id);
    }

    /**
     * Method to call repository layer to fetch a Damage object from the DB based on ID
     * @param id - id of the damage row to be fetched
     * @return Damage - returns a Damage object containing the values of the relevant row in the DB
     * @Author Hans Erritzøe
     */
    public Damage getDamageByID(int id) {
        return damageRepository.getDamageByID(id);
    }

    /**
     * Calls repository layer to update a Damage row with new values based on a Damage object
     * @param damage - the Damage object containing the values to be updated in the DB
     * @return boolean - true if successfully updated changes to DB, false if not
     * @Author - Hans Erritzøe
     */
    public boolean updateDamage(Damage damage) {
        return damageRepository.updateDamage(damage);
    }
}
