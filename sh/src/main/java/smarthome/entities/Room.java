package smarthome.entities;

import smarthome.HouseComponent;
import smarthome.entities.devices.Device;
import smarthome.entities.devices.Observer;
import smarthome.entities.sensors.Sensor;
import smarthome.iterators.HouseComponentIterator;
import smarthome.iterators.RoomIterator;
import smarthome.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Room implements HouseComponent {
    private String name;
    private List<HouseComponent> devices = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();
    private List<Sensor> sensors = new ArrayList<>();
    private double temperature;
    private double humidity;
    private double windSpeed;
    private double lightning;
    private boolean motionDetected;

    public Room(String roomName) {
        this.name = roomName;
    }

    public void accept(Visitor visitor) {
        visitor.visitRoom(this);
        HouseComponentIterator iterator = iterator();
        while (iterator.hasNext()) {
            HouseComponent device = iterator.next();
            device.accept(visitor);
        }
    }

    @Override
    public HouseComponentIterator iterator() {
        return new RoomIterator(devices);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addDevice(Device device) {
        devices.add(device);
    }

    public void addSensor(Sensor sensor) {
        sensors.add(sensor);
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getLightning() {
        return lightning;
    }

    public void setLightning(double lightning) {
        this.lightning = lightning;
    }

    public boolean isMotionDetected() {
        return motionDetected;
    }

    public void setMotionDetected(boolean motionDetected) {
        this.motionDetected = motionDetected;
    }
}
