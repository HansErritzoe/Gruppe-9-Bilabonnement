package org.example.gruppe9bilabonnement.Repository;

import org.example.gruppe9bilabonnement.Model.Car;
import org.example.gruppe9bilabonnement.Model.Damage_report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Damage_reportRepository {

    @Autowired
    JdbcTemplate template;

    //TODO
    public List<Damage_report> getAllDamageReports() {
        String sql = "SELECT * FROM damage_report";
        RowMapper<Damage_report> rowMapper = new BeanPropertyRowMapper<>(Damage_report.class);
        return template.query(sql,rowMapper);
    }

    //TODO
    public List<Damage_report> getDamageReportsByID(String query) {
        String sql = "SELECT * FROM damage_report WHERE id_damage_report = ?";
        RowMapper<Damage_report> rowMapper = new BeanPropertyRowMapper<>(Damage_report.class);
        return template.query(sql,rowMapper, query);
    }
}
