package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stack {
    private final String name;
    private ArrayList<Card> faceDown;
    private ArrayList<Card> faceUp;

    public Stack(String str) {
        this.name = str;
        this.faceDown = new ArrayList<>();
        this.faceUp = new ArrayList<>();
    }

    public void addFaceDown(Card c) {
        this.faceDown.add(c);
    }

    public void addFaceUp(Card c) {
        this.faceUp.add(c);
    }

    public String getName() {
        return this.name;
    }

    public List<Card> getFaceDownCards() {
        return Collections.unmodifiableList(this.faceDown);
    }

    public List<Card> getFaceUpCards() {
        return Collections.unmodifiableList(this.faceUp);
    }

}
