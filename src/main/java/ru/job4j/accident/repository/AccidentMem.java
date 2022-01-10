package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final AtomicInteger size = new AtomicInteger(4);

    private final Map<Integer, AccidentType> typeMap = new HashMap<>();
    private final AtomicInteger sizeType = new AtomicInteger(4);

    private final Map<Integer, Rule> ruleMap = new HashMap<>();
    private final AtomicInteger sizeRule = new AtomicInteger(4);

    public AccidentMem() {
        AccidentType type1 = AccidentType.of(1, "Две машины");
        AccidentType type2 = AccidentType.of(2, "Машина и человек");
        AccidentType type3 = AccidentType.of(3, "Машина и велосипед");

        Set<Rule> rules1 = Set.of(Rule.of("Статья 1"));
        Set<Rule> rules2 = Set.of(Rule.of("Статья 2"));
        Set<Rule> rules3 = Set.of(Rule.of("Статья 3"));

        accidents.put(1, new Accident(1,
                 "Михаил", "Превышение скорости", "пр-кт Ленина 32", type1, rules1));
        accidents.put(2, new Accident(2,
                 "Роман", "Не пропустил пешехода", "ул. Баумана 100", type2, rules2));
        accidents.put(3, new Accident(3,
                "Василий", "совершил ДТП", "пр-скт Гагарина 35", type3, rules3));

        typeMap.put(1,  AccidentType.of(1, "Две машины"));
        typeMap.put(2,  AccidentType.of(2, "Машина и человек"));
        typeMap.put(3,  AccidentType.of(3, "Машина и велосипед"));

        ruleMap.put(1,  Rule.of("Статья 1"));
        ruleMap.put(2,  Rule.of("Статья 2"));
        ruleMap.put(3,  Rule.of("Статья 3"));
    }

    public Collection<AccidentType> getValue() {
        return typeMap.values();
    }

    public Collection<Rule> getResultRule() {
        return ruleMap.values();
    }

    public void put(int id, Accident accident) {
        accidents.put(id, accident);
    }

    public void createResult(Accident accident) {
            accident.setId(size.getAndIncrement());
        accidents.put(accident.getId(), accident);
    }

    public Accident get(int id) {
        return accidents.get(id);
    }

    public Rule getRule(int id) {
        return ruleMap.get(id);
    }

    public AccidentType getType(int id) {
        return typeMap.get(id);
    }

    public void saveResult(Accident accident) {
        accident.setId(size.getAndIncrement());
        accidents.put(accident.getId(), accident);
    }

    public void saveResultType(AccidentType accidentType) {
        accidentType.setId(sizeType.getAndIncrement());
        typeMap.put(accidentType.getId(), accidentType);
    }

    public void saveResultRule(Rule rule) {
        rule.setId(sizeRule.getAndIncrement());
        ruleMap.put(rule.getId(), rule);
    }

    public Collection<Accident> getResult() {
        return accidents.values();
    }

}
