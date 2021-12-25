package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final AtomicInteger size = new AtomicInteger(4);

    private final Map<Integer, AccidentType> typeMap = new HashMap<>();
    private final AtomicInteger sizeType = new AtomicInteger(4);

    public AccidentMem() {
        AccidentType type1 = AccidentType.of(1, "Две машины");
        AccidentType type2 = AccidentType.of(2, "Машина и человек");
        AccidentType type3 = AccidentType.of(3, "Машина и велосипед");
        accidents.put(1, new Accident(
                 "Михаил", "Превышение скорости", "пр-кт Ленина 32", type1));
        accidents.put(2, new Accident(
                 "Роман", "Не пропустил пешехода", "ул. Баумана 100", type2));
        accidents.put(3, new Accident(
                "Василий", "совершил ДТП", "пр-скт Гагарина 35", type3));
        typeMap.put(1,  AccidentType.of(1, "Две машины"));
        typeMap.put(2,  AccidentType.of(2, "Машина и человек"));
        typeMap.put(3,  AccidentType.of(3, "Машина и велосипед"));
    }

    public Collection<AccidentType> getValue() {
        return typeMap.values();
    }

    public void put(int id, Accident accident) {
        accidents.put(id, accident);
    }

    public void createResult(Accident accident) {
            accident.setId(size.getAndIncrement());
        accidents.put(accident.getId(), accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public Accident get(int id) {
        return accidents.get(id);
    }

    public void saveResult(Accident accident) {
        accident.setId(size.getAndIncrement());
        accidents.put(accident.getId(), accident);
    }

    public void saveResultType(AccidentType accidentType) {
        accidentType.setId(sizeType.getAndIncrement());
        typeMap.put(accidentType.getId(), accidentType);
    }

    public Collection<Accident> getResult() {
        return accidents.values();
    }

}
