/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.leaguetable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jmuto
 */
public class CSVHelper {
    public static List<Team> readTeamsFromCsvFile(String path) throws IOException
    {
        List<Team> teams = new ArrayList<>();
        String row = new String();
        BufferedReader csvReader = new BufferedReader(new FileReader(path)); 
        
        int teamId = 1;
        while ((row = csvReader.readLine()) != null) 
        {  
            String[] data = row.split(",");
            teams.add(new Team(teamId,data[0],data[1],data[2]));
            teamId++;
        }
    csvReader.close();
    return teams;
    }
    public static void saveCsvFile(List<Team> teams, String filename) throws IOException
    {
        FileWriter csvWriter = new FileWriter(filename); 
        for (Team team : teams) 
        {  
            csvWriter.append(team.getName());  
            csvWriter.append(",");
            csvWriter.append(team.getTown());  
            csvWriter.append(",");
            csvWriter.append(team.getStadium());  
            csvWriter.append("\n");
        }
        csvWriter.flush();  
        csvWriter.close();
    }
    
}
