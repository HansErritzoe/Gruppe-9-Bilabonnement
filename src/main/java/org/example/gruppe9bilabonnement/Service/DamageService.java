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

    //TODO
    public List<Damage> getAllDamagesForDamageReport(int id) {
        return damageRepository.getAllDamagesForDamageRapport(id);
    }

    //TODO
    public boolean addDamage(Damage damage) {
        return damageRepository.addDamage(damage);
    }

    //TODO
    public boolean deleteDamageByID(int id) {
        return damageRepository.deleteDamageByID(id);
    }
}
