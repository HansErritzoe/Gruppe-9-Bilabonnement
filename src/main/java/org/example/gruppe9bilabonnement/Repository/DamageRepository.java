package org.example.gruppe9bilabonnement.Repository;

import org.example.gruppe9bilabonnement.Model.Damage;
import org.example.gruppe9bilabonnement.Model.Damage_report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DamageRepository {

    @Autowired
    JdbcTemplate template;

    /**
     * Method used to fetch list of all individual damages belonging to a damage report based on the damage report id
     * @param id - id of the damage report to which the damages belong
     * @return List<Damage> - list of Damage objects which belong to that damage report id
     */
    public List<Damage> getAllDamagesForDamageRapport(int id) {
        String sql = "SELECT * FROM damage WHERE id_damage_report = ?";
        RowMapper<Damage> rowMapper = new BeanPropertyRowMapper<>(Damage.class);
        return template.query(sql, rowMapper, id);
    }

    /**
     * Method to insert a Damage into the damage table in the DB.
     * @param damage - Damage object to be inserted into the DB
     * @return boolean - true if damage added to DB, false if not
     */
    public boolean addDamage(Damage damage) {
        String sql = "INSERT INTO damage (id_damage_report, type, placement, price) VALUES (?,?,?,?)";
        int affectedRows = template.update(sql, damage.getId_damage_report(),damage.getType(), damage.getPlacement(), damage.getPrice());
        return affectedRows > 0;
    }

    /**
     * Method to delete a damage row from the database based on id
     * @param id - int id of the damage row to be deleted
     * @return boolean - true if successfully deleted, false if failed to delete
     */
    public boolean deleteDamageByID(int id) {
        String sql = "DELETE FROM damage WHERE id_damage = ?";
        int affectedRows = template.update(sql,id);
        return affectedRows > 0;
    }
}
