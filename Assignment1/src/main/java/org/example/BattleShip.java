package org.example;
import java.util.Random;
import java.util.Scanner;

/**
 * The {@code BattleShip} class represents the main class for a simple Battleship game
 * where the user plays against the computer. It includes methods for setting up the game,
 * playing the game, and checking for the winner.
 */
public class BattleShip {

    /**
     * The main method that starts the Battleship game.
     *
     * @param args The command-line arguments (not used in this program).
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BattleGround battleGround = new BattleGround();

        System.out.println("Hi, lets play Battleship!");

        int shipCounter = 1;
        int grenadeCounter = 1;

        while(shipCounter <= 6){

            System.out.print("Enter the coordinates of your ship #"+shipCounter+": ");
            String coord = sc.nextLine().toUpperCase();

            while(!isValidCoordinate(coord) || isPositionOccupied(battleGround, coord)){
                System.out.println("Invalid input or position already occupied. Please try again.");
                System.out.print("Enter the position of your rocket : ");
                coord = sc.nextLine().toUpperCase();
            }

            int coordCol = coord.charAt(0)-'A';
            int coordRow = Character.getNumericValue(coord.charAt(1));

            Position newPosition = new Position("s","user", false);
            battleGround.setElement(coordRow-1, coordCol, newPosition);

            shipCounter++;

        }

        while(grenadeCounter <= 4){

            System.out.print("Enter the coordinates of your grenade #"+grenadeCounter+": ");
            String coord = sc.nextLine().toUpperCase();

            while(!isValidCoordinate(coord) || isPositionOccupied(battleGround, coord)){
                System.out.println("Invalid input or position already occupied. Please try again.");
                System.out.print("Enter the position of your rocket : ");
                coord = sc.nextLine().toUpperCase();
            }

            int coordCol = coord.charAt(0)-'A';
            int coordRow = Character.getNumericValue(coord.charAt(1));

            Position newPosition = new Position("g","user", false);
            battleGround.setElement(coordRow-1, coordCol, newPosition);

            grenadeCounter++;

        }

        setUpComputerShipGrenade(battleGround, sc);

        System.out.println("\nOK, the computer placed its ships and grenades at random. Lets play.");

        printGrid(battleGround);
        printHiddenGrid(battleGround);

        play(battleGround, sc);

        printGrid(battleGround);
    }

    /**
     * Prints the visible grid with ships, grenades, and rockets for the specified BattleGround.
     *
     * @param battleGround The BattleGround to print.
     */
    public static void printGrid( BattleGround battleGround){

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(battleGround.getElement(i,j).getElementType()+"\t");
            }
            System.out.println("\n");
        }
    }

    /**
     * Prints the hidden grid with only rockets revealed for the specified BattleGround.
     *
     * @param battleGround The BattleGround to print.
     */
    public static void printHiddenGrid( BattleGround battleGround){

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Position position = battleGround.getElement(i, j);
                if(position.isPositionCalled()){
                    System.out.print(position.getElementType()+"\t");
                }else{
                    System.out.print("_\t");
                }
            }
            System.out.println("\n");
        }
    }

    /**
     * Sets up computer ships and grenades randomly on the specified BattleGround.
     *
     * @param battleGround The BattleGround to set up.
     * @param sc           The Scanner object for input.
     */
    public static void setUpComputerShipGrenade(BattleGround battleGround, Scanner sc){

        Random random = new Random();

        int shipCounter = 1;
        int grenadeCounter = 1;

        while(shipCounter <= 6){

            int coordCol = random.nextInt(8);
            int coordRow = random.nextInt(8);

            Position newPosition = new Position("S","computer", false);
            battleGround.setElement(coordRow, coordCol, newPosition);

            shipCounter++;

        }

        while(grenadeCounter <= 4){

            int coordCol = random.nextInt(8);
            int coordRow = random.nextInt(8);

            Position newPosition = new Position("G","computer", false);
            battleGround.setElement(coordRow, coordCol, newPosition);

            grenadeCounter++;

        }


    }

    /**
     * Generates a random coordinate in the format A1 to H8.
     *
     * @param random The Random object for generating random values.
     * @return A random coordinate in the format A1 to H8.
     */
    public static String getRandomCoord(Random random){

        char randomCol = (char) ('A'+random.nextInt(8));
        int randomRow = random.nextInt(8) + 1;
        return randomCol+String.valueOf(randomRow);
    }

    /**
     * Plays the Battleship game by taking turns between the user and the computer.
     *
     * @param battleGround The BattleGround to play on.
     * @param sc           The Scanner object for input.
     */
    public static void play(BattleGround battleGround, Scanner sc){
        Random random = new Random();

        while (true){
            playGame(battleGround, sc, random, "user");

            playGame(battleGround, sc,random, "computer");

            printHiddenGrid(battleGround);

            String winner = checkForWinner(battleGround);
            if(winner != null){
                System.out.println("Game over" + " Winner : "+ winner);
                return;
            }
        }
    }

    /**
     * Checks for a winner by counting the number of sunk ships for the user and the computer.
     *
     * @param battleGround The BattleGround to check for a winner.
     * @return The winner of the game (user, computer, or null if the game is ongoing).
     */
    private static String checkForWinner(BattleGround battleGround) {

            int userShips = 0;
            int computerShips = 0;

            for(int i =0 ; i<8; i++){
                for(int j=0; j< 8;j++){
                    Position position = battleGround.getElement(i,j);

                    if(position.getElementType().equals("S") && position.isPositionCalled()){
                        computerShips++;
                    }else if(position.getElementType().equals("s") && position.isPositionCalled()){
                        userShips++;
                    }
                }
            }

            if(userShips == 6){
                return "computer";
            } else if (computerShips == 6) {
                return "user";
            }else{
                return null;
            }
    }

    /**
     * Plays a single turn of the game for the specified player (user or computer).
     *
     * @param battleGround The BattleGround to play on.
     * @param sc           The Scanner object for input.
     * @param random       The Random object for generating random coordinates.
     * @param player       The current player ("user" or "computer").
     */
    public static void playGame(BattleGround battleGround, Scanner sc, Random random, String player){

        System.out.print("position of your rocket: ");

        String coord = player.equals("user") ? sc.nextLine().toUpperCase() : getRandomCoord(random);

        int coordCol = coord.charAt(0)-'A';
        int coordRow = Character.getNumericValue(coord.charAt(1));

        Position rocketPosition = battleGround.getElement(coordRow-1, coordCol);

        if(rocketPosition.isPositionCalled()){
            System.out.println("Position already called");
            return;
        }

        rocketPosition.setPositionCalled(true);

        if(rocketPosition.getElementType().equals("S")){
            System.out.println("ship hit");
            if(rocketPosition.getPositionOwner().equals("computer")){
                rocketPosition.setElementType("S");
            }else{
                rocketPosition.setElementType("s");
            }
        }
        else if(rocketPosition.getElementType().equals("G")){

            if(rocketPosition.getPositionOwner().equals("computer")){
                rocketPosition.setElementType("G");
            }else{
                rocketPosition.setElementType("g");
            }

            System.out.println("Grenade Hit!");
            playGame(battleGround, sc, random,"computer");
        }
        else {
            System.out.println("nothing");
            rocketPosition.setElementType("*");
        }
    }


    /**
     * Checks if the provided coordinate is in a valid format (e.g., A1, B2).
     *
     * @param coord The coordinate to check.
     * @return {@code true} if the coordinate is valid, {@code false} otherwise.
     */
    public static boolean isValidCoordinate(String coord) {
        return coord.matches("[A-Ha-h][1-8]");
    }


    /**
     * Checks if the position at the specified coordinate on the BattleGround is already occupied.
     *
     * @param battleGround The BattleGround to check.
     * @param coord        The coordinate to check.
     * @return {@code true} if the position is occupied, {@code false} otherwise.
     */
    public static boolean isPositionOccupied(BattleGround battleGround, String coord) {
        int coordCol = coord.charAt(0) - 'A';
        int coordRow = Character.getNumericValue(coord.charAt(1)) - 1;

        Position position = battleGround.getElement(coordRow, coordCol);
        return !position.getElementType().equals("_");
    }
}
