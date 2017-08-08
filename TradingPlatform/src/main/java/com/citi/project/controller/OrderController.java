package com.citi.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citi.project.entities.Order;
import com.citi.project.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController
{
    @Autowired
    private OrderService orderService;

     @RequestMapping(method = RequestMethod.GET)
    public List<Order> getAll(){

        return orderService.find();
    }

}
