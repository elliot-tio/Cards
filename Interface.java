import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Write a description of class Interface here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Interface extends JPanel
{
    public Interface() {
        DeckManipulator deck = new DeckManipulator();
        
        this.setPreferredSize(new Dimension(200, 300));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        final JLabel deckLab = new JLabel("No Deck Available");
        deckLab.setBorder(BorderFactory.createEtchedBorder());
        this.add(deckLab);

        JButton createButt = new JButton("Create a Deck");
        createButt.setToolTipText("Creates a deck of 52 cards");
        this.add(createButt);
        
        class CreateDeckActionListener implements ActionListener {

            public void actionPerformed(ActionEvent ae) {
                deck.createDeck();
                deckLab.setText("Deck Available");
                invalidate();
                validate();
                repaint();
            }
        }
        createButt.addActionListener(new CreateDeckActionListener());
        
        JButton pickTopButt = new JButton("Pick Card from Top");
        createButt.setToolTipText("Picks a card from the top of the deck, and replaces it at the bottom");
        this.add(pickTopButt);
        
        final JLabel currCardLab = new JLabel("No Current Card Available");
        currCardLab.setBorder(BorderFactory.createEtchedBorder());
        this.add(currCardLab);
        
        class PickTopActionListener implements ActionListener {

            public void actionPerformed(ActionEvent ae) {
                if (deck.checkDeck()){
                    deck.pickCardFromTop(); 
                    currCardLab.setText(deck.currentCard.getValue() + " of " + deck.currentCard.getSuit());
                }
            }
        }
        pickTopButt.addActionListener(new PickTopActionListener());
        
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Cards Master");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLayout(new FlowLayout());

        f.add(new Interface());

        f.pack();
        f.setVisible(true);
    }
}
