package retro.games.tictactoe;

import java.util.HashSet;
import java.util.Set;

import retro.games.tictactoe.config.GameConfig;
import retro.games.tictactoe.exceptions.PositionTakenException;
import retro.games.tictactoe.utility.GameConstants;

public class GameEngine {

    public static final GameManager GM = GameManager.getInstance();
    public static final HashSet<HashSet<Integer>> WINNING_LINES = GameConstants.getWinningLines();
    private static HashSet<Integer> takenPlaces;
    private static HashSet<Integer> Os = new HashSet<Integer>();
    private static HashSet<Integer> Xs = new HashSet<Integer>();
    private static int boardSize;
    

    public static void applyConfig(GameConfig config) {
        if (config != null) {
            int dim = GameConfig.DIM;
            boardSize = dim * dim;
            takenPlaces = new HashSet<Integer>(boardSize); 
        } else {
            applyConfig(new GameConfig());
        }
    }

    public static void executeTurn(int pos) throws PositionTakenException {
        if (isPositionAvailable(pos)) {
            Set<Integer> currentTurn = GM.getCurrentPlayer().getToken() == 'O' ? Os : Xs;
            takenPlaces.add(pos);
            currentTurn.add(pos);
            if (GM.getTurn() >= 4) {
                GM.updateStatus(calculateResult(currentTurn));
            }
            GM.nextTurn();
        }else{
            throw new PositionTakenException();
        }
    }

    private static GameStatus calculateResult(Set<Integer> currentTurn) {
        for (HashSet<Integer> line : WINNING_LINES) {
            if (currentTurn.containsAll(line)) {
                return GameStatus.Ended;
            }
            if (takenPlaces.size() == boardSize) {
                return GameStatus.Tie;
            }
        }
        return GameStatus.InProcess;
    }

    private static boolean isPositionAvailable(int pos) {
        return !takenPlaces.contains(pos);
    }

    public static void reset() {
        if(takenPlaces !=null)
            takenPlaces.removeAll(takenPlaces);
        Os.removeAll(Os);
        Xs.removeAll(Xs);
    }
}
