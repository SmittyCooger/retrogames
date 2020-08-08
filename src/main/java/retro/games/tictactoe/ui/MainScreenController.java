/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retro.games.tictactoe.ui;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import retro.games.tictactoe.GameEngine;
import retro.games.tictactoe.GameManager;
import retro.games.tictactoe.GameStatus;
import retro.games.tictactoe.config.AIPlayer;
import retro.games.tictactoe.config.Player;
import retro.games.tictactoe.exceptions.PositionTakenException;
import retro.games.tictactoe.utility.ConfigUtility;
import retro.games.tictactoe.utility.GameUtility;

/**
 * FXML Controller class
 *
 * @author HAMDI
 */
public class MainScreenController implements Initializable {

    private int activePane;
    private MediaPlayer audio;
    private Alert alert;

    /**
     * FXML components :
     */
    @FXML
    private AnchorPane startMenu;

    @FXML
    private AnchorPane configPane;

    @FXML
    private AnchorPane game;

    @FXML
    private AnchorPane helpPane;

    @FXML
    private Pane board;

    @FXML
    private ImageView head_line;

    @FXML
    private TextField player1Name;

    @FXML
    private TextField player2Name;

    @FXML
    private ChoiceBox<String> player1Type;

    @FXML
    private ChoiceBox<String> player2Type;

    @FXML
    private Label announcement;

////////////////////////////////////////////////
    /**
     * On doit donner le controle au utilisateur sur la music qui lance dés que
     * l'écran du menu apparaître, L'utilisateur doit être capable d'arréter et
     * reprendre le son du jeu.
     */
    @FXML
    private ImageView sound_on;

    @FXML
    private ImageView sound_off;

    @FXML
    void handleSound_Off(MouseEvent event) {
        boolean visibility = this.sound_off.isVisible();
        this.sound_off.setVisible(!visibility);
        this.sound_on.setVisible(visibility);
        audio.play();
    }

    @FXML
    void handleSound_On(MouseEvent event) {
        boolean visibility = this.sound_on.isVisible();
        this.sound_on.setVisible(!visibility);
        this.sound_off.setVisible(visibility);
        audio.pause();
    }
////////////////////////////////////////////////
    /**
     * les buttons du menu princiale et leur fonctionnalité.
     */

    @FXML
    private Button continue_game;

    @FXML
    void continueGame(ActionEvent event) {
        this.startMenu.setVisible(false);
        this.game.setVisible(true);
        this.back.setVisible(true);
        setAnnouncement();
        this.activePane = 2;
    }

    @FXML
    void startGame(ActionEvent event) {
        this.startMenu.setVisible(false);
        this.configPane.setVisible(true);
        this.activePane = 1;
        this.back.setVisible(true);
    }

    @FXML
    void handleHelp(ActionEvent event) {
        this.startMenu.setVisible(false);
        this.helpPane.setVisible(true);
        this.activePane = 3;
        this.back.setVisible(true);
    }

    @FXML
    void exitGame(ActionEvent event) {
        Platform.exit();
    }
////////////////////////////////////////////////
    /**
     * Assurer la difference des tokens pour les 2 joueurs.
     */

    @FXML
    private RadioButton player2Xtoken;

    @FXML
    private RadioButton player2Otoken;

    @FXML
    void handleTokenO(ActionEvent event) {
        this.player2Xtoken.setSelected(true);
    }

    @FXML
    void handleTokenX(ActionEvent event) {
        this.player2Otoken.setSelected(true);
    }
////////////////////////////////////////////////
    /**
     * Le buttonback qui permet de retourne a l'écran précedent.
     */

    @FXML
    private ImageView back;

    @FXML
    void handleBackScreen(MouseEvent event) {
        switch (this.activePane) {
            case 1:
                this.configPane.setVisible(false);
                break;
            case 2:
                this.board.setDisable(false);
                this.announcement.setText(null);
                if (GameEngine.GM.getStatus() == GameStatus.InProcess) {
                    this.continue_game.setDisable(false);
                } else {
                    this.continue_game.setDisable(true);
                }
                this.game.setVisible(false);
                break;
            case 3:
                this.helpPane.setVisible(false);
                break;
        }
        this.startMenu.setVisible(true);
        this.activePane = 0;
        this.back.setVisible(false);
    }
////////////////////////////////////////////////

    /**
     * La verification du config avant le debut de jeu.
     *
     * @param event
     */
    @FXML
    void handleVerifyConfig(ActionEvent event) {
        if (this.player1Name.getText().isEmpty() || this.player2Name.getText().isEmpty() || this.player1Name.getText().equals(this.player2Name.getText())) {
            alert.setContentText("The player names must be fiiled with two different values!");
            alert.showAndWait();
        } else {
            this.configPane.setVisible(false);
            this.game.setVisible(true);
            this.activePane = Integer.parseInt(this.game.getId());
            resetGame();
            GameEngine.GM.start(ConfigUtility.buildConfig(retrieveConfig()));
            setAnnouncement();
        }
    }
////////////////////////////////////////////////
    /**
     * l'interpretation des tours et la mise a zero.
     */
    @FXML
    private ImageView reset;

    @FXML
    void handleReset(MouseEvent event) {
        resetGame();
        GameEngine.GM.start(null);
        setAnnouncement();
        this.board.setDisable(false);
    }

    @FXML
    void handleTurn(ActionEvent event) {
        Button pos = (Button) event.getSource();
        try {
            StringBuilder sb = new StringBuilder("fxml/");
            sb.append(GameEngine.GM.getCurrentPlayer().getToken() == 'X' ? "X.png" : "O.png");
            ImageView token = new ImageView(sb.toString());
            token.setFitWidth(75);
            token.setFitHeight(70);
            pos.setGraphic(token);
            GameEngine.executeTurn(Integer.parseInt(pos.getId()));
            setAnnouncement();
            Player player2 = GameEngine.GM.getConfig().getPlayer("Player2");
            if (!GameEngine.GM.matchHasEnded() && player2.getType() == Player.PlayerType.COMPUTER) {
                int aiMove = ((AIPlayer)player2).calculateNextMove(player2.getToken() == 'X' ? GameEngine.Xs : GameEngine.Os, player2.getToken() == 'X' ? GameEngine.Os : GameEngine.Xs, GameUtility.getAvailablePosition(GameEngine.takenPlaces));
                pos = getButtonById(aiMove);
                sb.replace(5, sb.length(), "");
                sb.append(player2.getToken() == 'X' ? "X.png" : "O.png");
                token = new ImageView(sb.toString());
                token.setFitWidth(75);
                token.setFitHeight(70);
                pos.setGraphic(token);
                GameEngine.executeTurn(Integer.parseInt(pos.getId()));
                setAnnouncement();
            }
        } catch (PositionTakenException ex) {
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
////////////////////////////////////////////////

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Media song = new Media(getClass().getClassLoader().getResource("fxml/background_music.mp3").toString());
        this.audio = new MediaPlayer(song);
        this.audio.setCycleCount(MediaPlayer.INDEFINITE);
        this.audio.setVolume(100);
        this.audio.play();
        ScaleTransition transition = new ScaleTransition(Duration.seconds(2), this.head_line);
        transition.setToX(3);
        transition.setToY(3);
        transition.play();
        this.player1Type.getItems().addAll(Player.PlayerType.HUMAN.toString(), Player.PlayerType.COMPUTER.toString());
        this.player2Type.getItems().addAll(Player.PlayerType.HUMAN.toString(), Player.PlayerType.COMPUTER.toString());
        this.player1Type.getSelectionModel().select(0);
        this.player2Type.getSelectionModel().select(1);
        this.player2Type.getSelectionModel().selectedIndexProperty().addListener(new javafx.beans.value.ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (oldValue.intValue() == 0) {
                    MainScreenController.this.player2Name.setText("AI");
                } else {
                    MainScreenController.this.player2Name.setText(null);
                }
            }
        });
        player2Name.setText("AI");
        this.alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
    }

    private Map<String, String> retrieveConfig() {
        Map<String, String> config = new HashMap();
        config.put("player1name", this.player1Name.getText());
        config.put("player2name", this.player2Name.getText());
        config.put("player1type", this.player1Type.getSelectionModel().getSelectedItem());
        config.put("player2type", this.player2Type.getSelectionModel().getSelectedItem());
        config.put("player2token", this.player2Otoken.isSelected() ? "O" : "X");
        return config;
    }

    private void setAnnouncement() {
        if (GameEngine.GM.getCurrentPlayer().getToken() == 'O') {
            this.announcement.setStyle("-fx-text-fill: Red");
        } else {
            this.announcement.setStyle("-fx-text-fill: Blue");
        }

        if (!GameEngine.GM.matchHasEnded()) {
            this.announcement.setText(String.format(GameUtility.PLAYERSTURN, GameEngine.GM.getCurrentPlayer().getName()));
        } else {
            this.announcement.setText(GameEngine.GM.displayResults());
            this.board.setDisable(true);
        }
    }

    private void resetGame() {
        for (Node node : this.board.getChildren()) {
            if (node.getStyleClass().contains("positions")) {
                ((Button) node).setGraphic(null);
            }
        }
        GameEngine.GM.resetGame();
    }

    private Button getButtonById(int pos) {
        StringBuilder id = new StringBuilder("#").append(pos);
        return (Button)this.board.lookup(id.toString());
    }
}
