/**
 * SYST 17796 Intermission Uno Project
 * Brandon Lamarre 2-12-2025
 */
package ca.sheridancollege.project;




/**
 * A class to be used as the base Card class for the project. Must be general enough to be instantiated for any Card
 * game. Students wishing to add to the code should remember to add themselves as a modifier.
 *
 * @author dancye
 */
public class Card {
    private String colour; //card's colour value
    private int value; //card's value value

    public void setValue(int setter){ //sets the card's value
        this.value=setter; 
    }

    public int getValue() { //returns the card's value
        return value;
    }

    public void setColour(String setter){ //sets the card's colour
        this.colour=setter;
    }

    public String getColour() { //returns the card's colour
        return colour;
    }
    public enum CardType{
        NORMAL,PLUS_TWO,WILD
    }
    private CardType type;
    public CardType getType(){
        return type;
    }
    public void setType(CardType type){
        this.type = type;
    }
}
