package com.wizard.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.wizard.TestConfiguration;
import com.wizard.dao.GenericDAO;
import com.wizard.dao.hibernate.ProjectHibernateDAO;
import com.wizard.domain.Person;
import com.wizard.dao.hibernate.PersonHibernateDAO;
import com.wizard.domain.Project;
import io.dropwizard.hibernate.HibernateBundle;

public class HibernateModule extends AbstractModule {

    private final HibernateBundle<TestConfiguration> hibernateBundle;

    public HibernateModule(HibernateBundle<TestConfiguration> hibernateBundle) {
        this.hibernateBundle = hibernateBundle;
    }

    @Override
    protected void configure() {
        bind(new TypeLiteral<GenericDAO<Person>>() {
        }).to(PersonHibernateDAO.class);
        bind(new TypeLiteral<GenericDAO<Project>>() {
        }).to(ProjectHibernateDAO.class);
    }

    @Provides
    public ProjectHibernateDAO provideProjectHibernateDAO() {
        return new ProjectHibernateDAO(hibernateBundle.getSessionFactory());
    }

    @Provides
    public PersonHibernateDAO providePersonHibernateDAO() {
        return new PersonHibernateDAO(hibernateBundle.getSessionFactory());
    }

}
