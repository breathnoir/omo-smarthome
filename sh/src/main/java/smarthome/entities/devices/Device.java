package smarthome.entities.devices;

import smarthome.HouseComponent;
import smarthome.Visitor;
import smarthome.iterators.HouseComponentIterator;
import smarthome.iterators.NullIterator;

public class Device implements HouseComponent {
    private String name;
    private double electricityUsage;

    public Device(String name, double electricityUsage) {
        this.name = name;
        this.electricityUsage = electricityUsage;

    }

    public String getName() {
        return name;
    }

    public double getElectricityUsage() {
        return electricityUsage;
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
