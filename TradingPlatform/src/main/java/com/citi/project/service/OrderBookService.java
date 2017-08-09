package com.citi.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    public List<OrderBook> find (Sort sort)
    {
        return orderBookDAO.findAll (sort);
    }

    public List<OrderBook> findByType (char type, Sort sort)
    {
        // TODO Auto-generated method stub
        return orderBookDAO.findByType (type, sort);
    }

    public OrderBook insert (OrderBook orderBook)
    {
        // TODO Auto-generated method stub
        return orderBookDAO.save (orderBook);
    }

}
