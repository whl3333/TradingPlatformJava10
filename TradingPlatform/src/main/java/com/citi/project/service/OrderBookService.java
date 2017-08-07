package com.citi.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.project.entities.OrderBook;
import com.citi.project.repository.OrderBookRespository;


@Service
public class OrderBookService
{
    @Autowired
    private OrderBookRespository orderBookDAO;
    
    public List<OrderBook> find(){
        return orderBookDAO.findAll();
    }

}
