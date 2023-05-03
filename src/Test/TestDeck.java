import Model.Card;
import Model.Deck;
import Model.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDeck {
    private Deck deck;

    private Card card1SpadeUp;
    private Card card13HeartDown;
    private Card card6DiamondUp;
    private Card card10ClubDown;

    private ArrayList<Card> cardList;

    @BeforeEach
    public void runBefore() {
        deck = new Deck("TestDeck");
        card1SpadeUp = new Card(1, Suit.SPADE, true);
        card13HeartDown = new Card(13, Suit.HEART, false);
        card6DiamondUp = new Card(6, Suit.DIAMOND, true);
        card10ClubDown = new Card(10, Suit.CLUB, false);
        cardList = new ArrayList<>();
        cardList.add(card1SpadeUp);
        cardList.add(card13HeartDown);
        cardList.add(card6DiamondUp);
        cardList.add(card10ClubDown);

    }

    @Test
    public void testAddCards() {
        //Card list is in order of faceUp: spade, diamond. Then faceDown: heart, club
        deck.addCards(cardList);

        //expect to add faceUp cards in reverse order, faceDown in order
        assertEquals(deck.getCards().get(0), card6DiamondUp);
        assertEquals(deck.getCards().get(1), card1SpadeUp);
        assertEquals(deck.getCards().get(2), card13HeartDown);
        assertEquals(deck.getCards().get(3), card10ClubDown);
    }

    @Test
    public void testGetFaceUpCards() {
        deck.addCards(cardList);

        ArrayList<Card> faceUpCards = new ArrayList<>();
        faceUpCards.add(card1SpadeUp);
        faceUpCards.add(card6DiamondUp);

        ArrayList<Card> testCardList = deck.getFaceUpCards();

        assertEquals(faceUpCards.get(0), testCardList.get(0));
        assertEquals(faceUpCards.get(1), testCardList.get(1));
        assertEquals(faceUpCards.size(), testCardList.size());
    }

    @Test
    public void testGetSelectedStack() {
        deck.addCards(cardList);

    }
}
