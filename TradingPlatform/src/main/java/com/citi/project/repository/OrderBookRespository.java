package com.citi.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citi.project.entities.OrderBook;


public interface OrderBookRespository extends JpaRepository<OrderBook, Integer>
{

}
