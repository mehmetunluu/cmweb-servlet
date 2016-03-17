package com.backend.exercise;
import com.backend.exercise.auth.BackendAuthenticator;
import com.backend.exercise.auth.BackendAuthorizer;
import com.backend.exercise.hibernate.entity.impl.User;
import com.backend.exercise.bundle.BackendExerciseHibernateBundle;
import com.backend.exercise.hibernate.dao.OrderDAO;
import com.backend.exercise.hibernate.dao.ProductDAO;
import com.backend.exercise.resources.BaseResource;
import com.backend.exercise.resources.OrderResource;
import com.backend.exercise.resources.ProductResource;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;

public class BackendExerciseApplication extends Application<BackendExerciseConfiguration> {
    public static void main(String[] args) throws Exception {
        new BackendExerciseApplication().run(args);
    }

    private static final BackendExerciseHibernateBundle hibernateBundle = BackendExerciseHibernateBundle.initializeEntities();

    @Override
    public String getName() {
        return "backend-exercise";
    }

    @Override
    public void initialize(Bootstrap<BackendExerciseConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );

        bootstrap.addBundle(new AssetsBundle());
        bootstrap.addBundle(new MigrationsBundle<BackendExerciseConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(BackendExerciseConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new ViewBundle<BackendExerciseConfiguration>() {
            @Override
            public Map<String, Map<String, String>> getViewConfiguration(BackendExerciseConfiguration configuration) {
                return configuration.getViewRendererConfiguration();
            }
        });
    }

    @Override
    public void run(BackendExerciseConfiguration configuration, Environment environment) {
        final OrderDAO orderDAO= new OrderDAO(hibernateBundle.getSessionFactory());
        final ProductDAO productDAO = new ProductDAO(hibernateBundle.getSessionFactory());

        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new BackendAuthenticator())
                .setAuthorizer(new BackendAuthorizer())
                .setRealm("SUPER SECRET STUFF")
                .buildAuthFilter()));

        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new OrderResource(orderDAO));
        environment.jersey().register(new ProductResource(productDAO));
        initializeDao(environment);
    }


    public void initializeDao(Environment environment){
        Reflections reflections = new Reflections("com.backend.exercise.resources");
        Set<Class<? extends BaseResource>> restClasses = reflections.getSubTypesOf(BaseResource.class);
        for(Class<? extends BaseResource> clazz : restClasses){
            try {
                environment.jersey().register(clazz.newInstance());
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            }
        }
    }

    public static BackendExerciseHibernateBundle getHibernateBundle() {
        return hibernateBundle;
    }
}
