import Model.Card;
import Model.Stack;
import Model.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestStack {
    private Stack stack;

    private Card card9SpadeUp;
    private Card card10HeartDown;
    private Card card11DiamondUp;
    private Card card12ClubDown;
    private Card card1HeartUp;
    private Card card2ClubUp;

    private ArrayList<Card> cardList;

    @BeforeEach
    public void runBefore() {
        stack = new Stack("TestStack");
        card9SpadeUp = new Card(9, Suit.SPADE, true);
        card10HeartDown = new Card(10, Suit.HEART, false);
        card11DiamondUp = new Card(11, Suit.DIAMOND, true);
        card12ClubDown = new Card(12, Suit.CLUB, false);

        card1HeartUp = new Card(1, Suit.HEART, true);
        card2ClubUp = new Card(2, Suit.CLUB, true);

        cardList = new ArrayList<>();
        cardList.add(card9SpadeUp);
        cardList.add(card10HeartDown);
        cardList.add(card11DiamondUp);
        cardList.add(card12ClubDown);
    }

//
//
//    @Test
//    public void testAddToFaceUpStack() {
//        //Card list is in order of faceUp: spade, diamond. Then faceDown: heart, club
//        stack.addToFaceUpCards(cardList);
//
//        ArrayList<Card> selectedCards = new ArrayList<>();
//        selectedCards.add(card1HeartUp);
//        selectedCards.add(card2ClubUp);
//
//        //New addition of selectedCards go at head of list
//        stack.addToFaceUpCards(selectedCards);
//
//        assertEquals(card1HeartUp, stack.viewAllCards().get(0));
//        assertEquals(card2ClubUp, stack.viewAllCards().get(1));
//
//        assertEquals(card9SpadeUp, stack.viewAllCards().get(2));
//        assertEquals(card11DiamondUp, stack.viewAllCards().get(3));
//
//        assertEquals(card10HeartDown, stack.viewAllCards().get(4));
//        assertEquals(card12ClubDown, stack.viewAllCards().get(5));
//    }
//
//    @Test
//    public void testGetSelectedStack() {
//        cardList.add(card1HeartUp);
//        cardList.add(card2ClubUp);
//
//        stack.addToFaceUpCards(cardList);
//        ArrayList<Card> testSelectedCards = stack.getSelected(card1HeartUp);
//
//        //assertEquals(3, testSelectedCards.size());
//        assertEquals(card9SpadeUp, testSelectedCards.get(0));
//        assertEquals(card11DiamondUp, testSelectedCards.get(1));
//        assertEquals(card1HeartUp, testSelectedCards.get(2));
//    }
//
//    //TODO: verify this test later
//    @Test
//    public void testRemoveCards() {
//        cardList.add(card1HeartUp);
//        cardList.add(card2ClubUp);
//
//        stack.addToFaceUpCards(cardList);
//        ArrayList<Card> selectFaceUpCards = stack.getSelected(card1HeartUp);
//        ArrayList<Card> selectFaceDownCards = new ArrayList<>();
//        selectFaceDownCards.add(card10HeartDown);
//
//        assertEquals(3, selectFaceUpCards.size());
//        //assertEquals(1, selectFaceDownCards.size());
//
//        stack.removeFaceUpCards(selectFaceUpCards);
//        //stack.removeFaceUpCards(selectFaceDownCards);
//
//        //assertEquals(2, stack.viewAllCards().size());
//        assertEquals(card2ClubUp, stack.viewAllCards().get(0));
//        assertEquals(card10HeartDown, stack.viewAllCards().get(1));
//    }
//
//    @Test
//    public void testFlipUp() {
//        cardList.add(card1HeartUp);
//        cardList.add(card2ClubUp);
//
//        stack.addToFaceUpCards(cardList);
//
//        //First affirm order
//        assertEquals(card9SpadeUp, stack.viewAllCards().get(0));
//        assertEquals(card11DiamondUp, stack.viewAllCards().get(1));
//        assertEquals(card1HeartUp, stack.viewAllCards().get(2));
//        assertEquals(card2ClubUp, stack.viewAllCards().get(3));
//        assertEquals(card10HeartDown, stack.viewAllCards().get(4));
//        assertEquals(card12ClubDown, stack.viewAllCards().get(5));
//
//        //one card at start of faceDown list is set faceUp, card10HeartDown is set to faceUp
//        stack.flipUp(1);
//
//        assertTrue(card10HeartDown.getFaceUp());
//        assertEquals(card10HeartDown, stack.viewAllCards().get(4));
//        assertFalse(card12ClubDown.getFaceUp());
//        assertEquals(card12ClubDown, stack.viewAllCards().get(5));
//
//        //Flipup requests 2 cards, only 1 card faceDown is left, flip just the one.
//        stack.flipUp(2);
//        assertEquals(card10HeartDown, stack.viewAllCards().get(4));
//        assertTrue(card12ClubDown.getFaceUp());
//        assertEquals(card12ClubDown, stack.viewAllCards().get(5));
//    }

//    @Test
//    public void testFlipDown() {
//        cardList.add(card1HeartUp);
//        cardList.add(card2ClubUp);
//
//        stack.addCards(cardList);
//        //order is affirmed in testFlipUp
//
//        stack.flipDown();
//
//        //cards previously faceUp are cycled to the END of the faceDown list, order is maintained
//        assertEquals(card9SpadeUp, stack.viewAllCards().get(2));
//        assertEquals(card11DiamondUp, stack.viewAllCards().get(3));
//        assertFalse(card11DiamondUp.getFaceUp());
//        assertEquals(card1HeartUp, stack.viewAllCards().get(4));
//        assertEquals(card2ClubUp, stack.viewAllCards().get(5));
//        assertFalse(card2ClubUp.getFaceUp());
//
//        assertEquals(card10HeartDown, stack.viewAllCards().get(0));
//        assertEquals(card12ClubDown, stack.viewAllCards().get(1));
//    }



}
