package com.citi.project.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="trader")
public class Trader
{
    @Id
    @GeneratedValue
    private int id;
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
    
    public Trader (int id, String firstName, String lastName)
    {
        super ();
        this.id = id;
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
        return "Trader [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }
    

  }

