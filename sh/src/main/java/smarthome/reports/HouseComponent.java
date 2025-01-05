package smarthome.reports;

import smarthome.iterators.HouseComponentIterator;

/**
 * Represents a component in a house hierarchy, following the Composite design pattern.
 * This interface allows for a unified treatment of individual objects (e.g., Device)
 * and composite objects (e.g., Floor, House) in the structure. Each component can
 * accept a visitor for performing operations and provide an iterator for traversal
 * through its contained elements.
 */
public interface HouseComponent {
    /**
     * Accepts a visitor to perform an operation defined by the Visitor interface.
     * This method is part of the Visitor design pattern implementation, allowing
     * external logic to be executed on this component or its children.
     *
     * @param visitor the Visitor instance containing the operation to be performed
     *                on this component or its subcomponents.
     */
    void acceptVisitor(Visitor visitor);
    HouseComponentIterator iterator();
}
