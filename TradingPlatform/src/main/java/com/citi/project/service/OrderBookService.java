package com.citi.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.citi.project.entities.Execution;
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

    public List<OrderBook> findByType (char type)
    {
        Sort sort;
    if(type=='B'){
        sort = new Sort(Sort.Direction.ASC,"price");
    }
    else if(type=='O'){
        sort = new Sort(Sort.Direction.DESC,"price");   
    }else{
        return null;
    }
        return orderBookDAO.findByType (type, sort);
    }

    public OrderBook insert (OrderBook orderBook)
    {
        return orderBookDAO.save (orderBook);
    }
    public void delete(OrderBook orderBook){
        //@throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
        orderBookDAO.delete (orderBook);
    }
    public void update(OrderBook orderBook){
        orderBookDAO.save (orderBook);
    }
}
