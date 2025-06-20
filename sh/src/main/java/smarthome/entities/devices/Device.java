package smarthome.entities.devices;


import smarthome.reports.HouseComponent;
import smarthome.entities.Room;
import smarthome.events.BrokenDeviceEvent;
import smarthome.iterators.HouseComponentIterator;
import smarthome.iterators.NullIterator;
import smarthome.reports.Visitor;
import smarthome.entities.devices.state.BrokenState;
import smarthome.entities.devices.state.DeviceState;
import smarthome.entities.devices.state.OffState;
import smarthome.entities.devices.state.OnState;

public class Device implements HouseComponent {
    private String name;
    private final double electricityUsage;
    private double electricityUsed = 0;
    private boolean isUsingElectricity = false;
    private Room room;

    private DeviceState onState;
    private DeviceState offState;
    private DeviceState brokenState;
    private DeviceState state;

    public Device(Room room, String name, double electricityUsage) {
        this.room = room;
        this.name = name;
        this.electricityUsage = electricityUsage;


        onState = new OnState(this);
        offState = new OffState(this);
        brokenState = new BrokenState(this);

        state = offState;
    }

    public void useElectricity(){
        if (isUsingElectricity) electricityUsed += electricityUsage;
    }

    public Room getRoom() {
        return room;
    }

    public void enable() {
        state.enable();
    }

    public void disable() {
        state.disable();
    }

    public void fix() {
        state.fix();
    }

    public void setState(DeviceState state) {
        this.state = state;
    }

    public boolean isBroken(){
        return state == brokenState;
    }

    public DeviceState getOnState() {
        return onState;
    }

    public DeviceState getOffState() {
        return offState;
    }

    public DeviceState getBrokenState() {
        return brokenState;
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

    public double getElectricityUsed() {
        return electricityUsed;
    }

    public void setUsingElectricity(boolean usingElectricity) {
        isUsingElectricity = usingElectricity;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitDevice(this);
    }

    @Override
    public HouseComponentIterator iterator() {
        return new NullIterator();
    }

    public BrokenDeviceEvent breakDevice() {
        return state.breakDevice();
    }

    @Override
    public String toString() {
        return "Device " + name;
    }
}