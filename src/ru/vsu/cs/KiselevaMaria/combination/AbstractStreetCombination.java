package ru.vsu.cs.KiselevaMaria.combination;



import card.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStreetCombination extends AbstractCombination {

    private static final int STREET_SIZE = 5;
    private boolean done = false;

    protected List<Card> findStreet(List<Card> allCards) {
        List<Card> street = new ArrayList<>();
        for (int i = 0; i < allCards.size() - 1; i++) {
            Card card1 = allCards.get(i);
            Card card2 = allCards.get(i + 1);
            if (card2.getValue().getNumber() - card1.getValue().getNumber() == 1) {
                addCardsToStreet(card1, card2, street);
            } else if (allCards.size() - street.size() < STREET_SIZE) {
                break;//если оставшееся количество карт сликом маленькое для стрита, то выходим
            } else {
                street.clear();
            }
        }
        if (done) {
            done = false;
            return street;
        }
        return null;
    }

    private void addCardsToStreet(Card card1, Card card2, List<Card> street) {
        if (street.size() == STREET_SIZE) {
            street.remove(0);
            street.add(card2);
        } else {
            street.add(card1);
            if (street.size() == STREET_SIZE - 1) {
                street.add(card2);
                done = true;
            }
        }
    }
}
