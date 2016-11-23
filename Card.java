
/**
 * Write a description of class Card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Card
{
    // instance variables - replace the example below with your own
    private int value;
    private String suit;
    
    public Card(int value, String suit) {
        this.value = value;
        this.suit = suit;
    }
    
    public String printCard() {
        return this.value + " of " + this.suit;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public String getSuit() {
        return this.suit;
    }
}
