package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;
import java.util.Set;

@Repository
public class AccidentHibernate {

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident save(Accident accident) {
        try (Session session = sf.openSession()) {
            Set<Rule> ruleSet = accident.getRules();
            for (Rule rule : ruleSet) {
                session.save(rule);
            }
            session.save(accident);
            return accident;
        }
    }

    public List<Accident> getAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Accident", Accident.class)
                    .list();
        }
    }

    public List<AccidentType> getAllType() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from AccidentType ", AccidentType.class)
                    .list();
        }
    }

    public List<Rule> getAllRule() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Rule ", Rule.class)
                    .list();
        }
    }

    public Accident findByIdAccident(int id) {
        try (Session session = sf.openSession()) {
            return session.createQuery(
                    "select distinct a from Accident a join fetch a.rules where a.id = :id",
                    Accident.class
            ).setParameter("id", id).uniqueResult();
        }
    }

    public AccidentType findByIdType(int id) {
        try (Session session = sf.openSession()) {
            return session.get(AccidentType.class, id);
        }
    }

    public Rule findByIdRule(int id) {
        try (Session session = sf.openSession()) {
            return session.get(Rule.class, id);
        }
    }
}
