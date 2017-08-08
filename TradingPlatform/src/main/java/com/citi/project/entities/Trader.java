package com.citi.project.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Trader
{
    @Id
    @GeneratedValue
    private int id;
    private int tradeID;
    private String firstName;
    private String lastName;
    
    public Trader ()
    {
        // TODO Auto-generated constructor stub
    }

    public Trader (int id)
    {
        super ();
        this.id = id;
    }

    public Trader (int tradeID, String firstName, String lastName)
    {
        this.tradeID = tradeID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public int getTradeID ()
    {
        return tradeID;
    }

    public void setTradeID (int tradeID)
    {
        this.tradeID = tradeID;
    }

    public String getFirstName ()
    {
        return firstName;
    }

    public void setFirstName (String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName ()
    {
        return lastName;
    }

    public void setLastName (String lastName)
    {
        this.lastName = lastName;
    }

    @Override
    public String toString ()
    {
        return "Trader [tradeID=" + tradeID + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }

}

