package org.example;
import java.util.Random;
import java.util.Scanner;
public class BattleShip {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BattleGround battleGround = new BattleGround();

        System.out.println("Hi, let’s play Battleship!");

        int shipCounter = 1;
        int grenadeCounter = 1;

        while(shipCounter <= 6){

            System.out.print("Enter the coordinates of your ship #"+shipCounter+": ");
            String coord = sc.nextLine().toUpperCase();

            int coordCol = coord.charAt(0)-'A';
            int coordRow = Character.getNumericValue(coord.charAt(1));

            Position newPosition = new Position("s","user", false);
            battleGround.setElement(coordRow-1, coordCol, newPosition);

            shipCounter++;

        }

        while(grenadeCounter <= 4){

            System.out.print("Enter the coordinates of your grenade #"+grenadeCounter+": ");
            String coord = sc.nextLine().toUpperCase();

            int coordCol = coord.charAt(0)-'A';
            int coordRow = Character.getNumericValue(coord.charAt(1));

            Position newPosition = new Position("g","user", false);
            battleGround.setElement(coordRow-1, coordCol, newPosition);

            grenadeCounter++;

        }

        setUpComputerShipGrenade(battleGround, sc);

        System.out.println("\nOK, the computer placed its ships and grenades at random. Let’s play.");

        printHiddenGrid(battleGround);

        play(battleGround, sc);

        printGrid(battleGround);
    }


    // Print the grid/BattleGround
    public static void printGrid( BattleGround battleGround){

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(battleGround.getElement(i,j).getElementType()+"\t");
            }
            System.out.println("\n");
        }
    }

    // Print the grid/BattleGround at the start of game
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


    // setting computer's ships and grenades
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

    public static String getRandomCoord(Random random){

        char randomCol = (char) ('A'+random.nextInt(8));
        int randomRow = random.nextInt(8) + 1;
        return randomCol+String.valueOf(randomRow);
    }

    public static void play(BattleGround battleGround, Scanner sc){
        Random random = new Random();

        while (true){
            playGame(battleGround, sc, random, "user");

            playGame(battleGround, sc,random, "computer");

            printHiddenGrid(battleGround);


            // check for winner
            String winner = checkForWinner(battleGround);
            if(winner != null){
                System.out.println("Game over!!");
            }
        }
    }

    private static String checkForWinner(BattleGround battleGround) {

            int userShips = 0;
            int computerShips = 0;

            for(int i =0 ; i<8; i++){
                for(int j=0; j< 8;j++){
                    Position position = battleGround.getElement(i,j);

                    if(position.getElementType().equals("S") && !position.isPositionCalled()){
                        userShips++;
                    }else if(position.getElementType().equals("s") && !position.isPositionCalled()){
                        computerShips++;
                    }
                }
            }

            if(userShips == 0){
                return "computer";
            } else if (computerShips == 0) {
                return "user";
            }else{
                return null;
            }
    }

    public static void playGame(BattleGround battleGround, Scanner sc, Random random, String player){

        System.out.print("position of your rocket: ");

        String coord = player.equals("user") ? sc.nextLine().toUpperCase() : getRandomCoord(random);

        int coordCol = coord.charAt(0)-'A';
        int coordRow = Character.getNumericValue(coord.charAt(1));

        Position rocketPosition = battleGround.getElement(coordRow-1, coordCol);

        if(rocketPosition.isPositionCalled()){
            System.out.println("Position already called");
//            playGame(battleGround, sc,random, "computer");
            return;
        }

        rocketPosition.setPositionCalled(true);

        if(rocketPosition.getElementType().equals("S")){
            //  Ship Hit
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
            // hit a grenade
            // computer will play twice in a row
            playGame(battleGround, sc, random,"computer");
        }
        else {
            // no hit
            System.out.println("nothing");
            rocketPosition.setElementType("*");
        }
    }
}
