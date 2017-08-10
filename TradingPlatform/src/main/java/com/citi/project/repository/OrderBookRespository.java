package com.citi.project.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.citi.project.entities.OrderBook;


public interface OrderBookRespository extends JpaRepository<OrderBook, Integer>
{
    List<OrderBook> findByType(char type, Sort sort);

    List<OrderBook> findByTypeAndSymbol (char type, String symbol, Sort sort);
}
