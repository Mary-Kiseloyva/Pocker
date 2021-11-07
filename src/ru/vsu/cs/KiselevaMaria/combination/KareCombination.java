package ru.vsu.cs.KiselevaMaria.combination;

import card.Card;
import card.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KareCombination extends AbstractCombination{
    private static final int KARE_SIZE = 4;

    public KareCombination() {
        this.name = "kare";
        this.priority = 8;
    }

    @Override
    protected List<Card> findCards(List<Card> allCards) {
        Map<Value, List<Card>> map = groupCardsByValue(allCards);
        return findKare(map);
    }

    private Map<Value, List<Card>> groupCardsByValue(List<Card> allCards) {
        Map<Value, List<Card>> map = new HashMap<>();
        for (Card card : allCards) {
            if(map.containsKey(card.getValue())) {
                List<Card> cardList = map.get(card.getValue());
                cardList.add(card);
            }else {
                List<Card> list = new ArrayList<>();
                list.add(card);
                map.put(card.getValue(), list);
            }
        }
        return map;
    }

    private List<Card> findKare(Map<Value, List<Card>> map) {
        for(Map.Entry<Value, List<Card>> entry : map.entrySet()) {
            if(entry.getValue().size() == KARE_SIZE) {
                return new ArrayList<>(entry.getValue());
            }
        }
        return null;
    }
}
