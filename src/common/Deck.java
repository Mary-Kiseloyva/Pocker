package common;


import card.Card;
import card.Color;
import card.Value;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {
    private Stack<Card> deck = new Stack<>();

    public Deck() throws IOException {
        for (Color color : Color.values()) {
            for (Value value : Value.values()) {
                String s1 = color.name();
                String s2 = value.name();
                String path = "src/ru/vsu/cs/KiselevaMaria/image/" + s1 + " " + s2 + ".png";
                File file = new File(path);
                Image image = new Image(file.toURI().toString());
                Card card = new Card(color, value);
                card.setImage(image);
                deck.add(card);
            }

        }
        Collections.shuffle(deck);

    }

     public void printDeck() {
         for(Card card: deck) {
             System.out.println(card.getValue()+" "+card.getColor());
         }
         System.out.println(deck.size());
     }

     public void shuffle() {
         Collections.shuffle(deck);
     }

     public Card peek() {
         return deck.peek();
     }

    public Card pop() {
        return deck.pop();
    }

    public void put(List<Card> cards) {
        for (Card card : cards) {
            deck.push(card);
        }
    }

}
