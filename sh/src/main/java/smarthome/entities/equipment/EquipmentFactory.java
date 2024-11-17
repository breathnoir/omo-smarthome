package smarthome.entities.equipment;

public class EquipmentFactory {
    public static Equipment createEquipment(String type, String name) {
        switch (type) {
            case "Bicycle":
                return new Bicycle(name);
            default:
                return null;
        }
    }
}
