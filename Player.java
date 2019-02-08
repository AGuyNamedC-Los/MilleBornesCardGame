package Player;

import CardStuff.CardType;
import CardStuff.DistanceCard;
import CardStuff.RollCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/* This class handles everything that has to do with how a player interacts with the cards */
public class Player implements CardType {
    private String name;
    private int status;     // indicates whether the player is able to use mileage cards
    private int distanceTraveled;       // holds how far the player has traveled
    private static List<CardType> hand;

    public Player(String name){
        this.name = name;
        this.status = 1;     // means the player cant move until a roll card is played
        this.distanceTraveled = 0;      // every player will always start by having traveled 0km!
        hand = new ArrayList<>();       // will allow the player to have a "hand" with no cards initially
    }

    /* DELETE THE INT PARAMETER AFTER ALL TESTING IS DONE */
    public Player(String name, int distance){
        this.name = name;
        this.status = 0;     // means the player cant move until a roll card is played
        this.distanceTraveled = distance;      // every player will always start by having traveled 0km!
    }

    void changePlayerStatus(RollCard card){
        // return the value of the roll card and set it equal to the status
    }

    // will return the player's status
    public int getPlayerStatus(){ return status; }

    // will return the player's name
    @Override
    public String toString(){ return name; }

    // will update the player's distance traveled
    public void updateDistanceTraveled(DistanceCard card){
        // should return the distance card's value and add it to the player's distance
        distanceTraveled += card.getDistance();
    }

    // will return how far the player has traveled
    public int getDistanceTraveled(){
        return distanceTraveled;
    }

    // adds cards to the player's hand but wont allow it to exceed 7 cards
    public static void addToHand(CardType card){
        if(hand.size() > 6){
            System.out.println("You have too many cards! Play or Discard one!");
        } else{
            hand.add(card);
        }
    }

    // will print out what cards the player has
    public void showHand(){
        int i = 0;
        System.out.println("[" + name + "]'s hand");
        for(CardType card : hand){
            System.out.print("[" + i + "] ");
            card.getCard();
            i++;
        }
        System.out.println("");
    }

    /* Maybe add a function called discardCard and give the choices in main to either play or discard a card */
    // will allow the player to use a card
    public CardType playCard(){
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        int choice;
        int currentCard;
        boolean playableCard = false;

        /* will check to make sure that the player has a card in their hand that is actually playable */
        for(int i = 0; i < hand.size(); i++){
            currentCard = hand.get(i).getCardValue();
            if(currentCard + distanceTraveled <= 250){
                playableCard = true;
                break;
            }
        }

        /* If the player does not have a playable card then they will need to discard a card and get a new one from the deck */
        if(playableCard == false){
            System.out.println("Looks like you cant play any of the cards in your hand!");
            System.out.println("Please discard one of you cards!");
            showHand();
            if(reader.hasNextInt() )
                choice = reader.nextInt(); // if there is another number
            else
                choice = 0; // nothing added in the input

            choice = verifyChoice(choice);

//            while(choice > hand.size() - 1){
//                System.out.println("That card does not exist! Try again!");
//                showHand();
//                if(reader.hasNextInt() )
//                    choice = reader.nextInt(); // if there is another number
//                else
//                    choice = 0; // nothing added in the input
//            }
            CardType discardedCard = discardCard(choice);
            return discardedCard;
        }

        /* prompt the user to pick a card */
        System.out.println("Pick your card!");
        showHand();
        if(reader.hasNextInt() )
            choice = reader.nextInt(); // if there is another number
        else
            choice = 0; // nothing added in the input

        int handSize = hand.size() - 1;     // represents the highest index in the player's hand that they can choose from
        /* makes sure that the user doesn't select an index from their hand that is between 0 - [current hand size] */
//        while(choice > handSize && handSize != 0) {
//            System.out.println("That card does not exist! Try again!");
//            showHand();
//            if(reader.hasNextInt() )
//                choice = reader.nextInt(); // if there is another number
//            else
//                choice = 0; // nothing added in the input
//        }
        choice = verifyChoice(choice);

        currentCard = hand.get(choice).getCardValue();
        if(currentCard + distanceTraveled >= 251){     // makes sure the user doesn't select a card that puts him over the goal limit
            while(currentCard + distanceTraveled >= 251){
                System.out.println("You have gone over the goal of 250 with that card!");
                System.out.println("Please select another card!");
                System.out.println(this.name + distanceTraveled);
                showHand();
                choice = reader.nextInt();

                choice = verifyChoice(choice);

//                while(choice > handSize) {      // makes sure the user doesn't select an card that isn't in his hand
//                    System.out.println("That card does not exist! Try again!");
//                    showHand();
//                    if(reader.hasNextInt() )
//                        choice = reader.nextInt(); // if there is another number
//                    else
//                        choice = 0; // nothing added in the input
//                }

                currentCard = hand.get(choice).getCardValue();
            }
        }

        distanceTraveled += hand.get(choice).getCardValue();        // update the player's distance by adding it with the distance card played
        CardType cardToDiscard = hand.get(choice);
        hand.remove(choice);        // remove the card from the players hand
        return cardToDiscard;       // return the card the player chose so it can be returned to the discard deck
    }


    /* discards a card from the player's hand and returns it to the discard deck */
    public CardType discardCard(int choice){
        CardType cardToDiscard = hand.get(choice);
        hand.remove(choice);
        return cardToDiscard;        // this will always be return to the discard deck
    }

    /* function to make sure the player doesn't choose a card [index] that doesn't exist */
    private int verifyChoice(int choice){
        Scanner reader = new Scanner(System.in);
        int handSize = hand.size() - 1;

        /* MAYBE INCLUDE IF THE PLAYER HAS AN EMPTY HAND SO HAND SIZE COULD BE ZERO */
        while(choice > handSize || choice < 0){
            System.out.println("That card does not exist!");
            System.out.println("Pick one from whats available in your hand please!");
            showHand();
            if(reader.hasNextInt() )
                choice = reader.nextInt(); // if there is another number
            else
                choice = 0; // nothing added in the input

        }

        return choice;      // after everything is verified, return the player's choice (index for the [hand] array )
    }

    @Override
    public void getCard() {

    }

    @Override
    public int getCardValue() {
        return 0;
    }
}
