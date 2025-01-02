package smarthome.entities.devices;


import smarthome.HouseComponent;
import smarthome.entities.Room;
import smarthome.iterators.HouseComponentIterator;
import smarthome.iterators.NullIterator;
import smarthome.Visitor;

public class Device implements HouseComponent {
    private String name;
    private double electricityUsage;
    private Room room;

    public Device(Room room, String name, double electricityUsage) {
        this.room = room;
        this.name = name;
        this.electricityUsage = electricityUsage;

    }

    public Room getRoom() {
        return room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getElectricityUsage() {
        return electricityUsage;
    }

    public void setElectricityUsage(double electricityUsage) {
        this.electricityUsage = electricityUsage;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitDevice(this);
    }

    @Override
    public HouseComponentIterator iterator() {
        return new NullIterator();
    }
}
