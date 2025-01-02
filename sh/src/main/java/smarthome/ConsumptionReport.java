package smarthome;

import smarthome.entities.Floor;
import smarthome.entities.House;
import smarthome.entities.Room;
import smarthome.entities.devices.Device;


public class ConsumptionReport implements Visitor {

    @Override
    public void visitHouse(House house) {
        System.out.println("Consumption Report for House {}" + house.getName());
    }

    @Override
    public void visitRoom(Room room) {
        System.out.println("    Room: {}"+ room.getName());
    }

    @Override
    public void visitFloor(Floor floor) {
        System.out.println("  Floor: {}"+ floor.getName());
    }

    @Override
    public void visitDevice(Device device) {
        System.out.println("      Device: " + device.getName() + " - Usage: " + device.getElectricityUsed() + " kWh");
    }
}
