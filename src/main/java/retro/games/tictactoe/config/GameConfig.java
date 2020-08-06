package retro.games.tictactoe.config;

import java.util.HashMap;

public class GameConfig implements DefaultConfig{

	private HashMap<String, Player> players = new HashMap<String, Player>();
	
	public GameConfig() {
		players.put(PLAYER_1_NAME, new Player(PLAYER_1_NAME, PLAYER_1_TYPE, PLAYER_1_TOKEN));
		players.put(PLAYER_2_NAME, new Player(PLAYER_2_NAME, PLAYER_2_TYPE, PLAYER_2_TOKEN));
	}

	public Player getPlayer(String name) {
		return players.get(name);
	}

	public void setPlayer(String name, Player player) {
		this.players.put(name, player);
	}
}
