/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetable;

/**
 *
 * @author jmuto
 */
public class Team {
    private int id;
    private String name;
    private String town;
    private String stadium;
    public Team()
    {
    }
    public Team(String name, String town, String stadium)
    {
        this.name = name;
        this.town = town;
        this.stadium = stadium;
    }
    public Team(int id,String name, String town, String stadium)
    {
        this.id = id;
        this.name = name;
        this.town = town;
        this.stadium = stadium;
    }
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getTown()
    {
        return town;
    }
    public void setTown(String town)
    {
        this.town = town;
    }
    public String getStadium()
    {
        return stadium;
    }
    public void setStadium(String stadium)
    {
        this.stadium = stadium;
    }
}
