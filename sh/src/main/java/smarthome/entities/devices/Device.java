package smarthome.entities.devices;

import smarthome.HouseComponent;
import smarthome.Visitor;

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
}
