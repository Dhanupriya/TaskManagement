package org.example;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.example.core.Task;
import org.example.db.TaskDAO;
import org.example.resources.TaskResource;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class TaskManagement extends Application<TaskManagementConfiguration> {

    public static void main(final String[] args) throws Exception {
        new TaskManagement().run(args);
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

        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

    private final HibernateBundle<TaskManagementConfiguration> hibernateBundle = new HibernateBundle<TaskManagementConfiguration>(Task.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(TaskManagementConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

}
