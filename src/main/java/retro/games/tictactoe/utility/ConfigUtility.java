package retro.games.tictactoe.utility;

import java.util.Map;
import javafx.scene.layout.GridPane;
import retro.games.tictactoe.config.GameConfig;
import retro.games.tictactoe.config.Player;

public class ConfigUtility {

	public static void Export(GameConfig config) {
		
	}
	
	public static GameConfig Import() {
		return null;
	}
        
        public static GameConfig buildConfig(Map<String,String> config){
            
            String name = config.get("player2name");
            Player.PlayerType type = Player.PlayerType.valueOf(config.get("player2type"));
            char token = config.get("player2token").charAt(0);
            Player player2 = new Player(name, type, token);
            
            name = config.get("player1name");
            type = Player.PlayerType.valueOf(config.get("player1type"));
            token = token == 'O' ? 'X' : 'O';
            Player player1 = new Player(name, type, token);
            
            GameConfig gc = new GameConfig();
            gc.setPlayer("Player1", player1);
            gc.setPlayer("Player2", player2);
            
            return gc;
        }
                
}
