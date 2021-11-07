package ru.vsu.cs.KiselevaMaria;

import card.Card;
import common.Deck;
import common.Player;
import ru.vsu.cs.KiselevaMaria.combination.AbstractCombination;
import ru.vsu.cs.KiselevaMaria.combination.CombinationDTO;
import ru.vsu.cs.KiselevaMaria.combination.CombinationStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Game {


    private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    private static int bank = 0;
    private static Deck deck = null;


    public static void main(String[] args) throws IOException {
        List<Player> players = new ArrayList<>();
        List<Card> cards = new ArrayList<>(5);
        createPlayers(players);

        while (true) {
            giveCardsToPlayers(players);
            simulateGame(players, cards);
            Candidate winner = findWinner(players, cards);

            finishGame(winner.getPlayer(), players);
        }

    }

    private static void addBet(List<Player> players) throws IOException {
        for (Player player : players) {
            if (!player.isAi()) {// если играю я, а не AI
                if (player.isPass()) {
                    continue;
                }
                System.out.println("введите ставку или пас ");
                makeBet(player);
            } else {
                bank = bank + player.decreaseMoney(100);
            }
        }
        System.out.println("текущий банк " + bank);
    }

    private static void putCardOnTable(List<Card> cards, int n) {
        cards.add(deck.pop());
        System.out.println(cards.get(n));
    }

    public static void giveCardsToPlayers(List<Player> players) throws IOException {
        for (Player player : players) {
            giveStartCards(player);
            if (!player.isAi()) {
                makeStartBet(player);
            } else {
                bank = bank + player.decreaseMoney(50);
            }
        }
    }

    public static void createPlayers(List<Player> players) throws IOException {
        String name = BR.readLine();//себе отключить ai(т.е первому игроку)
        for (int i = 0; i < 3; i++) {
            Player player = new Player("Player" + (i + 1));
            if (i == 0) {
                player.setAi(false);
                player.setName(name);
            }
            players.add(player);
        }
    }

    private static void makeStartBet(Player player) throws IOException {
        System.out.println(player.getCards());
        System.out.println("начальная ставка : ");
        makeBet(player);
    }

    private static void makeBet(Player player) throws IOException {
        int bet = Integer.parseInt(BR.readLine());
        if (bet > 0) {
            bank += player.decreaseMoney(bet);

        } else {
            player.setPass(true);
        }
    }


    private static void giveStartCards(Player player) {
        player.getCards().add(deck.pop());
        player.getCards().add(deck.pop());
    }

    public static void simulateGame(List<Player> players, List<Card> cards) throws IOException {
        for (int i = 0; i < 3; i++) {
            putCardOnTable(cards, i);
        }
        addBet(players);
        putCardOnTable(cards, 3);
        addBet(players);
        putCardOnTable(cards, 4);
        addBet(players);
    }

    private static Game.Candidate findWinner(List<Player> players, List<Card> cards) {
        ru.vsu.cs.KiselevaMaria.ConsoleTable.Candidate winner = new ru.vsu.cs.KiselevaMaria.ConsoleTable.Candidate();
        for (Player player : players) {
            if (!player.isPass()) {
                System.out.println("player: " + player.getName());
                findAllPlayerCombinations(player, cards);
                compareWithWinner(player, winner);
                System.out.println("----------------------");
            }
        }
        return winner;
    }

    private static void compareWithWinner(Player player, ru.vsu.cs.KiselevaMaria.ConsoleTable.Candidate winner) {
        for (CombinationDTO combination : player.getCombinations()) {
            System.out.println(combination);
            ru.vsu.cs.KiselevaMaria.ConsoleTable.Candidate candidate = new ru.vsu.cs.KiselevaMaria.ConsoleTable.Candidate(combination, player);
            findOrSetWinner(winner, candidate);
        }
    }

    private static void findAllPlayerCombinations(Player player, List<Card> cards) {
        for (AbstractCombination combination : CombinationStorage.combinations) {
            combination.check(player, cards);
        }
    }

    private static void finishGame(Player player, List<Player> players) {
        player.increaseMoney(bank);
        bank = 0;
        for (Player player1 : players) {
            deck.put(player1.getCards());
            player1.getCards().clear();
            player1.setPass(false);
            player1.getCombinations().clear();
        }

    }

    private static void findWinner(ru.vsu.cs.KiselevaMaria.ConsoleTable.Candidate winner, ru.vsu.cs.KiselevaMaria.ConsoleTable.Candidate candidate) {
        if (isPriorityBigger(winner, candidate) ||
                isPriorityTheSameAndCardsBetter(winner, candidate)) {
            setWinner(winner, candidate);
        }
    }

    private static void findOrSetWinner(ru.vsu.cs.KiselevaMaria.ConsoleTable.Candidate winner, ru.vsu.cs.KiselevaMaria.ConsoleTable.Candidate candidate) {
        if (winner.getCombination() != null) {
            findWinner(winner, candidate);
        } else {
            setWinner(winner, candidate);
        }
    }

    private static boolean isPriorityBigger(ru.vsu.cs.KiselevaMaria.ConsoleTable.Candidate winner, ru.vsu.cs.KiselevaMaria.ConsoleTable.Candidate candidate) {
        return candidate.getCombination().getPriority() >
                winner.getCombination().getPriority();
    }

    private static boolean isPriorityTheSameAndCardsBetter(ru.vsu.cs.KiselevaMaria.ConsoleTable.Candidate winner, ru.vsu.cs.KiselevaMaria.ConsoleTable.Candidate candidate) {
        return candidate.getCombination().getPriority() ==
                winner.getCombination().getPriority() &&
                candidate.getCombination().getCardsSumValue() >
                        winner.getCombination().getCardsSumValue();
    }

    private static void setWinner(ru.vsu.cs.KiselevaMaria.ConsoleTable.Candidate winner, ru.vsu.cs.KiselevaMaria.ConsoleTable.Candidate candidate) {
        winner.setCombination(candidate.getCombination());
        winner.setPlayer(candidate.getPlayer());
    }


    private static class Candidate {
        private CombinationDTO combination;
        private Player player;

        public Candidate() {
        }

        public Candidate(CombinationDTO combination, Player player) {
            this.combination = combination;
            this.player = player;
        }

        public CombinationDTO getCombination() {
            return combination;
        }

        public Player getPlayer() {
            return player;
        }

        public void setCombination(CombinationDTO combination) {
            this.combination = combination;
        }

        public void setPlayer(Player player) {
            this.player = player;
        }
    }

}



