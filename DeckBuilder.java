import java.util.*;
import java.util.Collections;

/**
 * Write a description of class DeckBuilder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DeckBuilder
{
    protected ArrayList<Card> deck = new ArrayList<Card>(51);

    private void createDeckHelper(String suit) {
        for (int i = 1; i < 14; i++) {
            deck.add(new Card(i, suit));
        }
    }

    protected void createDeck() {
        this.clearDeck();
        createDeckHelper("Spades");
        createDeckHelper("Diamonds");
        createDeckHelper("Hearts");
        createDeckHelper("Clubs");
    }

    protected void shuffleDeck() {
        Collections.shuffle(deck);
    }

    protected void clearDeck() {
        deck.clear();
    }

    protected void printDeck() {

        if (deck.size() == 0) {
            System.out.println("Please create a deck first.");
        }
        for(int x = 0; x < deck.size(); x++) {
            System.out.println(deck.get(x).printCard());
        }
    }
}
