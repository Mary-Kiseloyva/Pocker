package ru.vsu.cs.KiselevaMaria.combination;



import card.Card;
import card.Value;

import java.util.ArrayList;
import java.util.List;

public class SetCombination extends AbstractCombination {

    public SetCombination() {
        this.name = "set";
        this.priority = 4;
    }

    @Override
    protected List<Card> findCards(List<Card> allCards) {
        for(int i=0; i<allCards.size(); i++) {
            Value value = allCards.get(i).getValue();
            for(int j=i+1; j<allCards.size(); j++) {
                if(value.equals(allCards.get(j).getValue())) {
                    for(int k=j+1; k<allCards.size(); k++) {
                        if (allCards.get(k).getValue().equals(value)) {
                            List<Card> set = new ArrayList<>();
                            set.add(allCards.get(j));
                            set.add(allCards.get(i));
                            set.add(allCards.get(k));
                            return set;
                        }
                    }
                }
            }
        }
        return null;
    }
}
