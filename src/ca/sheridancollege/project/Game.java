/**
 * SYST 17796 Intermission Uno Project
 * Brandon Lamarre 2-12-2025
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class that models your game. You should create a more specific child of this class and instantiate the methods
 * given.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
public class Game {
    private static Scanner scanOBJ = new Scanner(System.in); 
    private static InputChecker inChk = new InputChecker();

    private final String name;//the title of the game
    private ArrayList<Player> players = new ArrayList();// the players of the game
    private int oppCount =0; //opponent count
    private int loopC =0; //loop variable
    private String input=""; //initialize input variable
    private int cCount =0; //selected card count for each player
    private int cPlayer =0; //ID of current player, used to change between user and other opponents
    private GroupOfCards InActive = new GroupOfCards(76); //create inactive pile of cards to draw from with size 76
    private GroupOfCards Active = new GroupOfCards(0); //create active pile of cards to put stuff onto with size 0


    public Game() {
        this.name = "Intermission";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the players of this game
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }
    
    public Card getActiveCard(){ //returns card on top of Active pile
        return this.Active.getLastCard();
    }
    public void placeCard(Card card){ //adds card to top of active pile
        Active.addCard(card);
    }

    /**
     * @param players the players of this game
     */
    public void setPlayers(Player player) {
        this.players.add(player);
    }
    public void play() {
        intro(); //call the intro
        mMenu(); //call the main initialization menu
        InActive.setCards(); //sets up default cards for play in temp array
        InActive.addCards(); //moves temp array to main pile of cards
        Active.addCard(InActive.getCard(0)); //adds one card to start off the game
        InActive.removeCard(); //removes that card from the inactive pile
        for (int i = 0; i < oppCount; i++){ //spawns players according to user's selection
            Player player = new Player(i);
            setPlayers(player);
        }
        for (Player player:players){ //gives each player their cards according to user's selection
            for (int i = 0; i < cCount; i++){
                player.addHand(this.InActive.getCard(0));
                this.InActive.removeCard();
            }
        }
        while (loopC==0){ //main loop
            if (InActive.getRemainingSize()<=1){ //if inactive pile is nearly empty, take cards from already played pile
                    InActive.addCardsFrom(Active);
                }
            if (cPlayer==0){
                gMenu();
                if (players.get(0).getHandSize()==0){ //if player's handsize = zero, they win
                    System.out.println("You Win!");
                    for (int i = 0; i < oppCount; i++){
                        System.out.println("Player "+(players.get(i).getName()+1)+" had "+players.get(i).getHandSize()+" Card(s) left!");
                        System.out.println("~~~~~~~~~~~~~~~~");
                    }
                    System.exit(0);
                }
            }
            else {
                if (InActive.getRemainingSize()<=2){ //if inactive pile is nearly empty, take cards from already played pile
                    InActive.addCardsFrom(Active);
                }
                System.out.println("it is now Player "+(cPlayer+1)+"'s turn."); //announce whos turn it is
                System.out.println("~~~~~~~~~~~");
                int played=0;
                while (played==0){ 
                    played = players.get(cPlayer).getCardManager().roboCheckCard(players.get(cPlayer).getHandOBJ(), getActiveCard()); //check if opponent has any valid cards
                    if (played==0){ //if they do not:
                        players.get(cPlayer).addHand(this.InActive.getCard(0));  //grab card from inactive pile
                        this.InActive.removeCard(); //remove that card from inactive pile
                        System.out.println("player "+(cPlayer+1)+" Picked up a card."); //announce who picked up a card
                        System.out.println("~~~~~~~~~~~"); 
                        System.out.println("");
                    } //repeat if still no valid cards
                }
                Active.addCard(players.get(cPlayer).aiPlayCard(Active.getLastCard())); //opponent plays card
                players.get(cPlayer).removeCardById(players.get(cPlayer).getRoboId()); //remove card from opponent's hand
                //i wanted to do this more encapsulated by having it be within the PLAYER.java file, however if i wanted to use a RETURN function to bring the selected card's
                //data to GAME.java, i was unable to cleanly remove a card while also calling for it's data
                System.out.println((cPlayer+1)+" Played a"); //announce what card the opponent played
                System.out.println("[----------]");
                System.out.println(this.Active.getLastCard().getColour()+" "+this.Active.getLastCard().getValue()); 
                System.out.println("[----------]");
                System.out.println("~~~~~~~~~~~~~");
                System.out.println("");
                if (players.get(cPlayer).getHandSize()==0){ //check if opponent's handsize =zero, if yes they win
                    System.out.println((cPlayer+1)+" Wins!");
                    for (int i = 0; i < oppCount; i++){
                        System.out.println("Player "+(players.get(i).getName()+1)+" had "+players.get(i).getHandSize()+" Card(s) left!");
                        System.out.println("~~~~~~~~~~~~~~~~");
                    }
                    System.exit(0);
                }
                cPlayer+=1; //tick the turn counter
                if (cPlayer>=players.size()){ //if turn counter is greater or equal to the amount of players, reset to 0
                    cPlayer=0;
                }
            }
            

        }
    }
    public static void intro(){
        System.out.println("Wecome to"); //basic text intro
        System.out.println("    ____      __                      _           _                __  __          \r\n" + //
                        "   /  _/___  / /____  _________ ___  (_)_________(_)___  ____     / / / /___  ____ \r\n" + //
                        "   / // __ \\/ __/ _ \\/ ___/ __ `__ \\/ / ___/ ___/ / __ \\/ __ \\   / / / / __ \\/ __ \\\r\n" + //
                        " _/ // / / / /_/  __/ /  / / / / / / (__  |__  ) / /_/ / / / /  / /_/ / / / / /_/ /\r\n" + //
                        "/___/_/ /_/\\__/\\___/_/  /_/ /_/ /_/_/____/____/_/\\____/_/ /_/   \\____/_/ /_/\\____/ \r\n" + //
                        "                                                                                   "); //made using patorjk ascii text generator
    }

    public void mMenu(){ //requests initialization data from user
        System.out.println("How many Opponents would you like?"); 
        this.oppCount= (inChk.intCheck()+1); //typecheck the input, add one more player for the user
        System.out.println("How many cards do you want each player to start with?");
        this.cCount=inChk.intCheck(); //typecheck the input

    }
    public void gMenu(){
        Player you = players.get(0); //user is player 0
        System.out.println(""); 
        System.out.println("What would you like to do:"); //text information for controls
        System.out.println("Please type the text within the (Brackets) for your selection");
        System.out.println("(Play) card");
        System.out.println("Check (Hand)");
        System.out.println("Check (Active)");
        System.out.println("(Pick) up a card");
        System.out.println("Card (Count) of each player");
        System.out.println("(Quit)");
        this.input= scanOBJ.nextLine(); //scan for input
        if (this.input.equalsIgnoreCase("quit")){ //if quit then stop program
            System.out.println("Have a good day");
            this.loopC=1;
        }
        else if (this.input.equalsIgnoreCase("play")){ //if play card
            int played=0;
            while (played==0){
                played = you.getCardManager().roboCheckCard(you.getHandOBJ(), getActiveCard()); //check if you have valid cards
                if (played==0){
                    System.out.println("No valid plays, please pick up a card");
                    return; //if not, try a different command
                }
                else if (played==1){
                    Active.addCard(you.playCard(Active.getLastCard())); //if yes, select which card to play
                    you.removeCardById((you.getId()-1)); //remove card from user
                    cPlayer+=1; //advance turn counter
                }
                System.out.println((cPlayer)+" Played a"); //announce what card you played
                System.out.println("[----------]");
                System.out.println(this.Active.getLastCard().getColour()+" "+this.Active.getLastCard().getValue());
                System.out.println("[----------]");
                System.out.println("~~~~~~~~~~~~~");
                System.out.println("");
            }
            
        }
        else if (this.input.equalsIgnoreCase("hand")){ //print out user's hand
            you.getHand();
        }
        else if (this.input.equalsIgnoreCase("active")){ //print out card on top of active pile
            System.out.println("[----------]");
            System.out.println(this.Active.getLastCard().getColour()+" "+this.Active.getLastCard().getValue());
            System.out.println("[----------]");
        }
        else if (this.input.equalsIgnoreCase("pick")){ //take card from inactive pile

            you.addHand(this.InActive.getCard(0)); //add card to user's hand
            System.out.println("player "+(players.indexOf(you)+1)+" Picked up a card.");
            System.out.println("[----------]");
            System.out.println(this.InActive.getCard(0).getColour()+" "+this.InActive.getCard(0).getValue()); //announce what card you got
            System.out.println("[----------]");
            System.out.println("~~~~~~~~~~~~~~~~");
            this.InActive.removeCard(); //remove card from inactive pile
        }
        else if (this.input.equalsIgnoreCase("devcheckall")){ //dev command to check hands of all players
            for (int i = 0; i < oppCount; i++){
                System.out.println(players.get(i).getName());
                players.get(i).getHand();
                System.out.println("~~~~~~~~~~~~~~~~");
            }
        }
        else if (this.input.equalsIgnoreCase("devcheckallindeck")){ //dev command to check hands of all players
            for (int i = 0; i < InActive.getRemainingSize(); i++){
                System.out.println(InActive.getCard(i).getColour()+" "+InActive.getCard(i).getValue());
                System.out.println("~~~~~~~~~~~~~~~~");
            }
        }
        else if (this.input.equalsIgnoreCase("devremovecard")){ //dev command to remove card from user's hand by ID
            int temp1;
            temp1=inChk.intCheck();
            you.removeCardById((temp1-1));
        }
        else if (this.input.equalsIgnoreCase("devremovefrompile")){ //dev command to remove cards from inactive pile
            for (int i = 0; i < 67; i++){
                Active.addCard(InActive.getCard(0));
                this.InActive.removeCard();
            }
        }
        else if (this.input.equalsIgnoreCase("count")){ //print how many cards each player has
            for (int i = 0; i < oppCount; i++){
                System.out.println((players.get(i).getName()+1));
                System.out.println( players.get(i).getHandSize());
                System.out.println("~~~~~~~~~~~~~~~~");
            }
        }
        else{
            System.out.println("Please try entering your command again."); //edgecase if you dont put the right command in
        }
        
    }

}//end class
