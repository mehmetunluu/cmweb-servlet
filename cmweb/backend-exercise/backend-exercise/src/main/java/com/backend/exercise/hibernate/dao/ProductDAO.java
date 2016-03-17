package com.backend.exercise.hibernate.dao;

import com.backend.exercise.hibernate.entity.impl.Person;
import com.backend.exercise.hibernate.entity.impl.Product;
import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class ProductDAO extends AbstractDAO<Product> {
    public ProductDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Product> findById(Long id) {
        return Optional.fromNullable(get(id));
    }

    public Product create(Product product) {
        return persist(product);
    }

    public List<Product> findAll() {
        return list(namedQuery("Person.findAll"));
    }
}
