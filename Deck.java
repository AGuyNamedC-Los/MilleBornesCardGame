package CardStuff;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

/* will create a deck with the generic card types in an array list */
public class Deck implements CardType{
    private String deckName;
    private String discardDeckName;
    private List<CardType> deck;
    private List<CardType> discardDeck;
    public Deck(String deckName, String discardDeckName){
        this.deckName = deckName;
        this.discardDeckName = discardDeckName;
        deck = new ArrayList<>();       // create an array so that the deck can hold [CardType] cards
        discardDeck = new ArrayList<>();        // create an array of discarded cards and will eventualy be shuffled back into the original deck
    }

    public void addToDeck(CardType card){
        deck.add(card);
    }
    public void addToDiscard(CardType card) {
        discardDeck.add(card);
        if(deck.isEmpty()){
            refillDeck();
            shuffleDeck();
        }

    }

    // will return the card at the "top" of the deck
    public CardType removeFromDeck(){
        if(deck.isEmpty()){
            System.out.println("The deck is empty!");
            System.out.println("Now refilling the deck");
            refillDeck();
            shuffleDeck();
            System.out.println("THIS DOESN'T WORK YET MAYBE");
            /* SOMETHING HAPPENED WHEN I CHOSE 30 KM, NOTHING MAY BE IN THE DISCARD DECK WHEN refillDeck() is called*/
            CardType card = deck.get(deck.size() - 1);      // will hold the card being drawn from the deck
            deck.remove(deck.size() - 1);       // actually remove it from the deck
            return card;
        }

        CardType card = deck.get(deck.size() - 1);      // will hold the card being drawn from the deck
        deck.remove(deck.size() - 1);       // actually remove it from the deck
        return card;
    }

    // function to refill the deck from the discard deck
    private void refillDeck(){
        for(int i = 0; i < discardDeck.size(); i++){        // however big the deck is....
            addToDeck(discardDeck.get(i));      // add a card from the discardDeck pile to the original deck
            discardDeck.remove(i);      // then actually remove it from the deck;
        }
    }

    /* will shuffle the items in the main deck */
    private void shuffleDeck(){
        Collections.shuffle(deck);
    }

    @Override
    public void getCard() {

    }

    @Override
    public int getCardValue() {
        return 0;
    }
}
