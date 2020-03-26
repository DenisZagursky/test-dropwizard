package com.wizard;

import com.bendb.dropwizard.jooq.JooqBundle;
import com.google.inject.Stage;
import com.wizard.domain.Person;
import com.wizard.domain.Project;
import com.wizard.inject.BaseModule;
import com.wizard.inject.HibernateModule;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;
import org.dhatim.dropwizard.jwt.cookie.authentication.JwtCookieAuthBundle;
import ru.vyarus.dropwizard.guice.GuiceBundle;

@Slf4j
public class TestApplication extends Application<TestConfiguration> {

    public static void main(final String[] args) throws Exception {
        new TestApplication().run(args);
    }

    public final static JooqBundle<TestConfiguration> JOOQ_BUNDLE = new JooqBundle<>() {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(TestConfiguration testConfiguration) {
            return testConfiguration.getDataSourceFactory();
        }
    };

    public static final HibernateBundle<TestConfiguration> HIBERNATE_BUNDLE =
            new HibernateBundle<>(
                    Project.class,
                    Person.class
            ) {

                @Override
                public PooledDataSourceFactory getDataSourceFactory(TestConfiguration testConfiguration) {
                    return testConfiguration.getDataSourceFactory();
                }
            };

    public static final MigrationsBundle<TestConfiguration> MIGRATIONS_BUNDLE =
            new MigrationsBundle<>() {
                @Override
                public DataSourceFactory getDataSourceFactory(TestConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    public static final GuiceBundle GUICE_BUNDLE =
            GuiceBundle.builder()
                    .bundles()
                    .modules(new HibernateModule(HIBERNATE_BUNDLE), new BaseModule())
                    .enableAutoConfig(TestConfiguration.class.getPackage().getName())
                    .build(Stage.DEVELOPMENT);


    public static GuiceBundle getGuiceBundle() {
        return GUICE_BUNDLE;
    }

    @Override
    public String getName() {
        return "test";
    }

    @Override
    public void initialize(final Bootstrap<TestConfiguration> bootstrap) {
        bootstrap.addBundle(JOOQ_BUNDLE);
        bootstrap.addBundle(JwtCookieAuthBundle.getDefault());
        bootstrap.addBundle(HIBERNATE_BUNDLE);
        bootstrap.addBundle(GUICE_BUNDLE);
        bootstrap.addBundle(MIGRATIONS_BUNDLE);
    }


    @Override
    public void run(final TestConfiguration configuration,
                    final Environment environment) {
        log.info("###########################################");
        log.info("###########################################");
        log.info("###########################################");
        log.info("##APPLICATION STARTED, POWERED BY DZENYAA##");
        log.info("###########################################");
        log.info("###########################################");
        log.info("###########################################");
    }

}
