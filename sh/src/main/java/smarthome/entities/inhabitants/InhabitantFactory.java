package smarthome.entities.inhabitants;

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
