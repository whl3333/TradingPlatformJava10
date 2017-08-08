package com.citi.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citi.project.entities.Execution;
import com.citi.project.service.ExecutionService;

@RestController
@RequestMapping("/executions")
public class ExecutionController
{

    @Autowired
    private ExecutionService executionService;

     @RequestMapping(method = RequestMethod.GET)
    public List<Execution> getAll(){

        return executionService.find();
    }

}
