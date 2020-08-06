package retro.games.tictactoe;

import retro.games.tictactoe.GameStatus;
import retro.games.tictactoe.config.GameConfig;
import retro.games.tictactoe.config.Player;

public class GameManager {

    private GameConfig config;
    private int turn;
    private GameStatus status = GameStatus.Created;
    private static GameManager gm;

    private GameManager() {
        config = new GameConfig();
    }

    public static GameManager getInstance() {
        if (gm == null) {
            gm = new GameManager();
        }
        return gm;
    }

    public GameConfig getConfig() {
        return config;
    }

    public void setConfig(GameConfig newConfig) {
        this.config = newConfig;
    }

    public int getTurn() {
        return turn;
    }

    public GameStatus getStatus() {
        return status;
    }
    
    public void nextTurn() {
        if (status == GameStatus.InProcess) {
            turn++;
        }
    }

    public void updateStatus(GameStatus newStatus) {
        status = newStatus;
    }

    public void start(GameConfig config) {
        if (status == GameStatus.Created) {
            if (config != null) {
                setConfig(config);
            }
            GameEngine.applyConfig(this.config);
            status = GameStatus.InProcess;
            nextTurn();
        }
    }

    public Player getCurrentPlayer() {
        return config.getPlayer(turn % 2 != 0 ? GameConfig.PLAYER_1_NAME : GameConfig.PLAYER_2_NAME);
    }

    public boolean matchHasEnded() {
        return status == GameStatus.Ended || status == GameStatus.Tie;
    }

    public String displayResults() {
        if (matchHasEnded()) {
            StringBuilder sb = new StringBuilder();
            switch (status) {
                case Tie:
                    sb.append("It's a Tie");
                    break;

                default:
                    sb.append(gm.getCurrentPlayer().getName()).append(" won, Congratulations!");
                    break;
            }
            return sb.toString();
        }
        return "Game still in progress";
    }

    public void resetGame() {
        GameEngine.reset();
        status = GameStatus.Created;
        turn = 0;
    }
}
