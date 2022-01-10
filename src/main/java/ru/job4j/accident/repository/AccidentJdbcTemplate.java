package ru.job4j.accident.repository;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;


public class AccidentJdbcTemplate {

    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident save(Accident accident) {
        if (accident.getId() != 0) {
            jdbc.update("update accident set name = ? where id = ?",
                    accident.getName(),
                    accident.getId());
        } else {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbc.update(
                    new PreparedStatementCreator() {
                        @Override
                        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                            PreparedStatement ps = connection.prepareStatement(
                                    "insert into accident (name, text, address, type_id) values (?, ?, ?, ?)",
                                    new String[]{"id"}
                            );
                            ps.setString(1, accident.getName());
                            ps.setString(2, accident.getText());
                            ps.setString(3, accident.getAddress());
                            ps.setInt(4, accident.getType().getId());
                            return ps;
                        }
                    }, keyHolder
            );
            accident.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
            for (Rule elem : accident.getRules()) {
                jdbc.update("insert into type_rule (acciident_id, rule_id) values (?, ?)",
                        accident.getId(),
                        elem.getId()
                );
            }
        }
            return accident;
        }

    public List<Accident> getAll() {
        List<Accident> buffer = new ArrayList<>();
         buffer = jdbc.query("select * from accident order by id asc",
                (rs, row) -> {
                    Accident accident = new Accident();
                    int accidentTypeId = rs.getInt("type_id");
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(findAccidentTypeById(accidentTypeId));
                    accident.setRules(new HashSet<>(findRulesByAccidentId(accident.getId())));
                    return accident;
                });
         return buffer;
    }

    public Collection<AccidentType> getAllType() {
        return jdbc.query("select * from accident_type",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                });
    }

    public Accident findByIdAccident(int id) {
        return jdbc.queryForObject(
                "select * from accident where id = ?",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    return accident;
                },
                id
        );
    }

    public Collection<Rule> getAllRule() {
        return jdbc.query("select * from accident_rule",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    public AccidentType findAccidentTypeById(int id) {
        return jdbc.queryForObject(
                "select * from accident_type where id = ?",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                },
                id
        );
    }

    public Rule findRuleById(int id) {
        return jdbc.queryForObject(
                "select * from accident_rule where id = ?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                },
                id
        );
    }

    public List<Rule> findRulesByAccidentId(int id) {
        List<Rule> buffer = jdbc.query("select * from type_rule where acciident_id = ?",
                (rs, row) -> {
                    Rule rule = new Rule();
                   rule.setId(rs.getInt("rule_id"));
                    return rule;
                }, id);
        List<Rule> result = new ArrayList<>();
        for (Rule rule : buffer) {
            result.add(findRuleById(rule.getId()));
        }
        return result;
    }
}
