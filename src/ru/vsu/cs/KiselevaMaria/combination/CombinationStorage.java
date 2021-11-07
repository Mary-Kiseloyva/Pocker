package ru.vsu.cs.KiselevaMaria.combination;

import java.util.ArrayList;
import java.util.List;

public class CombinationStorage {
    public static List<AbstractCombination> combinations = new ArrayList<>();

    static {
        combinations.add(new MaxCardCombination());
        combinations.add(new PairCombination());
        combinations.add(new TwoPairsCombination());
        combinations.add(new SetCombination());
        combinations.add(new StreetCombination());
        combinations.add(new FlashCombination());
        combinations.add(new FullHouseCombination());
        combinations.add(new KareCombination());
        combinations.add(new StreetFlashCombination());
        combinations.add(new FleshRoyalCombination());
    }
}
