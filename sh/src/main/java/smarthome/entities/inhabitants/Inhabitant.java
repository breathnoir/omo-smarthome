package smarthome.entities.inhabitants;

import smarthome.entities.Floor;
import smarthome.entities.Room;

public class Inhabitant {
    private String name;
    private boolean isHome;
    private Room room;
    private Floor floor;
    private int age;

    public Inhabitant(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getLocation() {
        return null;
    }

}
