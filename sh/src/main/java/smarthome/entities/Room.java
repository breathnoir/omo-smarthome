package smarthome.entities;

import smarthome.reports.HouseComponent;
import smarthome.entities.devices.Device;
import smarthome.entities.devices.Observer;
import smarthome.entities.inhabitants.Inhabitant;
import smarthome.entities.sensors.MotionSensor;
import smarthome.entities.sensors.Sensor;
import smarthome.iterators.HouseComponentIterator;
import smarthome.iterators.RoomIterator;
import smarthome.reports.Visitor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Room implements HouseComponent {
    private String name;
    private List<HouseComponent> devices = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();
    private List<Sensor> sensors = new ArrayList<>();
    private Set<Inhabitant> inhabitants = new HashSet<>();
    private MotionSensor motionSensor = null;
    private double temperature = 22.0;
    private double humidity = 50.0;
    private double windSpeed = 0;
    private double lightning = 700;
    private boolean motionDetected = false;

    public Room(String roomName) {
        this.name = roomName;
    }

    public void acceptVisitor(Visitor visitor) {
        visitor.visitRoom(this);
        HouseComponentIterator iterator = iterator();
        while (iterator.hasNext()) {
            HouseComponent device = iterator.next();
            device.acceptVisitor(visitor);
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
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

    public List<HouseComponent> getDevices() {
        return devices;
    }

    public List<Device> getAllDevices() {
        return devices.stream().map(Device.class::cast).collect(Collectors.toList());
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public MotionSensor getMotionSensor() {
        return motionSensor;
    }

    public void setMotionSensor(MotionSensor motionSensor) {
        this.motionSensor = motionSensor;
    }

    public void setMotionDetected(boolean motionDetected) {
        this.motionDetected = motionDetected;
    }

    public boolean getMotionDetected() {
        return motionDetected;
    }

    public int getInhabitantsCount() {
        return inhabitants.size();
    }

    public void addInhabitants(Inhabitant inhabitant) {
        inhabitants.add(inhabitant);
    }

    public void removeInhabitants(Inhabitant inhabitant) {
        inhabitants.remove(inhabitant);
    }

    @Override
    public String toString() {
        return name;
    }
}
