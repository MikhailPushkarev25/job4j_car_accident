package ru.job4j.accident.service;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import java.util.*;

@Service
public class AccidentService {

    private AccidentMem result;

    public AccidentService(AccidentMem result) {
        this.result = result;
    }

    public void create(Accident accident) {
        result.createResult(accident);
    }

    public Accident getAccident(int id) {
        return result.get(id);
    }

    public Collection<Accident> findAll() {
        return result.getResult();
    }

    public Collection<AccidentType> getAllType() {
        return result.getValue();
    }

    public Collection<Rule> getAllRule() {
        return result.getResultRule();
    }

    public void save(Accident accident, String[] line) {
        accident.setType(getType(accident.getType().getId()));
        Set<Rule> res = new HashSet<>();
        for (String str : line) {
            res.add(getRule(Integer.parseInt(str)));
        }
        accident.setRules(res);
        result.saveResult(accident);
    }

    public void saveRule(Rule rule) {
        result.saveResultRule(rule);
    }

    public void saveType(AccidentType accident) {
        result.saveResultType(accident);
    }

    public Rule getRule(int id) {
        return result.getRule(id);
    }

    public AccidentType getType(int id) {
        return result.getType(id);
    }
}
