package ru.vsu.cs.KiselevaMaria.combination;


import card.Card;
import card.Value;

import java.util.ArrayList;
import java.util.List;

public class TwoPairsCombination extends AbstractCombination {
    private boolean foundPair = false;

    public TwoPairsCombination() {
        this.name = "two pairs";
        this.priority = 3;
    }

    @Override
    protected List<Card> findCards(List<Card> allCards) {
        List<Card> twoPairs = new ArrayList<>();
        findPair(allCards, twoPairs);
        if (foundPair) {
            allCards.removeAll(twoPairs);
            findPair(allCards, twoPairs);
            if(foundPair) {
                foundPair = false;
                return twoPairs;
            }
        }
        return null;
    }

    private void findPair(List<Card> allCards, List<Card> twoPairs) {
        foundPair = false;
        for (int i = 0; i < allCards.size(); i++) {
            if (foundPair) {
                break;
            }
            Value value = allCards.get(i).getValue();
            for (int j = i + 1; j < allCards.size(); j++) {
                Value value1 = allCards.get(j).getValue();
                if (value.equals(value1)) {
                    twoPairs.add(allCards.get(i));
                    twoPairs.add(allCards.get(j));
                    foundPair = true;
                    break;
                }
            }
        }
    }
}
