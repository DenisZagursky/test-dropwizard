package com.wizard.dao.hibernate;

import com.google.inject.Inject;
import com.wizard.dao.BaseHibernateDAO;
import com.wizard.dao.GenericDAO;
import com.wizard.domain.Person;
import org.hibernate.SessionFactory;

public class PersonHibernateDAO extends BaseHibernateDAO<Person> implements GenericDAO<Person> {
    @Inject
    public PersonHibernateDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
