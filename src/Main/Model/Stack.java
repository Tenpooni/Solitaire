package Model;

import java.util.ArrayList;
import java.util.List;

public interface Stack {
    String name = "";
    ArrayList<Card> faceDown = new ArrayList<>();

    public void addCard(Card c);


    public String getName();

    public List<Card> getFaceDownCards();
}
