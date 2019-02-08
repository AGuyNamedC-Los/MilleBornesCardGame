package MilleBornesDriver;

import CardStuff.CardType;
import CardStuff.Deck;
import CardStuff.DistanceCard;
import Player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void greetings(String player1, String player2, int goal){
        System.out.println("Welcome [" + player1 + "] and [" + player2 + "] to the Mille Bornes Card game!");
        System.out.println("To win you must reach " + goal + "km's exactly!");
        System.out.println("ReadysetGO!");
    }

    public static void main(String args[]){
        int goal = 250;
        Player player1 = new Player("player1", 200);
        Player player2 = new Player("player2");
        greetings(player1.toString(), player2.toString(), goal);

        // creating an array list of distance cards. These also print out just fine
        List<DistanceCard> distanceCards = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            distanceCards.add(new DistanceCard(25, "25km"));
            distanceCards.add(new DistanceCard(50, "50km"));
        }

        CardType dcard25 = new DistanceCard(25, "25km");
        CardType dcard30 = new DistanceCard(30, "30km");
        CardType dcard50 = new DistanceCard(50, "50km");
        CardType dcard250 = new DistanceCard(250, "250km");
        CardType dcard20 = new DistanceCard(20, "20km");

        Deck deck = new Deck("Draw Deck", "Discard Deck");
        deck.addToDeck(dcard20);
        deck.addToDeck(dcard25);
        deck.addToDeck(dcard30);
        deck.addToDeck(dcard50);
        deck.addToDeck(dcard250);

        player1.addToHand(deck.removeFromDeck());
        player1.addToHand(deck.removeFromDeck());
        player1.addToHand(deck.removeFromDeck());
        player1.addToHand(deck.removeFromDeck());


        do
        {

            System.out.println("\nWhat would you like to do with your hand?");
            System.out.println(" ");
            deck.addToDiscard(player1.playCard());
            player1.addToHand(deck.removeFromDeck());
        }while(player1.getDistanceTraveled() != goal && player2.getDistanceTraveled() != goal);

        if(player1.getDistanceTraveled() == goal){
            System.out.println("Congrats [" + player1 + "] You've Won!");
        } else{
            System.out.println("Congrats [" + player2 + "] You've Won!");
        }
    }

}
