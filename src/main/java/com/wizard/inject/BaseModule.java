package com.wizard.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.wizard.TestApplication;
import com.wizard.web.resource.AuthApi;
import com.wizard.web.resource.ProjectApi;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class BaseModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ProjectApi.class).in(Singleton.class);
        bind(AuthApi.class).in(Singleton.class);
    }

    @Provides
    public GuiceBundle provideGuiceBundle() {
        return TestApplication.getGuiceBundle();
    }
}
