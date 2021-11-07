package ru.vsu.cs.KiselevaMaria.combination;


import card.Card;
import card.Color;

import java.util.List;
import java.util.Map;

public class StreetFlashCombination extends AbstractStreetCombination {

    private static final int STREET_FLASH_SIZE = 5;

    public StreetFlashCombination() {
        this.name = "street flash";
        this.priority = 9;
    }

    @Override
    protected List<Card> findCards(List<Card> allCards) {
        Map<Color, List<Card>> colorGroups = groupByColor(allCards);
        for(Map.Entry<Color, List<Card>> entry : colorGroups.entrySet()) {
            if (entry.getValue().size() >= STREET_FLASH_SIZE) {
                sort(entry.getValue());
                return findStreet(entry.getValue());
            }
        }
        return null;
    }
}
