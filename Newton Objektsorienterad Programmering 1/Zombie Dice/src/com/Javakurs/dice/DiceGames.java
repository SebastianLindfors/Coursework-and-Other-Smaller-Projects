package com.Javakurs.dice;

import java.util.Scanner;

/**
 *  This Program runs a game of Zombie Dice.
 *
 *  It uses the class GameBoard to handle the game logic, it uses
 *  the class Player to keep track of scores and turn order and
 *  it uses the class Die to create fair dice with custom sides.
 *
 *  This class ask for user input on the number of players and
 *  their names, then creates a list of the created players.
 *  Then it uses this list to create a gameBoard object witch
 *  runs the game itself.
 *
 *
 */

public class DiceGames {

    public static void main(String[] args) {

        Scanner inputScanner = new Scanner(System.in);

        int numberOfHumanPlayers;
        int numberOfComputerPlayers;

        System.out.println("Greetings! Shall we play a game of Zombie Dice?");
        System.out.print("How many human players wish to participate? ");
        numberOfHumanPlayers = inputScanner.nextInt();
        System.out.print("\nAnd how many computer players? ");
        numberOfComputerPlayers = inputScanner.nextInt();


       Player[] lop = null;

        try {
            lop = new Player[numberOfComputerPlayers + numberOfHumanPlayers];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("The number of players must be positive");
            System.exit(1);
        }


        for (int i = 0; i < numberOfHumanPlayers; i++) {
            System.out.print("What is the name of Player " + (i + 1) + "? ");
            String playerName = inputScanner.next();
            lop[i] = new Player(playerName, true);
        }
        for (int i = numberOfHumanPlayers; i < (numberOfComputerPlayers + numberOfHumanPlayers); i++) {
            lop[i] = new Player("CPU", false);
        }

        GameBoard gameEngine = new GameBoard(lop);
        gameEngine.playGame();

    }


}
