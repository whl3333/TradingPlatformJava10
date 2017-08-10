package com.citi.project.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
import com.citi.project.service.ExecutionService;
import com.citi.project.service.OrderBookService;
import com.citi.project.service.OrderService;
import com.citi.project.utils.GTCReturn;
import com.citi.project.utils.OrderType;

@RestController
@RequestMapping("/order")
public class OrderController
{
    @Autowired
    private OrderService orderService;
    @Autowired
    private ExecutionService executionService=new ExecutionService();
    @Autowired
    private OrderBookService orderBookService=new OrderBookService();

    @RequestMapping(value="/getAll",method = RequestMethod.GET)
    public List<Order> getAll(){
        return orderService.find();
    }
    
    @RequestMapping("/getByTraderID")
    public List<Order> getByTraderID(@RequestParam("traderID")int traderID){
        return orderService.findByTraderID(traderID);
    }
    
    @RequestMapping("/execute")
    public ReturnData execute(
        @RequestParam(value ="traderID",required = false, defaultValue="0")int traderID,

        @RequestParam(value ="symbol",required = false, defaultValue="HRB")String symbol,

        @RequestParam(value ="orderType",required = false, defaultValue="MRKT")String ordertype,
        @RequestParam(value ="side",required = false, defaultValue="0")boolean side,
        @RequestParam(value ="quantity",required = false, defaultValue="0")int quantity,
        @RequestParam(value = "price",required = false, defaultValue="0")double price,
        @RequestParam(value = "cancelTime",required = false, defaultValue="2014-05-20 21:33:00")Timestamp cancelTime){
        
        Order order = new Order(traderID, symbol, ordertype,side, quantity, price, new Timestamp(System.currentTimeMillis ()),cancelTime);        
        Order newOrder=orderService.insert(order);
        
        if(newOrder==null){
           System.out.println (1);
            return new ReturnData(0, "Failed", null);
        }
        //depends on orders`type to try
        List<OrderBook> oblist=new ArrayList<OrderBook>();
        List<Execution> elist=new ArrayList<Execution>();
        
        if(newOrder.isSide ()){
            oblist = orderBookService.findByTypeAndSymbol ('O',order.getSymbol());
        }else{
            oblist = orderBookService.findByTypeAndSymbol('B',order.getSymbol());
        }
        
        GTCReturn res = new GTCReturn();
        switch(newOrder.getOrderType ()){
            case "MRKT": {
                res = OrderType.ExecuteMrkt (newOrder, oblist);
                break;
            }
            case "IOC":{
                res = OrderType.ExecuteIOC (newOrder, oblist);   
                break;
            }
            case "FOK":{
                res = OrderType.ExecuteFOK (newOrder, oblist);
                break;
            }
            case "GTC":{
                res = OrderType.ExecuteGTC (newOrder, oblist);
                break;
            }
            default: break;
        }
        
//        if(executionController.isExecuted (newOrder)){
//            //todo
//        }
        elist = res.getExecutions ();
        oblist = res.getOrderBooks ();

        if(oblist!=null){
            for(int i = 0; i<oblist.size (); i++){
                orderBookService.delete (oblist.get (i));
            }
            orderBookService.update (oblist.get (oblist.size ()-1));
        }
        
        if(elist!=null){
            for(Execution execution:elist){
                executionService.insert (execution);
              
            }
        }else if( newOrder.getOrderType ().equals ("GTC")){
            return new ReturnData(0, "ACTIVE", null);
        }else{
            return new ReturnData(0, "DONE NOTHING", null);
        }
        return new ReturnData(1, "DONE", elist);
    }

}
