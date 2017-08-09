package com.citi.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.citi.project.entities.Execution;
import com.citi.project.entities.Order;
import com.citi.project.entities.OrderBook;
import com.citi.project.entities.ReturnData;
import com.citi.project.service.OrderBookService;


@RestController
@RequestMapping("/orderbook")
public class OrderBookController
{
    @Autowired
   private OrderBookService orderBookService;
    
   private List<OrderBook> list;
   
   @GetMapping(value="/getAll")
  public List<OrderBook> getAll(){
       Sort sort = new Sort(Sort.Direction.DESC,"price");
       this.list=orderBookService.find (sort);
       return this.list;
  }
   
   @GetMapping(value="/getAllSorted")
   public List<OrderBook> getAllSorted(@RequestParam(value="type",required=false,defaultValue="B") char type){
       
       List<OrderBook> typeList=orderBookService.findByType (type);
       return typeList;
   }
   
   @GetMapping(value="/getSymbols")
   public List<String> getSymbols(){  
       List<String> symbols = new ArrayList<String>();
       for(OrderBook o:this.list){
           symbols.add (o.getSymbol ());
           }
       return symbols;
   }
   
//   @GetMapping(value="/insert")
   public ReturnData insert(Order order){
       OrderBook orderBook=new OrderBook();
       orderBook.setOrderID (order.getId ());
       orderBook.setPrice (order.getPrice ());
       orderBook.setQuantity (order.getQuantity ());
       orderBook.setSymbol (order.getSymbol ());
       if (order.isSide ()){
           orderBook.setType ('B');
       }else{
           orderBook.setType ('O');
       }
       OrderBook newOrderbook=orderBookService.insert(orderBook);
       if(newOrderbook==null){
           return new ReturnData(0, "Failed", null);
       }
       return new ReturnData(1, "Success", newOrderbook);
   }
   
   public ReturnData delete(OrderBook orderBook){
       
       return null;
   }
}
