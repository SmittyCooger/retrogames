package retro.games.tictactoe.config;

public class Player {

	private String name;
	private PlayerType type;
	private char token;
	
	public Player(String name, PlayerType type, char token) {
		super();
		this.name = name;
		this.type = type;
		this.token = token;
	}
	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public PlayerType getType() {
		return type;
	}



	public void setType(PlayerType type) {
		this.type = type;
	}



	public char getToken() {
		return token;
	}



	public void setToken(char token) {
		this.token = token;
	}


	public enum PlayerType{
		HUMAN, COMPUTER
	}
}
