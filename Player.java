// Player.java
import java.util.Scanner;
import java.util.InputMismatchException;

// Defines a Player class for the Battleship game
public class Player {
    private String name; // Holds the name of the player
    private Board board; // Reference to the game board
    private int score; // Tracks the number of ships the player has sunk

    // Constructor for the Player with a name and board
    public Player(String name, Board board) {
        this.name = name; // Assigns the provided player name to this player
        this.board = board; // Sets the reference to the game board for this player
        this.score = 0; // Initialises the score to zero at the star
    }

    // Returns the name of the player
    public String getName() {
        return name;
    }

    // Returns the current score of the player
    public int getScore() {
        return score;
    }

    // Increments the player's score by one
    private void incrementScore() {
        score++; // Adds one to the current score
    }
    
    // Displays the current state of the board by printing it
    public void displayBoard() {
        System.out.println(board.toString()); // Calls the Board class's toString method to get the board's string
    }

    // Handles the player's turn, including taking and validating their shot
    public boolean takeTurn(Scanner scanner) {
        boolean validShot = false; // Flag to track if the shot is valid
        boolean shipSunk = false; // Flag to indicate if a ship has been sunk
        
        // Continues to prompt for a shot until a valid one is made
        while (!validShot) {
            try {
                // Prompts for the player's shot
                System.out.println("Admiral " + name + "'s turn");
                System.out.print("Enter row and column numbers (e.g., 0 0 for top-left): ");
                
                int row = scanner.nextInt(); // Reads the row number from user input
                int col = scanner.nextInt(); // Reads the column number from user input
                
                // takes leftover input including the newline character
                scanner.nextLine();

                // Validates the shot is within the board's boundaries
                if (row >= 0 && row < board.getRows() && col >= 0 && col < board.getColumns()) {
                    validShot = true; // Marks the shot as valid
                    // Executes the shot and checks if it hit a ship
                    if (board.shoot(row, col)) {
                        // Checks if the hit resulted in a sunk ship
                        if (board.getBattleshipAt(row, col).isSunk()) {
                            incrementScore(); // Increments player's score for sinking a ship
                            System.out.println("Admiral " + name + " sunk a battleship! Total score: " + getScore());
                            shipSunk = board.checkWinCondition(); // Checks if all ships have been sunk
                        } else {
                            System.out.println("Admiral " + name + " hit a battleship!"); // Acknowledges a hit
                        }
                    } else {
                        System.out.println("Admiral" + name + " missed."); // Acknowledges a miss
                    } //Make the game more smoother if coordinates were incorrect 
                } else {
                    System.out.println("Invalid coordinates Admiral! Please try again."); // Informs player of invalid shot coordinates
                }
            } catch (InputMismatchException ex) {
                // Catches exception if input was not an integer and prompts again
                System.out.println("Invalid input Admiral! Please enter numeric coordinates.");
                scanner.nextLine(); // Consumes the invalid input to reset the scanner
            }
        }
        return shipSunk; // Returns true if a ship was sunk, otherwise false
    }
}
