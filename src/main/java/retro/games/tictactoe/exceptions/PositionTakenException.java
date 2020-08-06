package retro.games.tictactoe.exceptions;

public class PositionTakenException extends Exception {

	public PositionTakenException() {
		super("Position already taken! Plz choose another one.");
	}

	public PositionTakenException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
