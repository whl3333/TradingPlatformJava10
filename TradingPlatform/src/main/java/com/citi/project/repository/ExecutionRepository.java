package com.citi.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citi.project.entities.Execution;

public interface ExecutionRepository extends JpaRepository<Execution, Integer>
{

}
