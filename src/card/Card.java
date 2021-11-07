package card;


import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

public class Card {
    private Color color;
    private Value value;
    private Image image;

    public Card(Color color, Value value) {
        this.color = color;
        this.value = value;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Color getColor() {
        return color;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + " " + color;
    }
}
