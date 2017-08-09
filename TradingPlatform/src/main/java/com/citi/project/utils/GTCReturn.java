package com.citi.project.utils;

import java.util.List;

import com.citi.project.entities.Execution;
import com.citi.project.entities.OrderBook;
public class GTCReturn
{

    private List<Execution> executions=null;
    private List<OrderBook> orderBooks=null;
    
    public GTCReturn ()
    {
        super ();
    }
    public GTCReturn (List<Execution> executions, List<OrderBook> orderBooks)
    {
        super ();
        this.executions = executions;
        this.orderBooks = orderBooks;
    }
    public List<Execution> getExecutions ()
    {
        return executions;
    }
    public void setExecutions (List<Execution> executions)
    {
        this.executions = executions;
    }
    public List<OrderBook> getOrderBooks ()
    {
        return orderBooks;
    }
    public void setOrderBooks (List<OrderBook> orderBooks)
    {
        this.orderBooks = orderBooks;
    }
   

}
