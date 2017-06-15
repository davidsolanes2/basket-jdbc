
package model;

import java.time.LocalDate;

/*
 * @author david
 */

public class Player {
       
    private String name;
    private LocalDate birth;
    private int nbaskets;
    private int nassists;
    private int nrebounds;
    private String position;
    private Team team;

    public Player() {
        team = new Team();
        birth = LocalDate.now();
        name = "";
        position = "";
    }
    
    public Player(String name, LocalDate birth, int nbaskets, int nassists, int nrebounds,
            String position, Team team) {
        this.name = name;
        this.birth = birth;
        this.nbaskets = nbaskets;
        this.nassists = nassists;
        this.nrebounds = nrebounds;
        this.position = position;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public int getNbaskets() {
        return nbaskets;
    }

    public void setNbaskets(int nbaskets) {
        this.nbaskets = nbaskets;
    }

    public int getNassists() {
        return nassists;
    }

    public void setNassists(int nassists) {
        this.nassists = nassists;
    }

    public int getNrebounds() {
        return nrebounds;
    }

    public void setNrebounds(int nrebounds) {
        this.nrebounds = nrebounds;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", birth=" + birth + ", nbaskets=" + nbaskets + ", nassists=" + nassists + ", nrebounds=" + nrebounds + ", position=" + position + ", team=" + team.getName() + '}';
    
    }
    
}
