package com.backend.exercise.hibernate.dao;

import com.backend.exercise.hibernate.entity.impl.Order;
import com.backend.exercise.hibernate.entity.impl.Product;
import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class OrderDAO extends AbstractDAO<Order> {
    public OrderDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Order> findById(Long id) {
        return Optional.fromNullable(get(id));
    }

    public Order create(Order order) {
        return persist(order);
    }

    public List<Order> findAll() {
        return list(namedQuery("Order.findAll"));
    }
}
