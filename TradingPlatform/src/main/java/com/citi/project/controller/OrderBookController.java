package com.citi.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.citi.project.entities.OrderBook;
import com.citi.project.service.OrderBookService;


@RestController

public class OrderBookController
{
    @Autowired
   private OrderBookService orderBookService;

  
    @RequestMapping(value ="/orderbook",method = RequestMethod.GET)
   public List<OrderBook> getBySymbol(@RequestParam (value = "symbol") String symbol){
      
       return orderBookService.findBySymbol (symbol);
   }

    @RequestMapping("/orderbooks")
    public List<OrderBook> getAll(){
        
        return orderBookService.find ();
    }
     
    
    @RequestMapping("/index")
   public ModelAndView  index(){
       return new ModelAndView("index");
   }
    
}
