package by.reshetilova.classes;

public class Satellite {
    String name;
    String planet;
    Double radius;
    Double distance;

    public void setName(String name) {
        this.name = name;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getPlanet() {
        return planet;
    }

    public Satellite(String name, String planet, Double radius, Double distance) {
        this.name = name;
        this.planet = planet;
        this.radius = radius;
        this.distance = distance;
    }

    @Override
    public String toString(){
        return "Satellite name: " + this.name + "\nPlanet name: " + this.planet + "\nSatellite radius: " + this.radius + "\nSatellite distance: " + this.distance;
    }
}
