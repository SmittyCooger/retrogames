package retro.games.tictactoe.config;

import retro.games.tictactoe.config.Player.PlayerType;

public interface DefaultConfig {

	public static final int DIM = 3;
	public static final String PLAYER_1_NAME = "Player1";
	public static final String PLAYER_2_NAME = "Player2";
	public static final PlayerType PLAYER_1_TYPE = PlayerType.HUMAN;
	public static final PlayerType PLAYER_2_TYPE = PlayerType.COMPUTER;
	public static final char PLAYER_1_TOKEN = 'O';
	public static final char PLAYER_2_TOKEN = 'X';
}
