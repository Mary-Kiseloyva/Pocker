package ru.vsu.cs.KiselevaMaria.combination;


import card.Card;

import java.util.ArrayList;
import java.util.List;

public class MaxCardCombination extends AbstractCombination {

    public MaxCardCombination() {
       this.name = "max card";
       this.priority = 1;
    }

    @Override
    protected List<Card> findCards(List<Card> allCards) {
        Card max = findMax(allCards);
        List<Card> list = new ArrayList<>();
        list.add(max);
        return list;
    }

    private Card findMax(List<Card> allPlayerCards) {
        Card max = allPlayerCards.get(0);
        for (int i = 1; i < allPlayerCards.size(); i++) {
            if (allPlayerCards.get(i).getValue().getNumber() > max.getValue().getNumber()) {
                max = allPlayerCards.get(i);
            }
        }
        return max;
    }
}
