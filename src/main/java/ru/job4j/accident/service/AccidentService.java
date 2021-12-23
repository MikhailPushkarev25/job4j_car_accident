package ru.job4j.accident.service;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.*;

@Service
public class AccidentService {

    private AccidentMem result;

    public AccidentService(AccidentMem result) {
        this.result = result;
    }

    public void create(Accident accident) {
        result.create(accident);
    }

    public Accident findById(int id) {
        return result.findById(id);
    }

    public Accident getAccident(int id) {
        return result.get(id);
    }

    public Collection<Accident> findAll() {
        return result.getAll();
    }

    public void save(Accident accident) {
        result.save(accident);
    }
}
