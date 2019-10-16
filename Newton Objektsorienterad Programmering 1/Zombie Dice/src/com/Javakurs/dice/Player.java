package com.Javakurs.dice;
import java.util.ArrayList;

/*
 * A class representing the different players in the Zombie Dice game.
 *
 * Class keeps track of a players Name, their dice pool, their score
 * and whether it is a human player or a CPU.
 *
 * NOTE: Only intended for use in one project, thus not written with
 * transferability un mind. For instance: class lacs several get and
 * set methods not use in the ZOmbie Dice game.
 */

public class Player {

    // ---- Private Variables ---- //

    private ArrayList<Die> dicePool;

    private boolean isHumanPlayer;

    private int score;

    private String playerName;


    // ---- Constructor ---- //

    public Player(String name, boolean human) {

        score = 0;

        dicePool = new ArrayList<>();

        isHumanPlayer = human;
        playerName = name;

    } //Constructs a new player with an empty dice pool and a score of 0

    // ---- Methods ---- //

    public boolean isHuman() {return isHumanPlayer;}

    public String[] rollAllDice() {

        String[] rolledDice = new String[dicePool.size()];
        int i = 0;
        for (Die d : dicePool) {
            d.roll();
            rolledDice[i] = d.getCurrentFaceString();
            i++;
        }
        return rolledDice;
    } //Rolls all player die and RETURNS all the current faces.

    public void addDieToPool(Die d) { dicePool.add(d); }

    public void removeDieFromPool(Die d) {dicePool.remove(d); }

    public void discardAllDice() {dicePool = new ArrayList<>(); } //Empties the dicePool by creating a new ArrayList.

    public void addToScore(int deltaScore) { score = score + deltaScore; } //Note: May be negative

    // ---- Get Methods ---- //

    public ArrayList<Die> getDicePool() { return dicePool; }

    public int getScore() { return score; }

    public int getDiePoolSize() { return dicePool.size(); }

    public String getPlayerName() { return playerName; }


}
