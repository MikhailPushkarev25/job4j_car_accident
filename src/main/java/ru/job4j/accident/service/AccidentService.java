package ru.job4j.accident.service;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.*;

@Service
public class AccidentService {

    private final AccidentJdbcTemplate mem;

    public AccidentService(AccidentJdbcTemplate mem) {
        this.mem = mem;
    }

    public Collection<Accident> findAllAccident() {
        return mem.getAll();
    }

    public Collection<AccidentType> findAllAccidentType() {
        return mem.getAllType();
    }

    public Collection<Rule> findAllRule() {
        return mem.getAllRule();
    }

    public void saveAccident(Accident accident, String[] str) {
        if (str != null) {
            Set<Rule> rules = new HashSet<>();
            for (String id : str) {
                rules.add(findByIdRule(Integer.parseInt(id)));
            }
            accident.setRules(rules);
        } else {
            accident.setRules(findRulesByAccidentId(accident.getId()));
        }
        mem.save(accident);
    }

    private Set<Rule> findRulesByAccidentId(int id) {
        return new HashSet<>(mem.findRulesByAccidentId(id));
    }

    public Accident findById(int id) {
        return mem.findByIdAccident(id);
    }

    public AccidentType findByIdType(int id) {
        return mem.findAccidentTypeById(id);
    }

    public Rule findByIdRule(int id) {
        return mem.findRuleById(id);
    }
}
