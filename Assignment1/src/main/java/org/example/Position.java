package org.example;

/**
 * The {@code Position} class represents an entry in the grid, where each position object can
 * be associated with a ship or a grenade. It also tracks whether the position belongs to the
 * computer or the user, and whether it has been called or not.
 */
public class Position {

    /**
     * The type of element at this position, which can be either a ship or a grenade.
     */
    private String elementType;

    /**
     * The owner of the position, which can be either the computer or the user.
     */
    private String positionOwner;

    /**
     * Indicates whether the position has been called or not (true or false).
     */
    private boolean positionCalled;

    /**
     * Constructs a new Position with the specified elementType, positionOwner, and positionCalled.
     *
     * @param elementType    The type of element at this position (ship or grenade).
     * @param positionOwner  The owner of the position (computer or user).
     * @param positionCalled Indicates whether the position has been called or not.
     */
    public Position(String elementType, String positionOwner, boolean positionCalled) {
        this.elementType = elementType;
        this.positionOwner = positionOwner;
        this.positionCalled = positionCalled;
    }

    /**
     * Gets the type of element at this position.
     *
     * @return The elementType (ship or grenade).
     */
    public String getElementType() {
        return elementType;
    }

    /**
     * Sets the type of element at this position.
     *
     * @param elementType The new elementType to set (ship or grenade).
     */
    public void setElementType(String elementType) {
        this.elementType = elementType;
    }


    /**
     * Gets the owner of the position.
     *
     * @return The positionOwner (computer or user).
     */
    public String getPositionOwner() {
        return positionOwner;
    }

    /**
     * Sets the owner of the position.
     *
     * @param positionOwner The new positionOwner to set (computer or user).
     */
    public void setPositionOwner(String positionOwner) {
        this.positionOwner = positionOwner;
    }

    /**
     * Checks whether the position has been called.
     *
     * @return {@code true} if the position has been called, {@code false} otherwise.
     */
    public boolean isPositionCalled() {
        return positionCalled;
    }

    /**
     * Sets whether the position has been called or not.
     *
     * @param positionCalled The new value to set (true or false).
     */
    public void setPositionCalled(boolean positionCalled) {
        this.positionCalled = positionCalled;
    }

    /**
     * Returns a string representation of the Position object.
     *
     * @return A string containing the elementType, positionOwner, and positionCalled.
     */
    @Override
    public String toString() {
        return "Position{" +
                "elementType='" + elementType + '\'' +
                ", positionOwner='" + positionOwner + '\'' +
                ", positionCalled=" + positionCalled +
                '}';
    }
}
