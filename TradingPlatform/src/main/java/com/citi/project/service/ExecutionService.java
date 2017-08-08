package com.citi.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.project.entities.Execution;
import com.citi.project.repository.ExecutionRepository;

@Service
public class ExecutionService
{

    @Autowired
    private ExecutionRepository executionRepository;
    
    public List<Execution> find(){
       return  executionRepository.findAll ();
    }

}
