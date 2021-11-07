package ru.vsu.cs.KiselevaMaria.combination;


import card.Card;
import card.Color;
import common.Player;

import java.util.*;

public abstract class AbstractCombination {
    protected String name;
    protected int priority;
    Comparator<Card> comparator = new Comparator<Card>() {
        @Override
        public int compare(Card o1, Card o2) {
            return o1.getValue().compareTo(o2.getValue());
        }
    };


        public void check(Player player, List<Card> tableCards) {
            List<Card> combinationCards;
            List<Card> allPlayerCards = uniteCards(player, tableCards);//все карты игрока с рук и со стола
            if (isPlayerNeeded()) {//нужны ли комбинации игрока
                combinationCards = findCards(allPlayerCards, player);
            } else {
                combinationCards = findCards(allPlayerCards);
            }
            addCombination(player, combinationCards);
        }

        public int getPriority() {
            return priority;
        }

        protected boolean isPlayerNeeded() {
            return false;
        }

        protected List<Card> findCards(List<Card> allCards) {
            return null;
        }


        protected List<Card> findCards(List<Card> allCards, Player player) {
            return null;
        }


        protected List<Card> uniteCards(Player player, List<Card> cards) {//объединение карт
            List<Card> allPlayerCards = new ArrayList<>();
            allPlayerCards.addAll(player.getCards());
            allPlayerCards.addAll(cards);
            sort(allPlayerCards);
            return allPlayerCards;
        }

        //сортировка пузырьком
   /* protected void sort(List<Card> allCards) {
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i < allCards.size() - 1; i++) {
                Card card1 = allCards.get(i);
                Card card2 = allCards.get(i + 1);
                if (card1.getValue().getNumber() > card2.getValue().getNumber()) {
                    allCards.set(i, card2);
                    allCards.set(i + 1, card1);
                    changed = true;
                }
            }
        }
    }*/

        protected void sort(List<Card> allCards) {
            Collections.sort(allCards, comparator);
        }

        protected Map<Color, List<Card>> groupByColor(List<Card> allCards) {
            Map<Color, List<Card>> colorGroups = new HashMap<>();//бумбумбум
            for (Card card : allCards) {
                Color color = card.getColor();
                if (colorGroups.get(color) == null) {
                    List<Card> group = new ArrayList<>();
                    group.add(card);
                    colorGroups.put(color, group);
                } else {
                    colorGroups.get(color).add(card);
                }
            }
            return colorGroups;
        }

        protected void addCombination(Player player, List<Card> list) {
            if (list != null) {
                CombinationDTO dto = new CombinationDTO();
                dto.getCards().addAll(list);
                dto.setName(name);
                dto.setPriority(priority);
                dto.setCardsSumValue(calculateSum(list));

                player.addCombination(dto);
            }
        }

        private int calculateSum(List<Card> combinationCards) {
            int sum = 0;
            for (Card card : combinationCards) {//складываем достоинства в комбинации
                sum += card.getValue().getNumber();
            }
            return sum;
        }
    }
