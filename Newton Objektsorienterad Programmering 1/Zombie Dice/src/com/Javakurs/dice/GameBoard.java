package com.Javakurs.dice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class contain the main game logic for the Zombie Dice game.
 *
 * This class uses the Player class, to keep track of scores and individual dice,
 * and also uses the Die class for generating fair dice rolls and keeping track of sides.
 *
 * To run a game, construct a new gameBoard object with an ArrayList of Players, then call
 * the playGame() method on that gameBoard object. Constructor does not run game automatically.
 *
 *
 */

public class GameBoard {

    // ---- Private Variables ---- //

    private ArrayList<Die> dieCup;

    private int currentTurn;

    private Player currentPlayer;
    private Player[] listOfPlayers;

    //The following three arrays are used to define the sides of the different dice used in Zombie Dice.
    private final String[] GREEN_OPTIONS = {"Shotgun","Runner","Runner","Brain","Brain","Brain"};     // The Green Dice
    private final String[] YELLOW_OPTIONS = {"Shotgun","Shotgun","Runner","Runner","Brain","Brain"};  // The Yellow Dice
    private final String[] RED_OPTIONS =   {"Shotgun","Shotgun","Shotgun","Runner","Runner","Brain"}; // The Red Dice

    // ---- Constructor ---- //

    public GameBoard(Player[] playerList) {

        listOfPlayers = playerList;
        currentPlayer = listOfPlayers[0];

        currentTurn = 0;

        dieCup = new ArrayList<Die>();
        resetDieCup();


    }

    // ---- Methods ----//

    //Calling this method starts the game. It will be played between the players in the 'listOfPlayers' array.
    public void playGame() {
        System.out.println("Welcome to a new game of Zombie Dice.");
        Scanner inputScanner = new Scanner(System.in);
        int choice = -1;

        String playerNames = "";
        for (Player p : listOfPlayers) {
            playerNames += p.getPlayerName() + "  ";
        }

        System.out.println("This game's players are: " + playerNames);

        //This while loops lasts for the entire game,
        //and every time this loop completes one round has passed in the game.
        boolean lastTurn = false;
        while(!lastTurn) {
            currentTurn++;
            System.out.println("It is now turn: " + currentTurn);

            //This for-loop loops over each player and gives each player their turn.
            for (Player p : listOfPlayers) {
                currentPlayer = p;
                int shotInTheFace = 0;
                int brainsEaten = 0;

                System.out.println("It is now " + currentPlayer.getPlayerName() + "'s turn to roll the ZOMBIE DIE");
                drawDice(currentPlayer);

                // This loop represents a single players turn with the die. This loop breaks if a player gets 3
                // hits or chooses to stop rolling and cash in their brains for points.
                while(true) {

                    String[] rolledResult = currentPlayer.rollAllDice();
                    ArrayList<Die> rolledDice = new ArrayList<>(currentPlayer.getDicePool());

                    System.out.print(currentPlayer.getPlayerName() + " rolled: ");
                    for (Die d : rolledDice) {
                        System.out.print(d.getCurrentFaceString() + "\t");
                        if (d.getCurrentFaceString().equals("Shotgun")) {
                            shotInTheFace++;
                            currentPlayer.removeDieFromPool(d);
                        } else if (d.getCurrentFaceString().equals("Brain")) {
                            brainsEaten++;
                            currentPlayer.removeDieFromPool(d);
                        }
                    }
                    System.out.println("\n " + currentPlayer.getPlayerName() + "\t Brains Eaten: " + brainsEaten);
                    System.out.println("\t\t Shots: " + shotInTheFace);
                    if (shotInTheFace >= 3) {
                        System.out.println(currentPlayer.getPlayerName() + " got shot in the face! Their turn is over!");
                        break;
                    }
                    if (currentPlayer.isHuman()) {
                        System.out.println("Please choose an option: ");
                        System.out.println("1. Draw up to 3 dice and roll again.");
                        System.out.println("2. End your turn and score.");
                        System.out.print("Option 1 or 2: ");

                        choice = inputScanner.nextInt();
                    }
                    else {
                        if (brainsEaten < 1) choice = 1;
                        else if (shotInTheFace == 0) choice = 1;
                        else choice = 2;
                    }
                    if (choice == 1) {
                        drawDice(currentPlayer);
                        continue;
                    }
                    else if (choice == 2) {
                        currentPlayer.addToScore(brainsEaten);
                        currentPlayer.discardAllDice();
                        if (currentPlayer.getScore() >= 13) {
                            lastTurn = true;
                            System.out.println(currentPlayer.getPlayerName() + " has enough points to end the game! This is the FINAL TURN!");
                        }
                        break;
                    }
                    else System.exit(0);
                }

            }
            System.out.println("At the end of turn " + currentTurn + " the Score are:");
            for (Player p : listOfPlayers) {
                System.out.println(p.getPlayerName() + "\t\t" + p.getScore());
            }
        }


    }

    //Refills a players hand up to three dice after they have been taken away after rolling.
    public void drawDice(Player p) {
        HashMap<String, Integer> dieColours = new HashMap<>();
        while(currentPlayer.getDiePoolSize() < 3) {
            if (dieCup.isEmpty()) resetDieCup();

            int index = (int) (Math.random()*dieCup.size());
            Die newDie = dieCup.get(index);

            if (dieColours.containsKey(newDie.getDieName())) {
                dieColours.put(newDie.getDieName(), dieColours.get(newDie.getDieName()) + 1); //Increment colour count vy 1;
            }
            else {
                dieColours.put(newDie.getDieName(), 1);
            }

            dieCup.remove(index);
            p.addDieToPool(newDie);

        }
        System.out.print(p.getPlayerName() + " drew: ");
        for (String dieColour : dieColours.keySet()) {
            System.out.print(dieColours.get(dieColour)  + " " + dieColour + " ");
        }
        System.out.println("");
    }

    //Resets the dice cup to contain the 13 starting dice.
    public void resetDieCup() {
        for (int i = 1; i <= 6; i++) {
            dieCup.add(new Die(GREEN_OPTIONS, "Green"));
        }
        for (int i = 1; i <= 4; i++) {
            dieCup.add(new Die(YELLOW_OPTIONS, "Yellow"));
        }
        for (int i = 1; i <= 3; i++) {
            dieCup.add(new Die(RED_OPTIONS, "Red"));
        }
    }

}

