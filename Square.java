// Square.java
// Represents a square on the Battleship game board
public class Square {
    private int row; // The row position of the square on the board
    private int column; // The column position of the square on the board
    private boolean hasShip; // Flag to indicate if the square has a ship
    private Battleship battleship; // Holds a reference to the battleship object if there is a ship
    private boolean shotAt; // Flag to indicate if the square has been shot at

    // Constructor for the Square class that sets its row and column position
    public Square(int row, int column) {
        this.row = row; // Sets the row position
        this.column = column; // Sets the column position
        this.hasShip = false; // Initially, the square does not have a ship
        this.battleship = null; // No battleship is associated yet
        this.shotAt = false; // The square has not been shot at
    }

    // Method to check if the square currently has a ship
    public boolean hasShip() {
        return hasShip;
    }

    // Method to set the hasShip flag of the square
    public void setHasShip(boolean hasShip) {
        this.hasShip = hasShip; // Updates the hasShip flag with the given value
    }

    // Method to get the battleship associated with this square
    public Battleship getBattleship() {
        return battleship;
    }

    // Method to set a battleship to the square and mark the square as having a ship
    public void setBattleship(Battleship battleship) {
        this.hasShip = true; // Marks that the square has a ship
        this.battleship = battleship; // Sets the battleship reference to the given battleship
    }

    // Method to check if the square has been shot at
    public boolean isShotAt() {
        return shotAt;
    }

    // Method to set the shotAt flag of the square
    public void setShotAt(boolean shotAt) {
        this.shotAt = shotAt; // Updates the shotAt flag withthe given value
    }

    // Overrides the default toString method to provide a string representation of the square state
    @Override
    public String toString() {
        if (shotAt) { // If the square has been shot at
            if (hasShip) {
                return "x "; // Returns 'x' if there was a ship
            } else {
                return "o "; // Returns 'o' if there was no ship
            }
        }
        return "- "; // Returns '-' if the square has not been shot at
    }
    
}
