package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "trade")  
public class Trade {
    @Id  
    @GeneratedValue(strategy = GenerationType.AUTO)  
    private int id;
    
    @Column(name = "content")  
    private String content;

    public Trade(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public String getContent ()
    {
        return content;
    }

    public void setContent (String content)
    {
        this.content = content;
    }

    
}