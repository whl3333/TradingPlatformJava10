package com.citi.project.utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.jpa.event.internal.jpa.ListenerFactoryBeanManagerDelayedImpl;

import com.citi.project.entities.Execution;
import com.citi.project.entities.Order;
import com.citi.project.entities.OrderBook;

public class OrderType {
		

	public static GTCReturn ExecuteMrkt(Order order, List<OrderBook> orderBooks) 
	{
	    List<Execution> executions = new ArrayList<Execution>();
        List<OrderBook> executeOrderBooks = new ArrayList<OrderBook>();
        GTCReturn res = new GTCReturn();
		int count = 0;
		int a = 0;
		int size = orderBooks.size();
		for (int i = 0; i < size; i++) {
			count+=orderBooks.get(i).getQuantity();
		}
		
		if(count<order.getQuantity())
		{
			Execution execution = new Execution(order.getId(), "rejection", order.getQuantity (), 0.0);
			executions.add(execution);
			res.setExecutions (executions);
			return res;
		}else
		{
			count = 0;
			for (int i = 0; i < size; i++) 
			{
				
				if(count>order.getQuantity())
					break;
				    Execution execution = new Execution(order.getId(), "filling",orderBooks.get(i).getQuantity(),orderBooks.get(i).getPrice());
					executions.add(execution);
					executeOrderBooks.add (orderBooks.get(i));
					count+=orderBooks.get(i).getQuantity();
				    a= i;
			}
			executions.get(a).setQuantity(orderBooks.get(a).getQuantity()-(count-order.getQuantity()));
			executeOrderBooks.get (a).setQuantity (count-order.getQuantity());
			
			res.setExecutions (executions);
			res.setOrderBooks (executeOrderBooks);
			return res;
		}
	}
	
	public static GTCReturn ExecuteIOC(Order order, List<OrderBook> orderBooks) 
	{
	    List<Execution> executions = new ArrayList<Execution>();
        List<OrderBook> executeOrderBooks = new ArrayList<OrderBook>();
        GTCReturn res = new GTCReturn();
		int count = 0;
		int a = 0;
		int size = orderBooks.size();
        for (int i = 0; i < size; i++) 
        {            
            if(count>=order.getQuantity())
                break;
            Execution execution = new Execution(order.getId(), "filling",orderBooks.get(i).getQuantity(),orderBooks.get(i).getPrice());
                executions.add(execution);
                executeOrderBooks.add (orderBooks.get(i));
                count+=orderBooks.get(i).getQuantity();
            a= i;
        }
        int rest=(count-order.getQuantity())<0? 0:count-order.getQuantity();
        executions.get(a).setQuantity(orderBooks.get(a).getQuantity()-rest);
        executeOrderBooks.get(a).setQuantity (rest);
        if(rest==0){
            Execution execution = new Execution(order.getId(), "rejection",order.getQuantity()-count,0.0);
            executions.add (execution);
        }
       
        res.setExecutions (executions);
        res.setOrderBooks (executeOrderBooks);
        return res;
	
	}
	
	public static GTCReturn ExecuteFOK(Order order, List<OrderBook> orderBooks) 
	{
	    List<Execution> executions = new ArrayList<Execution>();
        List<OrderBook> executeOrderBooks = new ArrayList<OrderBook>();
        GTCReturn res = new GTCReturn();
		double price = order.getPrice();
		
		for (int i = 0; i <orderBooks.size(); i++) {
			if(price==orderBooks.get(i).getPrice() && order.getQuantity()<=orderBooks.get(i).getQuantity())
			{
			    int rest = order.getQuantity()> orderBooks.get(i).getQuantity()?orderBooks.get(i).getQuantity():order.getQuantity();
				Execution execution = new Execution(order.getId(), "filling", rest, price);
				executions.add(execution);
				executeOrderBooks.add(orderBooks.get (i));
				executeOrderBooks.get (i).setQuantity (orderBooks.get (i).getQuantity ()-order.getQuantity());
				 res.setExecutions (executions);
			     res.setOrderBooks (executeOrderBooks);
			     return res;
			}
			
		}
		Execution execution = new Execution(order.getId(), "rejection", order.getQuantity (), 0);
		executions.add(execution);
		res.setExecutions (executions);
		return res;
	}
	
	public static GTCReturn ExecuteGTC(Order order, List<OrderBook> orderBooks) 
	{
	    List<Execution> executions = new ArrayList<Execution>();
        List<OrderBook> executeOrderBooks = new ArrayList<OrderBook>();
        GTCReturn res = new GTCReturn();
		double price = order.getPrice();
		for (int i = 0; i <orderBooks.size(); i++) {
			if(price==orderBooks.get(i).getPrice()&&order.getQuantity()<=orderBooks.get(i).getQuantity())
			{
			    int rest = order.getQuantity()> orderBooks.get(i).getQuantity()?orderBooks.get(i).getQuantity():order.getQuantity();
				Execution execution = new Execution(order.getId(), "filling", rest, price);
				executions.add(execution);
                executeOrderBooks.add(orderBooks.get (i));
                executeOrderBooks.get (i).setQuantity (orderBooks.get (i).getQuantity ()-order.getQuantity());
                 res.setExecutions (executions);
                 res.setOrderBooks (executeOrderBooks);
				return res;
			}
		}
		char type = (order.isSide()?'O':'B');
		OrderBook orderBook = new OrderBook(order.getId(),order.getSymbol(),type,price, order.getQuantity());
		executeOrderBooks.add (orderBook);
		res.setOrderBooks (executeOrderBooks);
		return res;
	}
}
