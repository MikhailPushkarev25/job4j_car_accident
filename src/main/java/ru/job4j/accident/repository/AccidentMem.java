package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();
    private AtomicInteger size = new AtomicInteger(4);

    public AccidentMem() {
        accidents.put(1, new Accident(1, "Михаил", "Превышение скорости", "пр-кт Ленина 32"));
        accidents.put(2, new Accident(2, "Роман", "Не пропустил пешехода", "ул. Баумана 100"));
        accidents.put(3, new Accident(3, "Василий", "совершил ДТП", "пр-скт Гагарина 35"));
    }

    public void put(int id, Accident accident) {
        accidents.put(id, accident);
    }

    public void create(Accident accident) {
            accident.setId(size.getAndIncrement());
        accidents.put(accident.getId(), accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public Accident get(int id) {
        return accidents.get(id);
    }

    public Collection<Accident> getAll() {
        return accidents.values();
    }

}
