package com.citi.project.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderbook")
public class OrderBook
    implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;

    private Integer orderID = new Integer(0);

    private String symbol;

    private char type;

    private double price;

    private int quantity;

    public OrderBook ()
    {
        // TODO Auto-generated constructor stub
    }

    public OrderBook (int id)
    {
        super ();
        this.id = id;
    }

    public OrderBook (Integer orderID, String symbol, char type, double price, int quantity)
    {
        super ();
        this.orderID = orderID;
        this.symbol = symbol;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public Integer getOrderID ()
    {
        return orderID;
    }

    public void setOrderID (Integer orderID)
    {
        this.orderID = orderID;
    }

    public String getSymbol ()
    {
        return symbol;
    }

    public void setSymbol (String symbol)
    {
        this.symbol = symbol;
    }

    public char getType ()
    {
        return type;
    }

    public void setType (char type)
    {
        this.type = type;
    }

    public double getPrice ()
    {
        return price;
    }

    public void setPrice (double price)
    {
        this.price = price;
    }

    public int getQuantity ()
    {
        return quantity;
    }

    public void setQuantity (int quantity)
    {
        this.quantity = quantity;
    }

    @Override
    public String toString ()
    {
        return "OrderBook [orderID=" + orderID + ", symbol=" + symbol + ", type=" + type + ", price=" + price
            + ", quantity=" + quantity + "]";
    }

}
