package smarthome.entities.inhabitants;

public class Animal extends Inhabitant {
    private String species;

    public Animal(String name, int age, String species) {
        super(name, age);
        this.species = species;
    }

}
