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
     * @Author - Hans Erritzøe
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
     * @Author - Hans Erritzøe
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
     * @Author - Hans Erritzøe
     */
    public boolean deleteDamageByID(int id) {
        String sql = "DELETE FROM damage WHERE id_damage = ?";
        int affectedRows = template.update(sql,id);
        return affectedRows > 0;
    }


    /**
     * Method to return a Damage object from the DB based on ID
     * @param id - ID of the damage to be retrieved
     * @return Damage - returns a Damage object with the values of the rows in the DB
     * @Author - Hans Erritzøe
     */
    public Damage getDamageByID(int id) {
        String sql = "SELECT * FROM damage WHERE id_damage = ?";
        RowMapper<Damage> rowMapper = new BeanPropertyRowMapper<>(Damage.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    /**
     * Method to update a damage row in the damage table based on the values of a Damage object
     * @param damage - Damage object containing the values to be set in the DB
     * @Author - Hans Erritzøe
     */
    public boolean updateDamage(Damage damage) {
        String sql = "UPDATE damage SET type = ?, placement = ?, price = ? WHERE id_damage = ?";
        int affectedRows = template.update(sql,damage.getType(), damage.getPlacement(), damage.getPrice(), damage.getId_damage());
        return affectedRows > 0;
    }
}
