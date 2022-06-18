package org.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.example.resources.HelloResource;

public class TaskManagementApplication extends Application<TaskManagementConfiguration> {

    public static void main(final String[] args) throws Exception {
        new TaskManagementApplication().run(args);
    }

    @Override
    public String getName() {
        return "TaskManagement";
    }

    @Override
    public void initialize(final Bootstrap<TaskManagementConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final TaskManagementConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        environment.jersey().register(new HelloResource());
    }

}
