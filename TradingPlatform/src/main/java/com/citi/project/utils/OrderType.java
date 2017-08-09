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
		

	public static List<Execution> ExecuteMrkt(Order order, List<OrderBook> orderBooks) 
	{
		List<Execution> executions = new ArrayList<Execution>();
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
			return executions;
		}else
		{
			count = 0;
			for (int i = 0; i < size; i++) 
			{
				
				if(count>order.getQuantity())
					break;
				Execution execution = new Execution(order.getId(), "filling",orderBooks.get(i).getQuantity(),orderBooks.get(i).getPrice());
					executions.add(execution);
				count+=orderBooks.get(i).getQuantity();
				a= i;
			}
			executions.get(a).setQuantity(executions.get(a).getQuantity()-(count-order.getQuantity()));
			return executions;
		}
	}
	
	public static List<Execution> ExecuteIOC(Order order, List<OrderBook> orderBooks) 
	{
		List<Execution> executions = new ArrayList<Execution>();
		int count = 0;
		int a = 0;
		int size = orderBooks.size();
		for (int i = 0; i < size; i++) {
		
			count+=orderBooks.get(i).getQuantity();
		}
		
		if(count<order.getQuantity())
		{
			 count = 0;
				for (int i = 0; i < size; i++) 
				{
					Execution execution = new Execution(order.getId(), "filling",orderBooks.get(i).getQuantity(),orderBooks.get(i).getPrice());
						executions.add(execution);
					count+=orderBooks.get(i).getQuantity();
				}
				return executions;
		}
		else
		{
			 count = 0;
			for (int i = 0; i < size; i++) 
			{
				
				if(count>order.getQuantity())
					break;
				Execution execution = new Execution(order.getId(), "success",orderBooks.get(i).getQuantity(),orderBooks.get(i).getPrice());
					executions.add(execution);
				count+=orderBooks.get(i).getQuantity();
				a= i;
			}
			executions.get(a).setQuantity(executions.get(a).getQuantity()-(count-order.getQuantity()));
			return executions;
		}
	}
	
	public static List<Execution> ExecuteFOK(Order order, List<OrderBook> orderBooks) 
	{
		List<Execution> executions = new ArrayList<Execution>();
		double price = order.getPrice();
		for (int i = 0; i <orderBooks.size(); i++) {
			if(price==orderBooks.get(i).getPrice()&&order.getQuantity()<=orderBooks.get(i).getQuantity())
			{
				Execution execution = new Execution(order.getId(), "success", order.getQuantity(), price);
				executions.add(execution);
			return executions;
			}
			
		}
		Execution execution = new Execution(order.getId(), "failure", 0, 0);
		executions.add(execution);
		return executions;
	}
	
	public static HashMap<ArrayList<Execution>,OrderBook> ExecuteGTC(Order order, List<OrderBook> orderBooks) 
	{
		ArrayList<Execution> executions = new ArrayList<Execution>();
		double price = order.getPrice();
		for (int i = 0; i <orderBooks.size(); i++) {
			if(price==orderBooks.get(i).getPrice()&&order.getQuantity()<=orderBooks.get(i).getQuantity())
			{
				Execution execution = new Execution(order.getId(), "success", order.getQuantity(), price);
				executions.add(execution);
				HashMap<ArrayList<Execution>,OrderBook> hashMap = new HashMap<>();
				hashMap.put((ArrayList<Execution>) executions, null);
			return hashMap;
			}
			
		}
		char type = (order.isSide()?'o':'b');
		OrderBook orderBook = new OrderBook(order.getId(),order.getSymbol(),type,price, order.getQuantity());
		Execution execution = new Execution(order.getId(), "failure", 0, 0);
		executions.add(execution);
		HashMap<ArrayList<Execution>,OrderBook> hashMap = new HashMap<>();
		hashMap.put((ArrayList<Execution>) executions, orderBook);
		return hashMap;
	}
}
