package com.example.order.controller;

import com.example.cart.entity.Cart;
import com.example.order.entity.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class Ordercontroller {
    @Resource
    Order order;
    @Resource
    Cart cart;
    @GetMapping("/order")
    public Order order(){
         return order;
    }
    public Cart cart(){
        return cart;
    }
}
