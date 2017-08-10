package com.citi.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citi.project.entities.Execution;
import com.citi.project.entities.Order;
import com.citi.project.entities.OrderBook;
import com.citi.project.entities.ReturnData;
import com.citi.project.service.ExecutionService;
import com.citi.project.service.OrderService;

@RestController
@RequestMapping("/execution")
public class ExecutionController
{

    @Autowired
    private ExecutionService executionService;
    @Autowired
    private OrderService orderService;
    
    private List<Execution> list;
    
    @RequestMapping(value="/getAll",method = RequestMethod.GET)
    public List<Execution> getAll(){
        this.list = executionService.find();
        return list;
    }
    
    @RequestMapping(value="/getSymbols", method = RequestMethod.GET)
    public Map<Integer, String > getSymbols(){
        List<Order> olist = orderService.find ();
        Map<Integer, String> map = new HashMap<Integer, String>();
        for(Order order:olist){
            map.put (order.getId (), order.getSymbol ());
            
        }
        return map;
    }
     
//     @GetMapping("/getPerformance")
//    public ReturnData getPerformance(@RequestParam("traderID")int traderID){
//
//        List<Order> olist=new ArrayList<Order>();
//        List<Execution> elist = new ArrayList<Execution>();
//        int orderID;
//        
//        olist = orderController.getByTraderID (traderID);
//        if(olist==null){
//            return new ReturnData(0,"no executions",null);
//        }
//        for(Order order:olist){
//            orderID=order.getId ();
//            elist.addAll (executionService.getPerformance(orderID));
//        }
//        if(elist==null || olist==null){
//            return new ReturnData(0,"no executions",null);
//        }
//        return new ReturnData(0,"success",elist);
//    }
    
     public ReturnData insert(OrderBook orderBook, Boolean res){
         Execution execution=new Execution();
         execution.setOrderID (orderBook.getOrderID ());
         execution.setPrice (orderBook.getPrice ());
         execution.setQuantity (orderBook.getQuantity ());
         if(res){
             execution.setResult ("Fillings");
         }
         execution.setResult ("Rejections");
         Execution newExecution = executionService.insert(execution);
         if(newExecution==null){
             return new ReturnData(0, "Failed", null);
         }
         return new ReturnData(1, "Success", newExecution);
         
     }

}
