package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;

@Repository
public class AccidentJdbcTemplate {

    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident save(Accident accident) {
        jdbc.update("insert into accident (name, text, address) values(?, ?, ?)",
                accident.getName(),
                accident.getText(),
                accident.getAddress());
        return accident;
    }

    public AccidentType saveType(AccidentType accidentType) {
        jdbc.update("insert into accident_type (name) values(?)",
                accidentType.getName());
        return accidentType;
    }

    public Rule saveRule(Rule rule) {
        jdbc.update("insert into accident_rule (name) values(?)",
                rule.getName());
        return rule;
    }

    public Collection<Accident> getAll() {
        return jdbc.query("select id, name, text, address from accident",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    return accident;
                });
    }

    public Accident findByIdAccident(int id) {
        return jdbc.queryForObject("select id, name, text, address from accident where id = ?",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    return accident;
                }, id);
    }

    public Rule findByIdRule(int id) {
        return jdbc.queryForObject("select id, name from accident_rule where id = ?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                }, id);
    }

    public AccidentType findByIdType(int id) {
        return jdbc.queryForObject("select id, name from accident_type where id = ?",
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                }, id);
    }

    public Collection<AccidentType> getAllType() {
        return jdbc.query("select id, name from accident_type",
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                });
    }

    public Collection<Rule> getAllRule() {
        return jdbc.query("select id, name from accident_rule",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }
}
