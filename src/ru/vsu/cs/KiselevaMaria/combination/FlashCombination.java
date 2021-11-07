package ru.vsu.cs.KiselevaMaria.combination;


import card.Card;
import card.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FlashCombination extends AbstractCombination {
    private static final int FLASH_SIZE = 5;

    public FlashCombination() {
        this.name = "flash";
        this.priority = 6;
    }

    @Override
    protected List<Card> findCards(List<Card> allCards) {
        Map<Color, List<Card>> colorGroups = groupByColor(allCards);
        for(Map.Entry<Color, List<Card>> entry : colorGroups.entrySet())  {
            List<Card> group =  entry.getValue();
            if(group.size() >= FLASH_SIZE ) {
                sort(group);
                return new ArrayList<>(group.subList(group.size() - FLASH_SIZE, group.size()));
            }
        }
        return null;
    }
}
