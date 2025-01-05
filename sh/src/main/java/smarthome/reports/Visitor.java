package smarthome.reports;

import smarthome.entities.Floor;
import smarthome.entities.House;
import smarthome.entities.Room;
import smarthome.entities.devices.Device;

/**
 * The Visitor interface defines a set of methods that can be implemented
 * by classes to perform operations on various components of a hierarchical
 * structure representing a house. This follows the Visitor design pattern
 * to separate algorithms from the object structure.
 */
public interface Visitor {
    void visitHouse(House house);
    void visitRoom(Room room);
    void visitFloor(Floor floor);
    void visitDevice(Device device);
}
