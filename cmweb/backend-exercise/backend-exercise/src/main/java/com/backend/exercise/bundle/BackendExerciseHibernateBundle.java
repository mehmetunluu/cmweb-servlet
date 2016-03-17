package com.backend.exercise.bundle;

import com.backend.exercise.BackendExerciseConfiguration;
import com.backend.exercise.hibernate.entity.BaseEntity;
import io.dropwizard.db.PooledDataSourceFactory;
import org.reflections.Reflections;

import javax.persistence.Entity;
import java.util.Set;

public class BackendExerciseHibernateBundle extends io.dropwizard.hibernate.HibernateBundle<BackendExerciseConfiguration> {

	@SuppressWarnings("rawtypes")
	public static BackendExerciseHibernateBundle initializeEntities() {
		 Reflections reflections = new Reflections("com.backend.exercise.hibernate.entity.impl");
		 Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Entity.class);

		Class[] classArr =new Class[classes.size()];
		classes.toArray(classArr);

		return new BackendExerciseHibernateBundle(BaseEntity.class,classArr);
	}


	protected BackendExerciseHibernateBundle(Class<?> entity, Class<?>... entities) {
		super(entity, entities);
	}
	

	@Override
	public PooledDataSourceFactory getDataSourceFactory(BackendExerciseConfiguration backendExerciseConfiguration) {
		return backendExerciseConfiguration.getDataSourceFactory();
	}

}
