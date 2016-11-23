import java.util.*;
import java.util.Random;

/**
 * Write a description of class DeckManipulator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DeckManipulator extends DeckBuilder
{

    protected Card currentCard;
    Random random = new Random();

    private void pickCard(int position) {
        if (checkDeck()) {
            currentCard = new Card(deck.get(position).getValue(), deck.get(position).getSuit());
            deck.remove(position);
            // replace card to bottom of pile
            deck.add(currentCard);
        }
    }

    protected boolean checkDeck() {
        if (deck.size() == 0) { System.out.println("Please create a deck."); return false; }
        return true;
    }

    public void createDeck() {super.createDeck(); currentCard = null; }    
    public void shuffleDeck() { super.shuffleDeck(); }
    public void clearDeck() { super.clearDeck(); currentCard = null; }
    public void printDeck() { super.printDeck(); }
    
    public void pickCardFromTop() {
        if (checkDeck()) {
            pickCard(0);
        }
    }

    public void pickCardFromRandom() {
        // pick a random number from 0 - 51, then pick a card from that position
        if (checkDeck()) {
            pickCard(random.nextInt(51));
        }
    }

    public void pickCardFromBottom() {
        if (checkDeck()) {
            pickCard(51);
        }
    }

    public void pickCardFromPosition(int position) {
        if (checkDeck()) {
            pickCard(position);
        }
    }

    public void displayCurrentCard() {
        if (checkDeck()) {
            if (currentCard == null) {
                System.out.println("Please pick a card.");
            } else {
                System.out.println(currentCard.printCard());
            }
        }
    }
}
