package ru.vsu.cs.KiselevaMaria.combination;


import card.Card;
import card.Value;

import java.util.ArrayList;
import java.util.List;

public class PairCombination extends AbstractCombination {

    public PairCombination() {
        this.name = "pair";
        this.priority = 2;
    }

    @Override
    protected List<Card> findCards(List<Card> allCards) {
        for (int i = allCards.size() - 1; i >= 0; i--) {
            Value value = allCards.get(i).getValue();
            for (int j = i - 1; j > 0; j--) {
                Value value1 = allCards.get(j).getValue();
                if (value.equals(value1)) {
                    List<Card> combinationCards = new ArrayList<>();
                    combinationCards.add(allCards.get(i));
                    combinationCards.add(allCards.get(j));
                    return combinationCards;
                }
            }
        }
        return null;
    }

}
