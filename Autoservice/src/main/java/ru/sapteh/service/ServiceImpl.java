package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.DAO;
import ru.sapteh.models.Client;
import ru.sapteh.models.Service;

import java.util.List;

public class ServiceImpl implements DAO<Service, Integer> {
    private final SessionFactory factory;

    public ServiceImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(Service service) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(service);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Service service) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(service);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Service service) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(service);
            session.getTransaction().commit();
        }
    }

    @Override
    public Service read(Integer id) {
        try (Session session = factory.openSession()) {
            return session.get(Service.class, id);
        }
    }

    @Override
    public List<Service> findByAll() {
        try(Session session = factory.openSession()) {
            Query<Service> query = session.createQuery("FROM Service");
            return query.list();
        }
    }
}
