import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

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

        this.setPreferredSize(new Dimension(230, 400));
        this.setLayout(new GridLayout(11, 1));
        this.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        final JLabel deckLab = new JLabel("No Deck Available", SwingConstants.CENTER);
        deckLab.setBorder(BorderFactory.createEtchedBorder());
        this.add(deckLab);

        final JLabel currCardLab = new JLabel("No Current Card Available", SwingConstants.CENTER);
        currCardLab.setBorder(BorderFactory.createEtchedBorder());
        this.add(currCardLab);

        final JLabel discardPileTopLab = new JLabel("Discard Pile Empty", SwingConstants.CENTER);
        discardPileTopLab.setBorder(BorderFactory.createEtchedBorder());
        this.add(discardPileTopLab);

        JButton createButt = new JButton("Create a Deck");
        createButt.setToolTipText("Creates a deck of 52 cards");
        this.add(createButt);

        JButton toggleDiscardButt = new JButton("Discard pile: Enable");
        toggleDiscardButt.setToolTipText("Enables or disables the discard pile");
        this.add(toggleDiscardButt);

        class CreateDeckActionListener implements ActionListener {

            public void actionPerformed(ActionEvent ae) {
                deck.createDeck();
                deckLab.setText("Deck Available");
                invalidate();
                validate();
                repaint();
                currCardLab.setText("No Current Card Available");
                discardPileTopLab.setText("Discard Pile Empty");
            }
        }
        createButt.addActionListener(new CreateDeckActionListener());

        class ToggleDiscardActionListener implements ActionListener {
            public void actionPerformed(ActionEvent ae) {
                if (toggleDiscardButt.getText().equals("Discard pile: Enable")) {
                    deck.discard = true;
                    toggleDiscardButt.setText("Discard pile: Disable");
                } else {
                    deck.discard = false;
                    toggleDiscardButt.setText("Discard pile: Enable");
                }
            }
        }
        toggleDiscardButt.addActionListener(new ToggleDiscardActionListener());

        JButton pickTopButt = new JButton("Pick Card from the Top");
        pickTopButt.setToolTipText("Picks a card from the top of the deck");
        this.add(pickTopButt);

        JButton pickRandomButt = new JButton("Pick a random Card");
        pickRandomButt.setToolTipText("Picks a Random card");
        this.add(pickRandomButt);

        JButton pickBottomButt = new JButton("Pick a Card from the Bottom");
        pickBottomButt.setToolTipText("Picks a card from the bottom of the deck");
        this.add(pickBottomButt);

        JButton pickPositionButt = new JButton("Pick a Card from a Position");
        pickPositionButt.setToolTipText("Picks a Card from a specified position");
        this.add(pickPositionButt);

        class PickCardActionListener implements ActionListener {
            public void actionPerformed(ActionEvent ae) {

                if (deck.checkDeck()){
                    if (ae.getSource() == pickTopButt) {
                        deck.pickCardFromTop(); 
                    } else if (ae.getSource() == pickRandomButt) {
                        deck.pickCardFromRandom();
                    } else if (ae.getSource() == pickBottomButt) {
                        deck.pickCardFromBottom();
                    } 
                    else if (ae.getSource() == pickPositionButt) {
                        boolean done = false;
                        String input;
                        while(!done) {
                            try {
                                input = JOptionPane.showInputDialog(null, "Enter a position (0 - " + (deck.deck.size() - 1) + "): ");
                                if (input == null  || input.length() == 0) {
                                    done = true;
                                }
                                else if (Integer.parseInt(input) < 0 || Integer.parseInt(input) > deck.deck.size() - 1) {
                                    JOptionPane.showMessageDialog(null, "Your number is invalid.", "Value Error", JOptionPane.ERROR_MESSAGE);
                                    continue;
                                } else {
                                    deck.pickCardFromPosition(Integer.parseInt(input));
                                }
                                done = true;
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Please enter a number value.", "Input Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    // if there is no current card, this cannot work. find out what current card is initialized as, and make if-else statement
                    if (deck.currentCard != null) { currCardLab.setText("Current card: " + deck.currentCard.getValue() + " of " + deck.currentCard.getSuit()); }
                    if(deck.discard) {
                        discardPileTopLab.setText(deck.discardPile.peek().getValue() + " of " + deck.discardPile.peek().getSuit());
                    }
                }
            }
        }
        pickTopButt.addActionListener(new 
            PickCardActionListener());
        pickRandomButt.addActionListener(new 
            PickCardActionListener());
        pickBottomButt.addActionListener(new 
            PickCardActionListener());
        pickPositionButt.addActionListener(new 
            PickCardActionListener());

        JButton shuffleButt = new JButton("Shuffle Deck");
        createButt.setToolTipText("Shuffles the deck");
        this.add(shuffleButt);

        class ShuffleActionListener implements ActionListener {

            public void actionPerformed(ActionEvent ae) {
                if (deck.checkDeck()){
                    deck.shuffleDeck(); 
                }
            }
        }
        shuffleButt.addActionListener(new ShuffleActionListener());

        JButton clearButt = new JButton("Clear Deck");
        createButt.setToolTipText("Discard current deck");
        this.add(clearButt);

        class ClearActionListener implements ActionListener {

            public void actionPerformed(ActionEvent ae) {
                if (deck.checkDeck()) {
                    deck.clearDeck();
                    deckLab.setText("No Deck Available");
                    currCardLab.setText("No Current Card Available");
                }
            }
        }
        clearButt.addActionListener(new ClearActionListener());

    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Cards Master");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLayout(new GridLayout());

        f.add(new Interface());

        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
