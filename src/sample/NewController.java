package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import card.Card;
import common.Deck;
import common.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class NewController {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int bank = 0;
    private final Controller controller = new Controller();
    private static Deck deck;

    static {
        try {
            deck = new Deck();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView Card1;

    @FXML
    private ImageView Card2;

    @FXML
    private ImageView Card3;

    @FXML
    private ImageView Card4;

    @FXML
    private ImageView Card5;

    @FXML
    private ImageView Card6;

    @FXML
    private ImageView Card7;

    @FXML
    private Button HandOutCards;

    @FXML
    private Button Pass;

    @FXML
    private TextArea Bank;

    @FXML
    private Spinner<Integer> Bet;

    @FXML
    private Button PlaseABet;

    private static void addBet(List<Player> players, int bet) throws IOException {
        for (Player player : players) {
            if (!player.isAi()) {// если играю я, а не AI
                if (player.isPass()) {
                    continue;
                }
                makeBet(players, bet);
            } else {
                bank = bank + player.decreaseMoney(100);
            }
        }
    }

    /*public static void simulateGame2(List<Player> players, List<Card> cards) throws IOException {
        addBet(players);
        putCardOnTable(cards);
        addBet(players);
    }*/

    private static void putCardOnTable(List<Card> cards) {
        cards.add(deck.pop());
    }

    public static void simulateGame1(List<Player> players, List<Card> cards) throws IOException {
        for (int i = 0; i < 3; i++) {
            putCardOnTable(cards);
        }
    }

    private static void makeBet(List<Player> players, int bet)  {
        for (Player player : players) {
            if (!player.isAi()) {
                if (bet > 0) {
                    bank += player.decreaseMoney(bet);
                } else {
                    player.setPass(true);
                }
            } else {
                bank = bank + player.decreaseMoney(50);
            }
        }
    }

    private static void giveStartCards(Player player) {
        player.getCards().add(deck.pop());
        player.getCards().add(deck.pop());
    }

    public static void giveCardsToPlayers(List<Player> players) {
        for (Player player : players) {
            giveStartCards(player);
        }
    }

    @FXML
    void initialize() {
        List<Player> players = controller.getPlayers();
        List<Card> cards = new ArrayList<>(5);
        HandOutCards.setOnAction(actionEvent -> {
                        giveCardsToPlayers(players);

                    Card6.setImage(players.get(0).getCards().get(0).getImage());
                    Card7.setImage(players.get(0).getCards().get(1).getImage());
                }
        );

        PlaseABet.setOnAction(actionEvent -> {
            int bet = Bet.getValue();

                makeBet(players, bet);
            Bank.setText(String.valueOf(bank));

        });

        HandOutCards.setOnAction(actionEvent -> {
            try {
                simulateGame1(players, cards);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //
            Card1.setImage(cards.get(0).getImage());
            Card2.setImage(cards.get(1).getImage());
            Card3.setImage(cards.get(2).getImage());
        });

        PlaseABet.setOnAction(actionEvent -> {
            int bet = Bet.getValue();
            try {
                addBet(players, bet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Bank.setText(String.valueOf(bank));
        });

        HandOutCards.setOnAction(actionEvent -> {
            putCardOnTable(cards);
        });

        PlaseABet.setOnAction(actionEvent -> {
            int bet = Bet.getValue();
            try {
                addBet(players, bet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Bank.setText(String.valueOf(bank));
        });

    }

    public Button getPass() {
        return Pass;
    }

    public void setPass(Button pass) {
        Pass = pass;
    }

    public TextArea getBank() {
        return Bank;
    }

    public void setBank(TextArea bank) {
        Bank = bank;
    }

    public Spinner<?> getBet() {
        return Bet;
    }

    public void setBet(Spinner<Integer> bet) {
        Bet = bet;
    }
}