package ru.job4j.accident.service;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.RuleRepository;

import java.util.*;

@Service
public class AccidentService {

    private final AccidentRepository acc;
    private final AccidentTypeRepository accType;
    private final RuleRepository rule;

    public AccidentService(AccidentRepository acc,
                           AccidentTypeRepository accType,
                           RuleRepository rule) {
        this.acc = acc;
        this.accType = accType;
        this.rule = rule;
    }

    public Collection<Accident> findAllAccident() {
        return acc.findAll();
    }

    public Collection<AccidentType> findAllAccidentType() {
        return (Collection<AccidentType>) accType.findAll();
    }

    public Collection<Rule> findAllRule() {
        return (Collection<Rule>) rule.findAll();
    }

    public void saveAccident(Accident accident, String[] str) {
        if (str != null) {
            Set<Rule> rules = new HashSet<>();
            for (String id : str) {
                rules.add(findByIdRule(Integer.parseInt(id)));
            }
            accident.setRules(rules);
        }
        acc.save(accident);
    }

    public Accident findById(int id) {
        return acc.findAccidentById(id);
    }

    public AccidentType findByIdType(int id) {
        return accType.findById(id).orElse(null);
    }

    public Rule findByIdRule(int id) {
        return rule.findById(id).orElse(null);
    }
}
