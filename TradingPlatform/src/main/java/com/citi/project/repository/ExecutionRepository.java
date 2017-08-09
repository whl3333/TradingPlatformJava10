package com.citi.project.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.citi.project.entities.Execution;

public interface ExecutionRepository extends JpaRepository<Execution, Integer>
{
    List<Execution> findByOrderID(int orderID);
}
