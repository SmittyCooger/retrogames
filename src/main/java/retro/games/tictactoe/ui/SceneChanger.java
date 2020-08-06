/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retro.games.tictactoe.ui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import retro.games.tictactoe.GraphicalApp;

/**
 * Simple Preloader Using the ProgressBar Control
 *
 * @author HAMDI
 */
public class SceneChanger implements EventHandler<ActionEvent> {

    public void handle(ActionEvent event) {
        try {
            Parent root = (Parent) FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MainScreen.fxml"));
            GraphicalApp.getStage().getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(SceneChanger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
