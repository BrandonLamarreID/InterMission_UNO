/**
 * SYST 17796 Intermission Uno Project
 * Brandon Lamarre 2-12-2025
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;
/**
 * A concrete class that represents any grouping of cards for a Game. HINT, you might want to subclass this more than
 * once. The group of cards has a maximum size attribute which is flexible for reuse.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
public class GroupOfCards {
    
    //The group of cards, stored in an ArrayList
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Card> tempcards = new ArrayList<>(); //temporary group of cards stored within arraylist
    private int size;//the size of the grouping
    private int[] validNumbers = {0,1,2,3,4,5,6,7,8,9}; //valid numbers for first pass
    private int[] validNumbers2 = {1,2,3,4,5,6,7,8,9}; //valid numbers for second pass
    private String[] validColours = {"Red","Blue","Green","Yellow"}; //valid colours for cards

    public GroupOfCards(int size) { 
        this.size = size;
    }

    public int getRemainingSize(){ //returns remaining size of active deck
        return cards.size();
    }
    public Card getCard(int i){  //get card at bottom of pile
        return cards.get(i);
    }
    public Card getLastCard(){ //get card on top of pile
        return cards.getLast();
    }
    public void removeCard(){ //remove card from pile
        cards.removeFirst();
    }
    public Card drawCard() {
    Card top = getLastCard();  
    removeCard();              
    return top;                
}

    public void setCards(){ //initialize inactive card pile
        for (String c :validColours){ //iterate through colours
            for (int n :validNumbers){ //iterate through numbers
                Card card = new Card(); //make new card
                card.setColour(c); //set colour
                card.setValue(n); //set value
                this.tempcards.add(card); //add to temp array
            }
            for (int n :validNumbers2){ //second pass for number iteration
                Card card = new Card();
                card.setColour(c);
                card.setValue(n);
                this.tempcards.add(card);
            }
        }
    }
    public void addCards(){ //add cards from temp array to inactive pile
        shuffletemp();
        for (int i = 0; i < size; i++){
            this.cards.add(this.tempcards.get(i));
        }
    }
    public void addCardsFrom(GroupOfCards in){ //add cards from input
        for (int i = 0; i <= 60; i++){
            this.cards.add(in.cards.get(i));
        }
        shuffle(); //shuffle deck
    }
    public void addCard(Card card){ //add card to pile from input
        this.cards.add(card);
    }
    // Skip the next player's turn
private void skipNextTurn(Player player) {
    System.out.println(player.getName() + " loses a turn!");
    // You can increment cPlayer or set a flag here depending on how turns are managed
}

// Set the current active color after a wild card
private void setCurrentColor(String color) {
    System.out.println("Color changed to " + color);
    // Store chosen color in a field if you want to enforce it
    // Example: this.activeColor = color;
}


    public void shuffle() { //shuffle main array
        Collections.shuffle(cards);
    }
    public void shuffletemp() { //shuffle temp array
        Collections.shuffle(tempcards);
    }

    /**
     * @return the size of the group of cards
     */
    public int getSize() { 
        return size;
    }

    /**
     * @param size the max size for the group of cards
     */
    public void setSize(int size) {
        this.size = size;
    }

}//end class
