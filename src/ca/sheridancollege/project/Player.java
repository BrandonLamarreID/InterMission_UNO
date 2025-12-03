/**
 * SYST 17796 Intermission Uno Project
 * Brandon Lamarre 2-12-2025
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
public class Player {
    private static InputChecker inChk = new InputChecker(); //construct input checker
    private ArrayList<Card> hand = new ArrayList<>(); //create hand array
    private CardManager manager = new CardManager(this.getName()); //construct card manager for this player instance
    private int id; //card ID initializer
    private int name; //the unique name for this player
    private Random randy = new Random();
    private int roboid=0;

    /**
     * A constructor that allows you to set the player's unique ID
     *
     * @param name the unique ID to assign to this player.
     */
    public Player(int name) {
        this.name = name;
    }
    public int getHandSize(){ //return hand size of player instance
        return hand.size();
    }
    public CardManager getCardManager(){ //return card manager of player instance
        return manager;
    }

    public void getHand(){ //print out hand of player instance
        for (Card c:hand){
            System.out.println((hand.indexOf(c)+1)); //print index number+1 of card
            System.out.println("[----------]");
            System.out.println(c.getColour()+" "+c.getValue()); //print card value
            System.out.println("[----------]");
            System.out.println("");
        } 
    }
    public ArrayList<Card> getHandOBJ(){ //return hand object of player instance
        return hand;
    }
    public Card getCardById(int id){ //return card by ID
        return hand.get(id); 
    }
    public void removeCardById(int id){ //remove card from instance hand by ID
        this.hand.remove(id);
    }
    public Card playCard(Card active){ //play card
        int tf = 0;
        while (tf==0){
            System.out.println("please enter the number of the card you wish to play:");
            this.id =inChk.intCheck(); //input checker
            tf=manager.playcard(hand.get(id-1),active); //check if selected card is valid, if no, ask for input again
        }
        return hand.get(id-1); // if yes, return that card's ID
    }
    public Card aiPlayCard(Card active){ //opponent play card
        int tf = 0;
        while (tf==0){
            roboid = randy.nextInt(hand.size()); //get random ID within the opponent's hand
            tf=manager.roboPlaycard(hand.get(roboid),active); //check if card selected is valid, if no, repeat
        }
        return hand.get(roboid); //if yes, return ID for use
    }

    public void addHand(Card card){ //add card to hand of instance
        this.hand.add(card);
    }

    /**
     * @return the player name
     */
    public int getName() {
        return name;
    }
    public int getId(){ //return card ID
        return id;
    }
    public int getRoboId(){ //returns card ID for Opponent
        return roboid; //this is seperate to make generation of random numbers easier without adjusting the main card ID for the file
    }

    /**
     * Ensure that the playerID is unique
     *
     * @param name the player name to set
     */
    public void setName(int name) {
        this.name = name;
    }

}
