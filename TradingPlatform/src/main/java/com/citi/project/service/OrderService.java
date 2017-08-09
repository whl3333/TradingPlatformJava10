package com.citi.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.project.entities.Order;
import com.citi.project.repository.OrderRespository;

@Service
public class OrderService
{
    @Autowired
    private OrderRespository orderRespository;
    
    public List<Order> find(){
        return orderRespository.findAll();
    }

    public Order insert (Order order)
    {
        return orderRespository.save(order);
    }

    public List<Order> findByTraderID (int traderID)
    {
        return orderRespository.findByTraderID(traderID);
    }

}
