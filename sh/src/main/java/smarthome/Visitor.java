package smarthome;

import smarthome.entities.Floor;
import smarthome.entities.House;
import smarthome.entities.Room;
import smarthome.entities.devices.Device;

public interface Visitor {
    void visitHouse(House house);
    void visitRoom(Room room);
    void visitFloor(Floor floor);
    void visitDevice(Device device);
}
