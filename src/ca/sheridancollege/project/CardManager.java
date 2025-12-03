/**
 * SYST 17796 Intermission Uno Project
 * Brandon Lamarre 2-12-2025
 */
package ca.sheridancollege.project;
import java.util.ArrayList;

public class CardManager {
    private int id; //private ID int

    public CardManager(int id) { //constructor for class
        this.id=id;
    }

    public int getId(){ //return ID
        return id;
    }

    public int playcard(Card card,Card active){ //get card on top of active pile, and selected card from player's hand
        if (card.getColour() == active.getColour() ||  
        card.getValue() == active.getValue()){ //check if the card is valid
            System.out.println("card valid");
            System.out.println("~~~~~~~~~~~");
            System.out.println("");
            return 1; //if yes, return 1
        } 
        else {
            System.out.println("card invalid");
            System.out.println("~~~~~~~~~~~");
            System.out.println("");
            return 0; //if no, return 0
        }
    }
    public int roboPlaycard(Card card,Card active){ //get selected card and card on top of active pile
        if (card.getColour() == active.getColour() ||  
            card.getValue() == active.getValue()){ //check if card is valid
            return 1; //if yes, return 1 
        }
        else {
            return 0; //if no, return 0
        }
    }

    public int roboCheckCard(ArrayList<Card> hand,Card active){ //check if player has any valid cards in their hand
        for (int i = 0; i < hand.size(); i++){
            if (hand.get(i).getColour() == active.getColour() || 
                hand.get(i).getValue() == active.getValue()){
                return 1; //if yes, return 1
            }
        }
        return 0; //if no, return 0
    }

}