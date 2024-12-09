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

    //TODO
    public List<Damage_report> getAllDamageReports() {
        return damageReportRepository.getAllDamageReports();
    }

    //TODO
    public List<Damage_report> getDamageReportsById(String query) {
        return damageReportRepository.getDamageReportsByID(query);
    }

    //TODO returns generated reports ID if succesfull, 0 if failure
    public int addDamageReport(Damage_report damageReport) {
        return damageReportRepository.addDamageReport(damageReport);
    }

    //TODO
    public Damage_report getDamageReportByID(int id){
        return damageReportRepository.getDamageReportByID(id);
    }

    //TODO
    public boolean updateDamageReport(Damage_report damageReport) {
        return damageReportRepository.updateDamageReport(damageReport);
    }
}
