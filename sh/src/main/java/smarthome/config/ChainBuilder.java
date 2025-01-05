package smarthome.config;

import smarthome.entities.inhabitants.Inhabitant;

import java.util.List;

/**
 * The {@code ChainBuilder} class provides a utility method to construct a
 * chain of {@link Inhabitant} objects from a list. Each inhabitant in the list
 * will be linked to the next one in the chain using their {@code setNext} method.
 */
public class ChainBuilder {

    /**
     * Builds a chain of {@link Inhabitant} objects using the provided list.
     * Each inhabitant is linked to the next one using their {@code setNext} method.
     * @return The head {@link Inhabitant} of the chain, or {@code null} if the list is empty.
     */
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