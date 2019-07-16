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
public class Fixture {
    private int id;
    private Team team1;
    private Team team2;
    private String stadium;
    private String town;
    private boolean isFirstLeg;
    private String weekend;
    public Fixture()
    {
    }
    public Fixture(Team team1,Team team2)
    {
        this.team1 = team1;
        this.team2 = team2;
        isFirstLeg = true;
        this.stadium = team1.getStadium();
        this.town = team1.getTown();
    }
    public Fixture(Team team1,Team team2,boolean isFirstLeg)
    {
        this.team1 = team1;
        this.team2 = team2;
        this.isFirstLeg = isFirstLeg;
        this.stadium = team2.getStadium();
        this.town = team2.getTown();
    }
    public int getId()
    {
        return id;
    }
    public void setTeam1(Team team)
    {
        this.team1 = team;
    }
    public Team getTeam1()
    {
        return team1;
    }
    public void setTeam2(Team team)
    {
        this.team2 = team;
    }
    public Team getTeam2()
    {
        return team2;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public void setLeg(boolean isFirstLeg)
    {
        this.isFirstLeg = isFirstLeg;
    }
    public boolean getIsFirstLeg()
    {
        return isFirstLeg ;
    }
    public void setStadium(String stadium)
    {
        this.stadium = stadium;
    }
    public String getStadium()
    {
        return stadium ;
    }
    public void setTown(String town)
    {
        this.town = town;
    }
    public String getTown()
    {
        return town ;
    }
    public void setWeekend(String weekend)
    {
        this.weekend = weekend;
    }
    public String getWeekend()
    {
        return weekend ;
    }
}
