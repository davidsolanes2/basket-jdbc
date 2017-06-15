
package model;

/**
 *
 * @author david
 */
public class averagePlayer {
    
    private String name;
    private String Position;
    private Team team;
    private double AvgBasket;
    private double AvgAssist;
    private double AvgRebounds;
    private int maxBasket;
    private int maxAssist;
    private int maxRebounds;
    private int minBasket;
    private int minAssist;
    private int minRebounds;

    public averagePlayer( String name, String Position, Team team, double AvgBasket, double AvgAssist, double AvgRebounds, int maxBasket, int maxAssist, int maxRebounds, int minBasket, int minAssist, int minRebounds) {
        this.name = name;
        this.Position = Position;
        this.team = team;
        this.AvgBasket = AvgBasket;
        this.AvgAssist = AvgAssist;
        this.AvgRebounds = AvgRebounds;
        this.maxBasket = maxBasket;
        this.maxAssist = maxAssist;
        this.maxRebounds = maxRebounds;
        this.minBasket = minBasket;
        this.minAssist = minAssist;
        this.minRebounds = minRebounds;
    }
    
    public averagePlayer() {
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public double getAvgBasket() {
        return AvgBasket;
    }

    public void setAvgBasket(double AvgBasket) {
        this.AvgBasket = AvgBasket;
    }

    public double getAvgAssist() {
        return AvgAssist;
    }

    public void setAvgAssist(double AvgAssist) {
        this.AvgAssist = AvgAssist;
    }

    public double getAvgRebounds() {
        return AvgRebounds;
    }

    public void setAvgRebounds(double AvgRebounds) {
        this.AvgRebounds = AvgRebounds;
    }

    public int getMaxBasket() {
        return maxBasket;
    }

    public void setMaxBasket(int maxBasket) {
        this.maxBasket = maxBasket;
    }

    public int getMaxAssist() {
        return maxAssist;
    }

    public void setMaxAssist(int maxAssist) {
        this.maxAssist = maxAssist;
    }

    public int getMaxRebounds() {
        return maxRebounds;
    }

    public void setMaxRebounds(int maxRebounds) {
        this.maxRebounds = maxRebounds;
    }

    public int getMinBasket() {
        return minBasket;
    }

    public void setMinBasket(int minBasket) {
        this.minBasket = minBasket;
    }

    public int getMinAssist() {
        return minAssist;
    }

    public void setMinAssist(int minAssist) {
        this.minAssist = minAssist;
    }

    public int getMinRebounds() {
        return minRebounds;
    }

    public void setMinRebounds(int minRebounds) {
        this.minRebounds = minRebounds;
    }

    @Override
    public String toString() {
        return "averagePlayer{" + "name=" + name + ", Position=" + Position + ", team=" + team + ", AvgBasket=" + AvgBasket + ", AvgAssist=" + AvgAssist + ", AvgRebounds=" + AvgRebounds + ", maxBasket=" + maxBasket + ", maxAssist=" + maxAssist + ", maxRebounds=" + maxRebounds + ", minBasket=" + minBasket + ", minAssist=" + minAssist + ", minRebounds=" + minRebounds + '}';
    }
      
}
