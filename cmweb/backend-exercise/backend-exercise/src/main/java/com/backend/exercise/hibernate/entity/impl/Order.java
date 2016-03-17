package com.backend.exercise.hibernate.entity.impl;

import com.backend.exercise.hibernate.entity.BaseEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name ="order")
public class Order extends BaseEntity {

    @Column(name = "products", nullable = false)
    @OneToMany
    private List<Product> products;

    @OneToOne
    private Person person;

    @Column(name="name")
    private String name;

    @Column(name = "order_date")
    private Date order_date;

    @Column(name = "delivery_date")
    private Date delivery_date;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PrePersist
    private void setDate(){
        create_date = new Date(Calendar.getInstance().getTimeInMillis());
        delivery_date =new Date(Calendar.getInstance().getTimeInMillis());
    }
}
