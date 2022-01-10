package ru.job4j.accident.service;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.*;

@Service
public class AccidentService {

    private final AccidentHibernate mem;

    public AccidentService(AccidentHibernate mem) {
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
        }
        mem.save(accident);
    }

    public Accident findById(int id) {
        return mem.findByIdAccident(id);
    }

    public AccidentType findByIdType(int id) {
        return mem.findByIdType(id);
    }

    public Rule findByIdRule(int id) {
        return mem.findByIdRule(id);
    }
}
