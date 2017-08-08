package com.citi.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.citi.project.entities.OrderBook;
import com.citi.project.service.OrderBookService;


@RestController

public class OrderBookController
{
    @Autowired
   private OrderBookService orderBookService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
   public List<OrderBook> getAll(HttpServletRequest req, HttpServletResponse res){
        
       return orderBookService.find();
   }
    
    @RequestMapping("/index")
   public ModelAndView  index(){
       return new ModelAndView("aaa");
   }
    
}
