import java.util.*;
import java.util.Random;
import javax.swing.*;

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
    protected boolean discard = false;

    private void pickCard(int position) {
        currentCard = new Card(deck.get(position).getValue(), deck.get(position).getSuit());
        deck.remove(position);
        // replace card to bottom of pile
        if (!discard) {
            deck.add(currentCard);
        } else {
            discardPile.push(currentCard);
        }
    }

    protected boolean checkDeck() {
        if (deck.size() == 0) { JOptionPane.showMessageDialog(null, "Deck is empty.", "Deck Error", JOptionPane.ERROR_MESSAGE); return false; }
        return true;
    }

    public void createDeck() {super.createDeck(); currentCard = null; }    

    public void shuffleDeck() { super.shuffleDeck(); }

    public void clearDeck() { super.clearDeck(); currentCard = null; }

    public void printDeck() { super.printDeck(); }

    public void pickCardFromTop() {
        pickCard(0);
    }

    public void pickCardFromRandom() {
        // pick a random number from 0 - 51, then pick a card from that position
        pickCard(random.nextInt(deck.size()));
    }

    public void pickCardFromBottom() {
        pickCard(deck.size() - 1);
    }

    public void pickCardFromPosition(int position) {
        pickCard(position);
    }

}
