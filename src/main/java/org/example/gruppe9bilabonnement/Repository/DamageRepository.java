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

    //TODO
    public List<Damage> getAllDamagesForDamageRapport(int id) {
        String sql = "SELECT * FROM damage WHERE id_damage_report = ?";
        RowMapper<Damage> rowMapper = new BeanPropertyRowMapper<>(Damage.class);
        return template.query(sql, rowMapper, id);
    }

    //TODO
    public boolean addDamage(Damage damage) {
        String sql = "INSERT INTO damage (id_damage_report, type, placement, price) VALUES (?,?,?,?)";
        int affectedRows = template.update(sql, damage.getId_damage_report(),damage.getType(), damage.getPlacement(), damage.getPrice());
        return affectedRows > 0;
    }

    //TODO
    public boolean deleteDamageByID(int id) {
        String sql = "DELETE FROM damage WHERE id_damage = ?";
        int affectedRows = template.update(sql,id);
        return affectedRows > 0;
    }
}
