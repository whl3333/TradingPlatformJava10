package com.citi.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citi.project.entities.Order;

public interface OrderRespository  extends JpaRepository<Order, Integer>
{
    List<Order> findByTraderID(int traderID);
}
