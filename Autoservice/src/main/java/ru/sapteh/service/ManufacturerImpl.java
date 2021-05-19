package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.DAO;
import ru.sapteh.models.Client;
import ru.sapteh.models.Manufacturer;

import java.util.List;

public class ManufacturerImpl implements DAO<Manufacturer, Integer> {
    private final SessionFactory factory;

    public ManufacturerImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(Manufacturer manufacturer) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(manufacturer);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Manufacturer manufacturer) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(manufacturer);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Manufacturer manufacturer) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(manufacturer);
            session.getTransaction().commit();
        }
    }

    @Override
    public Manufacturer read(Integer id) {
        try (Session session = factory.openSession()) {
            return session.get(Manufacturer.class, id);
        }
    }

    @Override
    public List<Manufacturer> findByAll() {
        try(Session session = factory.openSession()) {
            Query<Manufacturer> query = session.createQuery("FROM Manufacturer");
            return query.list();
        }
    }
}
