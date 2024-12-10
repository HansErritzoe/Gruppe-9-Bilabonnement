package org.example.gruppe9bilabonnement.Repository;

import org.example.gruppe9bilabonnement.Model.Car;
import org.example.gruppe9bilabonnement.Model.Damage_report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class Damage_reportRepository {

    @Autowired
    JdbcTemplate template;

    /**
     * Method to return all damage reports from the database in a List<Damage_report>
     * @return List<Damage_report> returns a list of all damage reports in the DB
     * @Author Hans Erritzøe
     */
    public List<Damage_report> getAllDamageReports() {
        String sql = "SELECT * FROM damage_report";
        RowMapper<Damage_report> rowMapper = new BeanPropertyRowMapper<>(Damage_report.class);
        return template.query(sql, rowMapper);
    }

    /**
     * Method to return a list containing just 1 damage report based on ID.
     * It's a list because it is to be displayed in a table which accepts lists
     * @param query - user's search query which should contain just an ID
     * @return List<Damage_report> - a list containing 1 or 0 Damage_report objects matching the ID in query
     */
    public List<Damage_report> getDamageReportsByID(String query) {
        String sql = "SELECT * FROM damage_report WHERE id_damage_report = ?";
        RowMapper<Damage_report> rowMapper = new BeanPropertyRowMapper<>(Damage_report.class);
        return template.query(sql, rowMapper, query);
    }

    /**
     * Method to add a damage report to the DB and return the generated autoincrement id
     * @param damageReport - Damage_report object to be inserted into the DB
     * @return int - returns the generated autoincrement id that the new damage report insert has if successfull, -1 if failed to add
     */
    public int addDamageReport(Damage_report damageReport) {
        String sql = """
                INSERT INTO damage_report (car_id_vehicle, price_total, date, filled_by, payment_status)
                 VALUES (?, 0, ?, ?, ?)
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, damageReport.getCar_id_vehicle());
            ps.setDate(2, Date.valueOf(damageReport.getDate()));
            ps.setString(3, damageReport.getFilled_by());
            ps.setString(4,damageReport.getPayment_status());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    /**
     * Method to return a single Damage_report object from the DB based on an id (id_damage_report)
     * @param id - id of the damage report in the DB to be returned
     * @return Damage_report - Damage_report object from the DB containing all its informations
     */
    public Damage_report getDamageReportByID(int id) {
        String sql = "SELECT * FROM damage_report WHERE id_damage_report = ?";
        RowMapper<Damage_report> rowMapper = new BeanPropertyRowMapper<>(Damage_report.class);
        Damage_report dr = template.queryForObject(sql, rowMapper, id);
        return dr;
    }

    /**
     * Method to update the values of a damage report in the database based on a Damage_report objects values
     * @param report - Damage_report object with values to be updated in the DB
     * @return boolean - true if successfully updated in the DB, false if not
     */
    public boolean updateDamageReport(Damage_report report){
        String sql = "UPDATE damage_report SET car_id_vehicle = ?, date = ?, filled_by = ?, payment_status = ? WHERE id_damage_report = ?";
        int affectedRows = template.update(sql, report.getCar_id_vehicle(), report.getDate(), report.getFilled_by(), report.getPayment_status(), report.getId_damage_report());
        return affectedRows > 0;
    }

}
