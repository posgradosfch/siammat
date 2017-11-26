/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.fca.siammat.modelo;

import java.util.LinkedHashMap;
import java.util.Map;
 
public class Player {
     
    private String name;
     
    private Map<String,Integer> goals;
     
    public Player() {
        goals = new LinkedHashMap<String,Integer>();
    }
     
    public Player(String name, Map<String,Integer> goals) {
        this.name = name;
        this.goals = goals;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
     
    public int getGoals(String year) {
        return goals.get(year);
    }
}
