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

    public void saveAccident(int id, Accident accident) {
        result.put(id, accident);
    }

    public Accident getAccident(int id) {
        return result.get(id);
    }

    public Map<Integer, Accident> findAll() {
        return result.getAll();
    }
}
