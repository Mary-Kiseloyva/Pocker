package ru.vsu.cs.KiselevaMaria.combination;



import card.Card;

import java.util.ArrayList;
import java.util.List;

public class CombinationDTO {
    private String name;
    private int priority;
    private List<Card> cards = new ArrayList<>();
    private int cardsSumValue = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public int getCardsSumValue() {
        return cardsSumValue;
    }

    public void setCardsSumValue(int cardsSumValue) {
        this.cardsSumValue = cardsSumValue;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' + '\n' +
                "priority=" + priority + '\n' +
                "cards=" + cards + '\n';
    }
}
