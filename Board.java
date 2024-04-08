// Board.java
// Represents the game board for Battleship, including all squares and ship placements
import java.util.Random;
public class Board {
    private Square[][] grid; // 2D array representing the grid of squares
    private int rows; // Number of rows in the board
    private int columns; // Number of columns in the board

    // Constructor for Board, initialises the board with a specific size and sets up the game
    public Board(int rows, int columns) {
        this.rows = rows; // Set the number of rows for the board
        this.columns = columns; // Set the number of columns for the board
        this.grid = new Square[rows][columns]; // Initialise the grid array
        initializeBoard(); // Fill the board with squares
        placeBattleships(); // Place battleships on the board
    }

    // Initialises all sqares on the board without any ships placed
    private void initializeBoard() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                grid[row][col] = new Square(row, col); // Create a new Square for each position
            }
        }
    }

    // Getter for the number of rows
    public int getRows() {
        return rows;
    }

    // Getter for the number of columns
    public int getColumns() {
        return columns;
    }
    
    // Determines if a ship can be placed at the given coordinates without overlapping another ship
    private boolean canPlaceShip(int row, int col, boolean horizontal, int size) {
        // Check horizontally or vertically based on the value of horizontal
        if (horizontal) {
            for (int i = 0; i < size; i++) {
                // If the position is out of bounds or already has a ship, return false
                if (col + i >= columns || grid[row][col + i].hasShip()) {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                // If the position is out of bounds or already has a ship, return false
                if (row + i >= rows || grid[row + i][col].hasShip()) {
                    return false;
                }
            }
        }
        return true; // If no conflicts, return true
    }

    // Places a ship on the board at the specified location and orientation
    private void placeShip(int row, int col, boolean horizontal, int size) {
        // Create a new Battleship instance based on the size
        Battleship battleship = switch (size) {
            case 1 -> new SmallBattleship();
            case 2 -> new MediumBattleship();
            default -> new LargeBattleship();
        };

        // Place the ship horizontally or vertically on the board
        for (int i = 0; i < size; i++) {
            if (horizontal) {
                grid[row][col + i].setBattleship(battleship);
            } else {
                grid[row + i][col].setBattleship(battleship);
            }
        }
    }

    // Shoots at the given coordinates and updates the state of the square and ship
    public boolean shoot(int row, int col) {
        Square target = grid[row][col]; // Get the target square
        // If the square has not been shot at before
        if (!target.isShotAt()) {
            target.setShotAt(true); // Mark the square as shot at
            // If the square has a ship, hit the ship and return true
            if (target.hasShip()) {
                target.getBattleship().hit();
                return true;
            }
        }
        return false; // If already shot at or no ship, return false
    }

    // Returns the battleship at the specified coordinates, if any
    public Battleship getBattleshipAt(int row, int col) {
        return grid[row][col].getBattleship();
    }

    // Checks if all ships have been sunk, indicating a win condition
    public boolean checkWinCondition() {
        // Iterate over all squares, checking for unsunk ships
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (grid[row][col].hasShip() && !grid[row][col].getBattleship().isSunk()) {
                    return false; // If any ship is not sunk, return false
                }
            }
        }
        return true; // If all ships are sunk, return true
    }

    // Places a specific number and size of battleships randomly on the board
    private void placeBattleships() {
        // Call placeSpecificBattleship for each ship type
        placeSpecificBattleship(SmallBattleship.QUANTITY, 1);
        placeSpecificBattleship(MediumBattleship.QUANTITY, 2);
        placeSpecificBattleship(LargeBattleship.QUANTITY, 3);
    }

    // Helper method to place a specific quantity of ships of a given size
    private void placeSpecificBattleship(int quantity, int size) {
        Random random = new Random(); // Create a Random object for generating coordinates
        for (int i = 0; i < quantity; i++) {
            boolean placed = false;
            while (!placed) {
                // Generate random starting coordinates and orientation
                int row = random.nextInt(rows);
                int col = random.nextInt(columns);
                boolean horizontal = random.nextBoolean();

                // If the ship can be placed, place it and set placed to true
                if (canPlaceShip(row, col, horizontal, size)) {
                    placeShip(row, col, horizontal, size);
                    placed = true;

                }
            }
        }
    }

 // Method to display the board in a user-friendly way
    @Override
    public String toString() {
        StringBuilder boardVisual = new StringBuilder("  "); // Creates a StringBuilder to build the string representation of the boar

        // Adds column numbers to the top of the board for reference
        for (int i = 0; i < columns; i++) {
            boardVisual.append(i).append(" "); // Appends column numbers with space for separation
        }
        boardVisual.append("\n"); // Adds a newline to separate the column numbers from the board
        
        // Loops through each row of the board
        for (int row = 0; row < rows; row++) {
            boardVisual.append(row).append(" "); // Appends row number at the start of each line
            // Loops through each column in the current row
            for (int col = 0; col < columns; col++) {
                boardVisual.append(grid[row][col].toString()); // Appends the string representation of each square
            }
            boardVisual.append("\n"); // Adds a newline at the end of each row to start a new line
        }
        return boardVisual.toString(); // Returns the complete string representation of the board
    }
}
