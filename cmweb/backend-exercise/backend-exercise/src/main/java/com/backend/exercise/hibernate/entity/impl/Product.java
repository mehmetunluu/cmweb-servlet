package com.backend.exercise.hibernate.entity.impl;

import com.backend.exercise.hibernate.entity.BaseEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;

@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name ="amount",nullable = false)
    Double amount;

    public Product(String name,Double amount) {
        this.name=name;
        this.amount=amount;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    @PrePersist
    private void setDate(){
        create_date = new Date(Calendar.getInstance().getTimeInMillis());
    }

}
