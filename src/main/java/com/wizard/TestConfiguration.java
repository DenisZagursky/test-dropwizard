package com.wizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Setter;
import org.dhatim.dropwizard.jwt.cookie.authentication.JwtCookieAuthConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Setter
public class TestConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @Valid
    @NotNull
    private JwtCookieAuthConfiguration jwtCookieAuth = new JwtCookieAuthConfiguration();

}
