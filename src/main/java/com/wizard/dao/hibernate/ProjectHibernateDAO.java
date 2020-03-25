package com.wizard.dao.hibernate;

import com.google.inject.Inject;
import com.wizard.dao.BaseHibernateDAO;
import com.wizard.dao.GenericDAO;
import com.wizard.domain.Project;
import org.hibernate.SessionFactory;

public class ProjectHibernateDAO extends BaseHibernateDAO<Project> implements GenericDAO<Project> {
    @Inject
    public ProjectHibernateDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
