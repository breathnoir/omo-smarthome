package smarthome.config;

/**
 * Represents the configuration for an inhabitant in a smart home system.
 * This class stores the details about an inhabitant such as their type, name, age,
 * and species (for non-human inhabitants like animals).
 */
public class InhabitantConfig {
    private String type;
    private String name;
    private int age;
    private String species;  // For animals

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
}
