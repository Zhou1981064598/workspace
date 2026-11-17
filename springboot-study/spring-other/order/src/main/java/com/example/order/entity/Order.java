package com.example.order.entity;

import org.springframework.stereotype.Component;

@Component
public class Order {
    private String name="Order name";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

