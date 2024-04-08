//BattleshipGame.java
import java.util.Scanner;

public class BattleshipGame {
    private Player player1; // Holds a reference to the first player
    private Player player2; // Holds a reference to the second player
    private Board board; // Holds a reference to the game board
    private Scanner scanner; // Scanner for reading user input

    // Constructor for Game class initialises the game with two players and a board
    public BattleshipGame() {
        scanner = new Scanner(System.in); // Initialises the scanner
        board = new Board(10, 10); // Initialises a 10x10 board for the game
        
        // Prompt for and set Player 1's name
        System.out.print("Please enter name for Player 1: ");
        String player1Name = scanner.nextLine();
        player1 = new Player(player1Name, board);

        // Prompt for and set Player 2's name
        System.out.print("Please enter name for Player 2: ");
        String player2Name = scanner.nextLine();
        player2 = new Player(player2Name, board);
    }

    // start method begins the game loop
    public void start() {
        // Game introduction and instructions
        System.out.println("Welcome aboard Admirals! Prepare for battle.");
        System.out.println("INSTRUCTIONS:");
        System.out.println("1. Players take turns guessing the location of the enemy's ships.");
        System.out.println("2. Enter row and column numbers to fire at a location.");
        System.out.println("3. A hit on an enemy ship is marked with an 'x', a miss with an 'o'.");
        System.out.println("4. The game continues until one player sinks all the enemy's ships.");
        System.out.println("5. The player with the most ships sunk wins the game.");
        System.out.println("Good luck to both admirals!\n");
        
        String playAgain; // Variable to control the replay loop
        do {
            boolean gameOn = true; // Boolean to control the current game session
            Player currentPlayer = player1; // Start with player1 as the current player

            // Game loop continues until a win condition is met
            while (gameOn) {
                currentPlayer.displayBoard(); // Display the current state of the board
                // If currentPlayer takes a turn and sinks all ships, end the game
                if (currentPlayer.takeTurn(scanner)) {
                    gameOn = false; // Set the game state to over
                    System.out.println("Game over! Admiral " + currentPlayer.getName() + " wins the battle! Total score: " + currentPlayer.getScore());
                } else {
                    // Switch the current player
                    currentPlayer = (currentPlayer == player1) ? player2 : player1;
                }
            }

            // Display the final state of the board
            System.out.println("Final board:");
            currentPlayer.displayBoard();

            // Ask if the players want to play again
            System.out.print("Play again? (y/n): ");
            playAgain = scanner.nextLine(); // Read the player's input

        } while(playAgain.equalsIgnoreCase("y")); // Continue if the player enters 'y'

        scanner.close(); // Close the scanner to prevent resource leak
    }
}
