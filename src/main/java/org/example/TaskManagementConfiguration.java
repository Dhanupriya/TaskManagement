package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class TaskManagementConfiguration extends Configuration {
    // TODO: implement service configuration
    @Valid
    @NotNull
    @JsonProperty
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
}
