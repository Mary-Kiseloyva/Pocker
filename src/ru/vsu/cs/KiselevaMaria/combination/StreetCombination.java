package ru.vsu.cs.KiselevaMaria.combination;


import card.Card;

import java.util.List;

public class StreetCombination extends AbstractStreetCombination {

    public StreetCombination() {
        this.name = "street";
        this.priority = 5;
    }

    @Override
    protected List<Card> findCards(List<Card> allCards) {
        sort(allCards);
        return findStreet(allCards);
    }
}
