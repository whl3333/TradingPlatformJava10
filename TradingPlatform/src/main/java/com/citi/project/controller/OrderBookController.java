package com.citi.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citi.project.entities.OrderBook;
import com.citi.project.service.OrderBookService;


@RestController
@RequestMapping("/orderbooks")
public class OrderBookController
{
    @Autowired
   private OrderBookService orderBookService;

    @RequestMapping(method = RequestMethod.GET)
   public List<OrderBook> getAll(){
//       List<OrderBook> list =  orderBookService.find();
//       for(int i=0; i<list.size (); i++){
//           list.get (i).setOrderID (0);
//       }
//       return list;
       return orderBookService.find();
   }
}
