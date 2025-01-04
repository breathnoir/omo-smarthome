package smarthome.reports;

import smarthome.entities.Floor;
import smarthome.entities.House;
import smarthome.entities.Room;
import smarthome.entities.devices.Device;


public class ConsumptionReport implements Visitor {

    private static double totalUsage = 0;

    @Override
    public void visitHouse(House house) {
        LoggerManager.consumptionLogger.info("Consumption Report for House " + house.getName());
        LoggerManager.consumptionLogger.info("---------------------------------------");
    }

    @Override
    public void visitRoom(Room room) {
        LoggerManager.consumptionLogger.info("      Room: " + room.getName());
//        System.out.println("    Room: {}"+ room.getName());
    }

    @Override
    public void visitFloor(Floor floor) {
        LoggerManager.consumptionLogger.info("  Floor: " + floor.getName());
//        System.out.println("  Floor: {}"+ floor.getName());
    }

    @Override
    public void visitDevice(Device device) {
        LoggerManager.consumptionLogger.info("          Device: " + device.getName() + " - Usage: " + device.getElectricityUsed() + " kWh");
        totalUsage += device.getElectricityUsed();
//        System.out.println("      Device: " + device.getName() + " - Usage: " + device.getElectricityUsed() + " kWh");
    }

    public static void getTotalUsage() {
        LoggerManager.consumptionLogger.info("---------------------------------------");
        LoggerManager.consumptionLogger.info("Total Usage: " + totalUsage + " kWh");
    }
}
