package com.citi.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citi.project.entities.Order;

public interface OrderRespository  extends JpaRepository<Order, Integer>
{

}
