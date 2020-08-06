/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retro.games.tictactoe.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author HAMDI
 */
public class IntroScreenController implements Initializable {

    @FXML
    private ImageView production;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(4), production);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setOnFinished(new SceneChanger());
        fadeIn.play();
    }
}
