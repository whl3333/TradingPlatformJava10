package com.citi.project.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order 
{

    @Id
    @GeneratedValue
    private int id;
    private int traderID;
    private String symbol;
    private String orderType;
    private boolean side;
    private int quantity;
    private double price;
    private Timestamp createTime;
    private Timestamp cancelTime;

    public Order ()
    {
        // TODO Auto-generated constructor stub
    }

    public Order (int id)
    {
        super ();
        this.id = id;
    }

    public Order (int traderID, String symbol, String orderType, boolean side, int quantity, double price,
        Timestamp createTime, Timestamp cancelTime)
    {
        this.traderID = traderID;
        this.symbol = symbol;
        this.orderType = orderType;
        this.side = side;
        this.quantity = quantity;
        this.price = price;
        this.createTime = createTime;
        this.cancelTime = cancelTime;
    }

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public int getTraderID ()
    {
        return traderID;
    }

    public void setTraderID (int traderID)
    {
        this.traderID = traderID;
    }

    public String getSymbol ()
    {
        return symbol;
    }

    public void setSymbol (String symbol)
    {
        this.symbol = symbol;
    }

    public String getOrderType ()
    {
        return orderType;
    }

    public void setOrderType (String orderType)
    {
        this.orderType = orderType;
    }

    public boolean isSide ()
    {
        return side;
    }

    public void setSide (boolean side)
    {
        this.side = side;
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

    public Timestamp getCreateTime ()
    {
        return createTime;
    }

    public void setCreateTime (Timestamp createTime)
    {
        this.createTime = createTime;
    }

    public Timestamp getCancelTime ()
    {
        return cancelTime;
    }

    public void setCancelTime (Timestamp cancelTime)
    {
        this.cancelTime = cancelTime;
    }

    @Override
    public String toString ()
    {
        return "Order [traderID=" + traderID + ", symbol=" + symbol + ", orderType=" + orderType + ", side=" + side
            + ", quantity=" + quantity + ", price=" + price + ", createTime=" + createTime + ", cancelTime="
            + cancelTime + "]";
    }
}
