package smarthome.entities.inhabitants;

/**
 * The InhabitantFactory class provides a method to create instances of different types of Inhabitants
 * based on a specified type and associated attributes. It acts as a central factory to generate specific
 * Inhabitant objects like Adult, Child, Baby, or Animal with the necessary initialization.
 */
public class InhabitantFactory {
    public static Inhabitant createInhabitant(String type, String name, int age, String species) {
        switch (type) {
            case "Adult":
                return new Adult(name, age);
            case "Child":
                return new Child(name, age);
            case "Baby":
                return new Baby(name, age);
            case "Animal":
                return new Animal(name, age, species);
            default:
                return null;
        }
    }
}
