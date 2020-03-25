package com.wizard.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.Optional;

public abstract class BaseHibernateDAO<T> extends AbstractDAO<T> {

    public BaseHibernateDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public T create(T obj) {
        return persist(obj);
    }

    public Optional<T> findById(Long id) {
        return id != null
                ? Optional.ofNullable(get(id))
                : Optional.empty();
    }

    public T update(T obj) {
        T target = persist(obj);
        currentSession().flush();
        return target;
    }

    public void delete(T source) {
        currentSession().delete(source);
    }

    public void reload(T object) {
        currentSession().flush();
        currentSession().refresh(object);
    }

}
