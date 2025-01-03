package smarthome.entities;

import smarthome.HouseComponent;
import smarthome.Visitor;
import smarthome.entities.equipment.Equipment;
import smarthome.entities.inhabitants.Inhabitant;
import smarthome.iterators.HouseComponentIterator;
import smarthome.iterators.HouseIterator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class House implements HouseComponent {
    private String name;
    private List<HouseComponent> floors = new ArrayList<>();
    private List<Inhabitant> inhabitants = new ArrayList<>();
    private List<Equipment> equipment = new ArrayList<>();


    public House(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }

    public void addInhabitant(Inhabitant inhabitant) {
        inhabitants.add(inhabitant);
    }

    public void addEquipment(Equipment equipmentPiece) {
        equipment.add(equipmentPiece);
    }

    public List<Inhabitant> getInhabitants() {
        return inhabitants;
    }

    public List<HouseComponent> getFloors() {
        return floors;
    }

    public List<Floor> getAllFloors() {
        return floors.stream().map(Floor.class::cast).collect(Collectors.toList());
    }


    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitHouse(this);
        HouseComponentIterator iterator = iterator();
        while (iterator.hasNext()) {
            HouseComponent floor = iterator.next();
            floor.acceptVisitor(visitor);
        }
    }

    @Override
    public HouseComponentIterator iterator() {
        return new HouseIterator(floors);
    }
}
