package com.citi.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citi.project.entities.Orders;

public interface OrderRespository  extends JpaRepository<Orders, Integer>
{

}
