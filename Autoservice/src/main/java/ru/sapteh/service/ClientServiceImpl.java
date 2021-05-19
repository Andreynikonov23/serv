package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.DAO;
import ru.sapteh.models.Client;
import ru.sapteh.models.ClientService;

import java.util.List;

public class ClientServiceImpl implements DAO<ClientService, Integer> {
    private final SessionFactory factory;

    public ClientServiceImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(ClientService clientService) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(clientService);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(ClientService clientService) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(clientService);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(ClientService clientService) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(clientService);
            session.getTransaction().commit();
        }
    }

    @Override
    public ClientService read(Integer id) {
        try (Session session = factory.openSession()) {
            return session.get(ClientService.class, id);
        }
    }

    @Override
    public List<ClientService> findByAll() {
        try(Session session = factory.openSession()) {
            Query<ClientService> query = session.createQuery("FROM ClientService");
            return query.list();
        }
    }
}
