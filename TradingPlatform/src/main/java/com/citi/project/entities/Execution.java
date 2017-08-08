package com.citi.project.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table (name="execution")
public class Execution
{   
    @Id
    @GeneratedValue
    private int id;
    private int orderID;
    private String result;
    private int quantity;
    private double price;
    
    public Execution ()
    {
        // TODO Auto-generated constructor stub
    }

    public Execution (int id)
    {
        super ();
        this.id = id;
    }

    public Execution (int orderID, String result, int quantity, double price)
    {
        this.orderID = orderID;
        this.result = result;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public int getOrderID ()
    {
        return orderID;
    }

    public void setOrderID (int orderID)
    {
        this.orderID = orderID;
    }

    public String getResult ()
    {
        return result;
    }

    public void setResult (String result)
    {
        this.result = result;
    }

    public int getQuantity ()
    {
        return quantity;
    }

    public void setQuantity (int quantity)
    {
        this.quantity = quantity;
    }

    public double getPrice ()
    {
        return price;
    }

    public void setPrice (double price)
    {
        this.price = price;
    }

    @Override
    public String toString ()
    {
        return "Execution [orderID=" + orderID + ", result=" + result + ", quantity=" + quantity + ", price=" + price
            + "]";
    }

}
