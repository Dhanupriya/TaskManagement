package org.example;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.example.core.Task;
import org.example.db.TaskDAO;
import org.example.resources.TaskResource;

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
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final TaskManagementConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        final TaskDAO taskDAO = new TaskDAO(hibernateBundle.getSessionFactory());
        environment.jersey().register(new TaskResource(taskDAO));
    }

    private final HibernateBundle<TaskManagementConfiguration> hibernateBundle = new HibernateBundle<TaskManagementConfiguration>(Task.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(TaskManagementConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

}
