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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Controller {
    private static final BufferedReader Br = new BufferedReader(new InputStreamReader(System.in));

    private List<Player> players = new ArrayList<>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button StartTheGame;

    @FXML
    private TextField TextName;

    @FXML
    private TextField NumberOfPlayers;


    public static void createPlayers(List<Player> players, String name, int n) throws IOException {
        //String name = Br.readLine();//себе отключить ai(т.е первому игроку)
        for (int i = 0; i < n; i++) {
            Player player = new Player("Player" + (i + 1));
            if (i == 0) {
                player.setAi(false);
                player.setName(name);
            }
            players.add(player);
        }
    }

    @FXML
    void initialize() throws IOException {

        if(TextName != null && NumberOfPlayers!=null) {
            StartTheGame.setOnAction(actionEvent -> {
                int n = Integer.parseInt(NumberOfPlayers.getText());
                String name = TextName.getText();
                try {
                    createPlayers(players, name, n);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                StartTheGame.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/sample2.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();

            });
        }

    }

    public ResourceBundle getResources() {
        return resources;
    }

    public void setResources(ResourceBundle resources) {
        this.resources = resources;
    }

    public URL getLocation() {
        return location;
    }

    public void setLocation(URL location) {
        this.location = location;
    }

    public TextField getNumberOfPlayers() {
        return NumberOfPlayers;
    }

    public void setNumberOfPlayers(TextField numberOfPlayers) {
        NumberOfPlayers = numberOfPlayers;
    }

    public Button getStartTheGame() {
        return StartTheGame;
    }

    public void setStartTheGame(Button startTheGame) {
        StartTheGame = startTheGame;
    }

    public TextField getTextName() {
        return TextName;
    }

    public void setTextName(TextField textName) {
        TextName = textName;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
