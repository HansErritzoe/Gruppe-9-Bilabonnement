package org.example.gruppe9bilabonnement.Service;

import org.example.gruppe9bilabonnement.Model.Damage_report;
import org.example.gruppe9bilabonnement.Repository.Damage_reportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Damage_reportService {

    @Autowired
    Damage_reportRepository damageReportRepository;

    /**
     * Method to call repository layer for a list of all damage reports from the DB
     * @return List<Damage_report> - list of all Damage reports in the DB
     * @Author Hans Erritzøe
     */
    public List<Damage_report> getAllDamageReports() {
        return damageReportRepository.getAllDamageReports();
    }

    /**
     * Method to call repository layer for a list of Damage_report objects matching the id in the query
     * List instead of single object because the table displaying the object expects a List
     * @param query - query containing the id to be searched for in the DB
     * @return List<Damage_report> - list of all Damage reports in the DB matching the ID, (max 1)
     * @Author Hans Erritzøe
     */
    public List<Damage_report> getDamageReportsById(String query) {
        return damageReportRepository.getDamageReportsByID(query);
    }

    /**
     * Method to call repository layer to add a Damage_report object to the DB and return the generated id of the inserted row
     * @param damageReport - the damage report to be added to the DB
     * @return int - returns the generated auto-increment ID of the inserted row, returns -1 if failed to insert
     * @Author Hans Erritzøe
     */
    public int addDamageReport(Damage_report damageReport) {
        return damageReportRepository.addDamageReport(damageReport);
    }

    /**
     * Method to call repository layer to return a Damage_report object from the DB based on id
     * @param id - id of the damage report to be fetched from DB
     * @return Damage_report - the Damage_report object fetched from the DB
     * @Author Hans Erritzøe
     */
    public Damage_report getDamageReportByID(int id){
        return damageReportRepository.getDamageReportByID(id);
    }

    /**
     * Method to call repository layer to update a Damage_report report with new values based on the values of the
     * passed Damage_report object
     * @param damageReport - Damage_report object with values to be updated in the DB
     * @return boolean - returns true if update successfull, returns false if update failed
     * @Author Hans Erritzøe
     */
    public boolean updateDamageReport(Damage_report damageReport) {
        return damageReportRepository.updateDamageReport(damageReport);
    }
}
