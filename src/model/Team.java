
package model;

import java.time.LocalDate;

/**
 *
 * @author david
 */
public class Team {
    
    private String name;
    private String city;
    private LocalDate creacion;
    
    public Team() {}

    public Team(String name) {
        this.name = name;
    }
    
    

    public Team(String name, String city, LocalDate creacion) {
        this.name = name;
        this.city = city;
        this.creacion = creacion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getCreacion() {
        return creacion;
    }

    public void setCreacion(LocalDate creacion) {
        this.creacion = creacion;
    }
    
}
