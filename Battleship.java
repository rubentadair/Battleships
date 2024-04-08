// Battleship.java
// This class represents a battleship in the game with its size, health, and sunk status
public class Battleship {
    private boolean sunk; // Indicates whether the battleship has been sunk
    private int health; // Represents the remaining health of the battleship
    private int size; // The size of the battleship, which also determines its initial health

    // Constructor that sets the size of the battleship and initialises its health and sunk status
    public Battleship(int size) {
        this.size = size; // Sets the size of the battleship
        this.health = size; // Sets the health to the size, as a full-health battleship
        this.sunk = false; // Initially, the battleship is not sunk
    }

    // Method to check if the battleship is sunk
    public boolean isSunk() {
        return sunk; // Returns the sunk status
    }

    // Method to register a hit on the battleship, reduces health and checks if it's sunk
    public void hit() {
        health--; // Decreases the health of the battleship by on
        if (health <= 0) { // If health drops to 0 or below
            sunk = true; // Set the battleship's status to sunk
        }
    }
}


