package smarthome.entities;

import smarthome.entities.inhabitants.Inhabitant;

public interface UsableObject {
    boolean isFree();
    void use(Inhabitant user);
    void release();
}
