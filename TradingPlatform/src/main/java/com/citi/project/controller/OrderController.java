package com.citi.project.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citi.project.entities.Execution;
import com.citi.project.entities.Order;
import com.citi.project.entities.OrderBook;
import com.citi.project.entities.ReturnData;
import com.citi.project.service.OrderService;
import com.citi.project.utils.OrderType;

@RestController
@RequestMapping("/order")
public class OrderController
{
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderBookController obController = new OrderBookController();
    @Autowired
    private ExecutionController executionController=new ExecutionController();

    @RequestMapping(value="/getAll",method = RequestMethod.GET)
    public List<Order> getAll(){
        return orderService.find();
    }
    
    @RequestMapping("/getByTraderID")
    public List<Order> getByTraderID(@RequestParam("traderID")int traderID){
        return orderService.findByTraderID(traderID);
    }
    
    //@PostMapping("/execute")
    public ReturnData execute(
        @RequestParam("traderID")int traderID,
        @RequestParam("symbol")String symbol,
        @RequestParam("orderType")String orderType,
        @RequestParam("side")boolean side,
        @RequestParam("quantity")int quantity,
        @RequestParam("price")double price,
        @RequestParam("traderID")Timestamp cancelTime){
        
        Order order = new Order(traderID, symbol, orderType,side, quantity, price, new Timestamp(System.currentTimeMillis ()),cancelTime);        
        Order newOrder=orderService.insert(order);
        
        if(newOrder==null){
            return new ReturnData(0, "Failed", null);
        }
        //depends on orders`type to try
        List<OrderBook> oblist=new ArrayList<OrderBook>();
        List<Execution> elist=new ArrayList<Execution>();
        
        if(newOrder.isSide ()){
            oblist = obController.getAllSorted ('O');
        }
        switch(newOrder.getOrderType ()){
            case "Mrkt": elist = OrderType.ExecuteMrkt (newOrder, oblist);
            default:
        }
        
        if(executionController.isExecuted (newOrder)){
            //todo
        }
        return new ReturnData(1, "Success", newOrder);
    }

}
