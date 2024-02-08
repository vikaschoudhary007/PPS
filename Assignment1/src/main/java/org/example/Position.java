package org.example;

// Each position object will be a entry in the grid
public class Position {

    // Ship or Grenade
    private String elementType;

    // Computer or User
    private String positionOwner;

    // true or false
    private boolean positionCalled;

    public Position(String elementType, String positionOwner, boolean positionCalled) {
        this.elementType = elementType;
        this.positionOwner = positionOwner;
        this.positionCalled = positionCalled;
    }

    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    public String getPositionOwner() {
        return positionOwner;
    }

    public void setPositionOwner(String positionOwner) {
        this.positionOwner = positionOwner;
    }

    public boolean isPositionCalled() {
        return positionCalled;
    }

    public void setPositionCalled(boolean positionCalled) {
        this.positionCalled = positionCalled;
    }

    @Override
    public String toString() {
        return "Position{" +
                "elementType='" + elementType + '\'' +
                ", positionOwner='" + positionOwner + '\'' +
                ", positionCalled=" + positionCalled +
                '}';
    }
}
