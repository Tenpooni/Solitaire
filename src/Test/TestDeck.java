import Model.Card;
import Model.Deck;
import Model.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestDeck {
    private Deck deck;

    private Card card1SpadeUp;
    private Card card10HeartDown;
    private Card card6DiamondUp;
    private Card card13ClubDown;
    private Card card9HeartUp;
    private Card card12ClubUp;

    private ArrayList<Card> cardList;

    @BeforeEach
    public void runBefore() {
        deck = new Deck("TestDeck");
        card1SpadeUp = new Card(1, Suit.SPADE, true);
        card10HeartDown = new Card(10, Suit.HEART, false);
        card6DiamondUp = new Card(6, Suit.DIAMOND, true);
        card13ClubDown = new Card(13, Suit.CLUB, false);

        card9HeartUp = new Card(9, Suit.HEART, true);
        card12ClubUp = new Card(12, Suit.CLUB, true);

        cardList = new ArrayList<>();
        cardList.add(card1SpadeUp);
        cardList.add(card10HeartDown);
        cardList.add(card6DiamondUp);
        cardList.add(card13ClubDown);

    }

    @Test
    public void testAddCardsEmptyDeck() {
        //Card list is in order of faceUp: spade, diamond. Then faceDown: heart, club
        deck.addCards(cardList);

        //expect to add faceUp cards at front of list, faceDown at back, keeps order of both
        assertEquals(card1SpadeUp, deck.viewAllCards().get(0));
        assertEquals(card6DiamondUp, deck.viewAllCards().get(1));
        assertEquals(card10HeartDown, deck.viewAllCards().get(2));
        assertEquals(card13ClubDown, deck.viewAllCards().get(3));
    }

    @Test
    public void testAddCardsExistingDeck() {
        //Card list is in order of faceUp: spade, diamond. Then faceDown: heart, club
        deck.addCards(cardList);

        ArrayList<Card> selectedCards = new ArrayList<>();
        selectedCards.add(card9HeartUp);
        selectedCards.add(card12ClubUp);

        //New addition of selectedCards go at head of list
        deck.addCards(selectedCards);

        assertEquals(card9HeartUp, deck.viewAllCards().get(0));
        assertEquals(card12ClubUp, deck.viewAllCards().get(1));

        assertEquals(card1SpadeUp, deck.viewAllCards().get(2));
        assertEquals(card6DiamondUp, deck.viewAllCards().get(3));
        assertEquals(card10HeartDown, deck.viewAllCards().get(4));
        assertEquals(card13ClubDown, deck.viewAllCards().get(5));
    }

//    @Test
//    public void testGetFaceUpCards() {
//        deck.addCards(cardList);
//
//        ArrayList<Card> testCardList = deck.getFaceUpCards();
//
//        assertEquals(card6DiamondUp, testCardList.get(0));
//        assertEquals(card1SpadeUp, testCardList.get(1));
//        assertEquals(2, testCardList.size());
//    }

    @Test
    public void testGetSelectedStack() {
        cardList.add(card9HeartUp);
        cardList.add(card12ClubUp);

        deck.addCards(cardList);
        ArrayList<Card> testSelectedCards = deck.getSelectedStack(card9HeartUp);

        assertEquals(3, testSelectedCards.size());
        assertEquals(card1SpadeUp, testSelectedCards.get(0));
        assertEquals(card6DiamondUp, testSelectedCards.get(1));
        assertEquals(card9HeartUp, testSelectedCards.get(2));

        assertEquals(3, deck.viewAllCards().size());
    }

    @Test
    public void testGetNumFaceUp() {
        cardList.add(card9HeartUp);
        cardList.add(card12ClubUp);

        deck.addCards(cardList);

        int i = deck.getNumFaceUp();
        assertEquals(4, i);
    }

    @Test
    public void testFlipOneFaceUp() {
        cardList.add(card9HeartUp);
        cardList.add(card12ClubUp);

        deck.addCards(cardList);
        deck.flipUp(1);

        card10HeartDown.setFaceUp();
        assertEquals(5, deck.getNumFaceUp());
        assertEquals(card10HeartDown, deck.viewAllCards().get(4));
        assertEquals(card13ClubDown, deck.viewAllCards().get(5));
    }

    @Test
    public void testFlipFaceUpMax() {
        cardList.add(card9HeartUp);
        cardList.add(card12ClubUp);

        deck.addCards(cardList);
        deck.flipUp(2);

        card10HeartDown.setFaceUp();
        card13ClubDown.setFaceUp();
        assertEquals(6, deck.getNumFaceUp());
        assertEquals(card10HeartDown, deck.viewAllCards().get(4));
        assertEquals(card13ClubDown, deck.viewAllCards().get(5));
    }

    @Test
    public void testFlipFaceUpExceed() {
        cardList.add(card9HeartUp);
        cardList.add(card12ClubUp);

        deck.addCards(cardList);
        deck.flipUp(3);

        card10HeartDown.setFaceUp();
        card13ClubDown.setFaceUp();
        assertEquals(6, deck.getNumFaceUp());
        assertEquals(card10HeartDown, deck.viewAllCards().get(4));
        assertEquals(card13ClubDown, deck.viewAllCards().get(5));
    }

    @Test
    public void testFlipFaceDown() {
        deck.addCards(cardList);
        //affirm initial starting order of deck is from FaceUp to Facedowns
        assertEquals(card1SpadeUp, deck.viewAllCards().get(0));
        assertEquals(card6DiamondUp, deck.viewAllCards().get(1));
        assertEquals(card10HeartDown, deck.viewAllCards().get(2));
        assertEquals(card13ClubDown, deck.viewAllCards().get(3));
        assertEquals(2, deck.getNumFaceUp());

        deck.flipDown();

        //affirm after flipDown(), previous FaceUps are now FaceDowns
        assertEquals(card10HeartDown, deck.viewAllCards().get(0));
        assertEquals(card13ClubDown, deck.viewAllCards().get(1));
        assertEquals(card1SpadeUp, deck.viewAllCards().get(2));
        assertEquals(card6DiamondUp, deck.viewAllCards().get(3));

        assertFalse(card1SpadeUp.getFaceUp());
        assertFalse(card6DiamondUp.getFaceUp());
        assertEquals(0, deck.getNumFaceUp());
        assertEquals(4, deck.viewAllCards().size());
    }


}
