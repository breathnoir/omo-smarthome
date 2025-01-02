package smarthome.config;

import smarthome.entities.inhabitants.Inhabitant;

import java.util.List;

public class ChainBuilder {
    public static Inhabitant buildChain(List<Inhabitant> inhabitants) {
        Inhabitant head = null;
        Inhabitant current = null;

        for (Inhabitant inhabitant : inhabitants) {
            if (head == null) {
                head = inhabitant;
                current = inhabitant;
            } else {
                current.setNext(inhabitant);
                current = inhabitant;
            }
        }

        return head;
    }
}