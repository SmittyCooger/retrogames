package retro.games.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class GraphicalApp extends Application {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        GraphicalApp.stage = stage;
        Parent root = (Parent) FXMLLoader.load(getClass().getClassLoader().getResource("fxml/IntroScreen.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("fxml/style.css");
        stage.getIcons().add(new Image("fxml/logo.png"));
        stage.setTitle("TicTacToe");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main() {
        launch();
    }
}
