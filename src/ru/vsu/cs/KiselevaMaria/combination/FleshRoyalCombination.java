package ru.vsu.cs.KiselevaMaria.combination;



import card.Card;
import card.Value;
import common.Player;

import java.util.ArrayList;
import java.util.List;

public class FleshRoyalCombination extends AbstractCombination {

    public FleshRoyalCombination() {
        this.name = "flesh royal";
        this.priority = 10;
    }

    @Override
    protected List<Card> findCards(List<Card> allCards, Player player) {
        CombinationDTO appropriateStreetFlash = null;//подходящий стрит флэш
        for (CombinationDTO combination : player.getCombinations()) {
            if (combination.getPriority() == 9 &&
                    combination.getCards().get(0).getValue() == Value.TEN) {
                appropriateStreetFlash = combination;
                break;
            }
        }
        if (appropriateStreetFlash != null) {
            return new ArrayList<>(appropriateStreetFlash.getCards());
        }
        return null;
    }

    @Override
    protected boolean isPlayerNeeded() {
        return true;
    }
}

