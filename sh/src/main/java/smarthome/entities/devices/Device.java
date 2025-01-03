package smarthome.entities.devices;


import smarthome.HouseComponent;
import smarthome.entities.Room;
import smarthome.events.BrokenDeviceEvent;
import smarthome.events.EventBus;
import smarthome.iterators.HouseComponentIterator;
import smarthome.iterators.NullIterator;
import smarthome.Visitor;
import smarthome.entities.devices.state.BrokenState;
import smarthome.entities.devices.state.DeviceState;
import smarthome.entities.devices.state.OffState;
import smarthome.entities.devices.state.OnState;

public class Device implements HouseComponent {
    private String name;
    private final double electricityUsage;
    private double electricityUsed = 0;
    private Room room;

    private DeviceState onState;
    private DeviceState offState;
    private DeviceState brokenState;
    private DeviceState state;

    private EventBus eventBus = EventBus.getInstance();

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
        electricityUsed += electricityUsage;
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

}