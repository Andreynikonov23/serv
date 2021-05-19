package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.DAO;
import ru.sapteh.models.Service;
import ru.sapteh.models.User;

import java.util.List;

public class UserServ implements DAO<User, Integer> {
    private final SessionFactory factory;

    public UserServ(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(User user) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(User user) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(User user) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public User read(Integer id) {
        try (Session session = factory.openSession()) {
            return session.get(User.class, id);
        }
    }

    @Override
    public List<User> findByAll() {
        try(Session session = factory.openSession()) {
            Query<User> query = session.createQuery("FROM User");
            return query.list();
        }
    }
}
