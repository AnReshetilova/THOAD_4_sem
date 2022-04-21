package by.reshetilova.classes;

import java.util.ArrayList;

public class Planet {
    String name;
    Double radius;
    String temp;
    Boolean atmosphere;
    Boolean life;
    ArrayList<Satellite> satellites = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setAtmosphere(Boolean atmosphere) {
        this.atmosphere = atmosphere;
    }

    public void setLife(Boolean life) {
        this.life = life;
    }

    public void setSatellites(Satellite satellites) {
        this.satellites.add(satellites);
    }

    public ArrayList<Satellite> getSatellites() {
        return satellites;
    }

    public String getName() {
        return name;
    }

    public Planet(String name, Double radius, String temp, Boolean atmosphere, Boolean life) {
        this.name = name;
        this.radius = radius;
        this.temp = temp;
        this.atmosphere = atmosphere;
        this.life = life;
    }

    @Override
    public String toString(){
        return "Planet name: " + this.name + "\nPlanet radius: " + this.radius + "\nPlanet temperature: " + this.temp + "\nPlanet atmosphere: " + this.atmosphere + "\nPlanet life: " + this.life;
    }
}
