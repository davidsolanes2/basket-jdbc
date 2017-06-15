
package model;

/*
 *
 * @author david
 */

public class Ranking {

    private int ranking;
    private String name;
    private int nbaskets;
    
    public Ranking() {
      int ranking = 0;
      
    }

    public Ranking(int ranking, String name, int nbaskets) {
        this.ranking = ranking;
        this.name = name;
        this.nbaskets = nbaskets;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNbaskets() {
        return nbaskets;
    }

    public void setNbaskets(int nbaskets) {
        this.nbaskets = nbaskets;
    }

    @Override
    public String toString() {
        return "Ranking{" + " ranking=" + ranking + ", name=" + name + ", nbaskets=" + nbaskets + '}';
    }
    
}
