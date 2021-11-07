package common;
import card.Card;
import ru.vsu.cs.KiselevaMaria.combination.CombinationDTO;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private final List<Card> cards = new ArrayList<>(2);
    private int money = 2000;
    private final List<CombinationDTO> combinations = new ArrayList<>();
    private boolean ai = true;

    private boolean pass = false;


    public Player(String name) {
        this.name = name;
    }

    public int decreaseMoney(int delta) {
        money = money - delta;
        return delta;
    }

    public int increaseMoney(int delta) {
        money = money + delta;
        return delta;
    }

    public void addCombination(CombinationDTO combination) {
        combinations.add(combination);
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<CombinationDTO> getCombinations() {
        return combinations;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public boolean isAi() {
        return ai;
    }

    public void setAi(boolean ai) {
        this.ai = ai;
    }

    public void setName(String name) {
        this.name = name;
    }
}
