/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jmuto
 */
public class MainAplication {
    private static Scanner  in = new Scanner(System.in); 
    private static final String fileName = "teams.csv";
    public static void main(String args[]) throws IOException
    {
        int selectedMenu = selectMenu();     
        switch(selectedMenu)
        {
            case 1:
               getTeamsFromUser();
               displayTeams();
                break;
            case 2:
                generateFixtures();
                break;
            case 3:
              displayTeams();
            case 4:
                System.exit(0);
                break;
            default:
               System.out.println("Not supported menu"); 
                
        }
    }
    public static int selectMenu()
    {
        System.out.println("Select menu\n1. Input teams(10)\n2. Generate fixtures\n3. Display teams\n4. Exit");
        return Integer.parseInt(in.next());
    }
    public static void getTeamsFromUser() throws IOException
    {
         System.out.println("Input teams");
                List<Team> teams = new ArrayList<Team>();
                int teamsCounter = 1;
                String pass = in.nextLine();//prompt console to start accepting input
                while(teamsCounter <= 10)
                {
                    Team teamToAdd = new Team();
                    System.out.println("Team " + teamsCounter);
                    System.out.println("Name");
                    teamToAdd.setName(in.nextLine());
                    System.out.println("Town");
                    teamToAdd.setTown(in.nextLine());
                    System.out.println("Stadium");
                    teamToAdd.setStadium(in.nextLine());
                    teams.add(teamToAdd);
                    teamsCounter++;
                }
                CSVHelper.saveCsvFile(teams, fileName);
    }
    public static void displayTeams() throws IOException
    {
        List<Team> teams =   CSVHelper.readTeamsFromCsvFile(fileName);
              System.out.println("Teams name ----------- Local Town ---------- Team Stadium"); 
              for(Team team : teams)
              {
                  System.out.println( team.getName() + "--------" +team.getTown()+ "-------"+team.getStadium()); 
              }
    }
    public static void generateFixtures() throws IOException
    {
       List<Fixture> fixtures = generateCombination();
       List<Fixture> homeTownfixtures = new ArrayList<>();
       int week = 1;
       boolean isSameWeekend = false;
       int counter = 0;
       while(counter<fixtures.size())
       {
           if(fixtures.get(counter).getTeam1().getTown().equals(fixtures.get(counter).getTeam2().getTown()))
           {
               homeTownfixtures.add(fixtures.get(counter));
               fixtures.remove(counter);
               continue;
           }
           else
           {
           if(!isSameWeekend)
           {
               fixtures.get(counter).setWeekend("Weekend" +week);
               
               isSameWeekend = true;
           }
           else
           {
                fixtures.get(counter).setWeekend("Weekend" +week);
                isSameWeekend = false;
                week++;
           }
           }
           counter++;
       }
       for(Fixture fixture: homeTownfixtures)
       {
           if(!isSameWeekend)
           {
               fixture.setWeekend("Weekend" +week);
               
               isSameWeekend = true;
           }
           else
           {
                fixture.setWeekend("Weekend" +week);
                isSameWeekend = false;
                week++;
           }
       }
       fixtures.addAll(homeTownfixtures);
       fixtures.addAll(setSecondLeg(fixtures, week));
        displayFixtures(fixtures);
    }
    public static void displayFixtures(List<Fixture> fixtures)
    {
        System.out.println("Id-----Team1------- Team2----- Town --- Stadium ---- Weekend ---- IsFirstLeg"); 
        int id = 1;
              for(Fixture fixture : fixtures)
              {
                  System.out.println(id+"----"+ fixture.getTeam1().getName()+ "-----" +fixture.getTeam2().getName()+"----"+fixture.getTown()+"----"+fixture.getStadium()+ "-------"+fixture.getWeekend()+ "-------"+fixture.getIsFirstLeg());
                  id++;
              }
    }
    public static List<Fixture> combineTeams(List<Team> teams)
    {
        List<Fixture> fixtures = new ArrayList<Fixture>();
        for(int i = 0; i<teams.size();i++)
        {
            for(int j = i + 1;j<teams.size();j++)
            {
                fixtures.add(new Fixture(teams.get(i),teams.get(j)));                     
            }
        }
        return fixtures;
    }
    public static List<Fixture> setSecondLeg(List<Fixture> fixtures,int actualWeek)
    {
        List<Fixture> newFixtures = new ArrayList<>();
        int week = actualWeek +1;
        for(Fixture fixture:fixtures)
        {
            Fixture fixtureToAdd = new Fixture(fixture.getTeam1(),fixture.getTeam2(),false);
            fixtureToAdd.setWeekend("Weekend"+week);
            newFixtures.add(fixtureToAdd);
            week++;
        }
        return newFixtures;
    }
    public static List<String> getTowns() throws IOException
    {
        List<Team> teams = CSVHelper.readTeamsFromCsvFile(fileName);
        List<String> towns = new ArrayList<>();
        for(Team team: teams)
        {
            if(!towns.contains(team.getTown()))
                towns.add(team.getTown());
        }
        return towns;
    }
    public static List<Fixture> generateCombination() throws IOException
    {
        List<Team> teamList = CSVHelper.readTeamsFromCsvFile(fileName);
        List<Fixture> fixtures = new ArrayList<>();
        int teams = teamList.size();
        
        // If odd number of teams add a "ghost".
        boolean ghost = false;
        if (teams % 2 == 1) {
            teams++;
            ghost = true;
        }
        
        // Generate the fixtures using the cyclic algorithm.
        int totalRounds = teams - 1;
        int matchesPerRound = teams / 2;
        String[][] rounds = new String[totalRounds][matchesPerRound];
        
        for (int round = 0; round < totalRounds; round++) {
            for (int match = 0; match < matchesPerRound; match++) {
                int home = (round + match) % (teams - 1);
                int away = (teams - 1 - match + round) % (teams - 1);
                // Last team stays in the same place while the others
                // rotate around it.
                if (match == 0) {
                    away = teams - 1;
                }
                // Add one so teams are number 1 to teams not 0 to teams - 1
                // upon display.
                rounds[round][match] = (home + 1) + " v " + (away + 1);
            }
        }
        
        // Interleave so that home and away games are fairly evenly dispersed.
        String[][] interleaved = new String[totalRounds][matchesPerRound];
        
        int evn = 0;
        int odd = (teams / 2);
        for (int i = 0; i < rounds.length; i++) {
            if (i % 2 == 0) {
                interleaved[i] = rounds[evn++];
            } else {
                interleaved[i] = rounds[odd++];
            }
        }
        
        rounds = interleaved;

        // Last team can't be away for every game so flip them
        // to home on odd rounds.
        for (int round = 0; round < rounds.length; round++) {
            if (round % 2 == 1) {
                rounds[round][0] = flip(rounds[round][0]);
            }
        }
        
        // Populate fixtures      
        for (int i = 0; i < rounds.length; i++) {
            String[] roundsString = rounds[i];
            for(String s : roundsString)
            {
                String[] comp = s.split("v");
                fixtures.add(new Fixture(teamList.get((Integer.parseInt(comp[0].trim()) - 1)),teamList.get((Integer.parseInt(comp[1].trim())) - 1)));
            }
            
        }
        return fixtures;
       
    }
    private static String flip(String match) {
        String[] components = match.split(" v ");
        return components[1] + " v " + components[0];
    }

    
    }
