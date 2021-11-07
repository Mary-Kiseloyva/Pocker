package ru.vsu.cs.KiselevaMaria.combination;


import card.Card;
import card.Value;
import common.Player;

import java.util.ArrayList;
import java.util.List;

public class FullHouseCombination extends AbstractCombination {

    private CombinationDTO maxPair = null;
    private CombinationDTO set = null;

    public FullHouseCombination() {//сет и пара
        this.name = "full house";
        this.priority = 7;
    }

    @Override
    protected List<Card> findCards(List<Card> allCards, Player player) {
        List<CombinationDTO> combinations = player.getCombinations();
        findSet(combinations);
        findMaxPair(combinations);
        return buildFullHouse();
    }

    private void findMaxPair(CombinationDTO combination) {
        if(set != null && set.getCards().get(0).getValue() == combination.getCards().get(0).getValue()) {
            maxPair = null;//чтобы пара не входила в сет(думаю, что == нужно заменить на != )
        }else {
            if (maxPair == null) {
                maxPair = combination;
            } else {
                Value value = combination.getCards().get(0).getValue();
                if (value.getNumber() >= maxPair.getCards().get(0).getValue().getNumber()) {
                    maxPair = combination;
                }
            }
        }
    }

    private void findMaxPairBetweenTwoPairs(CombinationDTO combination) {
        List<Card> twoPairs = combination.getCards();
        sort(twoPairs);
        CombinationDTO pair = new CombinationDTO();
        if (twoPairs.get(0).getValue().getNumber() > twoPairs.get(2).getValue().getNumber()) {
            pair.getCards().addAll(twoPairs.subList(0, 2));//добавляем от индекса до индекса
        } else {
            pair.getCards().addAll(twoPairs.subList(2, twoPairs.size()));
        }
        maxPair = pair;
    }

    private void findSet(List<CombinationDTO> combinations) {
        for (CombinationDTO combination : combinations) {//OK
            if (combination.getPriority() == 4) {
                set = combination;
            }
        }
    }
     private void findMaxPair(List<CombinationDTO> combinations) {
         for (CombinationDTO combination : combinations) {
             if (combination.getPriority() == 2) {
                 findMaxPair(combination);
             } else if (combination.getPriority() == 3) {
                 findMaxPairBetweenTwoPairs(combination);
             }
         }
     }

     private List<Card> buildFullHouse() {
         if (maxPair != null && set != null ) {
             List<Card> fullHouse =  new ArrayList<>();
             fullHouse.addAll(set.getCards());
             fullHouse.addAll(maxPair.getCards());
             maxPair = null;
             set = null;
             return fullHouse;
         }
         return null;
     }

    @Override
    protected boolean isPlayerNeeded() {
        return true;
    }
}
