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

    //TODO
    public List<Damage_report> getAllDamageReports() {
        String sql = "SELECT * FROM damage_report";
        RowMapper<Damage_report> rowMapper = new BeanPropertyRowMapper<>(Damage_report.class);
        return template.query(sql, rowMapper);
    }

    //TODO
    public List<Damage_report> getDamageReportsByID(String query) {
        String sql = "SELECT * FROM damage_report WHERE id_damage_report = ?";
        RowMapper<Damage_report> rowMapper = new BeanPropertyRowMapper<>(Damage_report.class);
        return template.query(sql, rowMapper, query);
    }

    //TODO
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

    //TODO
    public Damage_report getDamageReportByID(int id) {
        String sql = "SELECT * FROM damage_report WHERE id_damage_report = ?";
        RowMapper<Damage_report> rowMapper = new BeanPropertyRowMapper<>(Damage_report.class);
        Damage_report dr = template.queryForObject(sql, rowMapper, id);
        return dr;
    }

    //TODO
    public boolean updateDamageReport(Damage_report report){
        String sql = "UPDATE damage_report SET car_id_vehicle = ?, date = ?, filled_by = ?, payment_status = ? WHERE id_damage_report = ?";
        int affectedRows = template.update(sql, report.getCar_id_vehicle(), report.getDate(), report.getFilled_by(), report.getPayment_status(), report.getId_damage_report());
        return affectedRows > 0;
    }

}
