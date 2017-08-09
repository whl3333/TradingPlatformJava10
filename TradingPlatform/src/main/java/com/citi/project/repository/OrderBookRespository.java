package com.citi.project.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.citi.project.entities.OrderBook;


public interface OrderBookRespository extends JpaRepository<OrderBook, Integer>
{
    List<OrderBook> findBySymbol(String symbol);
//    @Transactional
//    @Query("select o from orderbook o where o.type = 'B' and o.symbol = 'symbol' order by o.price")
//    List<OrderBook> findBidBySymbol(String symbol);
}
