package retro.games.tictactoe.utility;

import java.util.Arrays;
import java.util.HashSet;

public class GameConstants {

    public static String WELCOME = "Welcome to TicTacToe !";
    public static String DIM_ENTRY = "Please enter board size (%d): ";
    public static String NAME_ENTRY = "Please enter name for %s (%s): ";
    public static String TOKEN_ENTRY = "Please enter token for %s (%c): ";
    public static String TYPE_ENTRY = "Please enter %s type <-0 for HUMAN / 1 for COMPUTER-> (%d): ";
    public static String PLAYERSTURN = "%s 's turn :";
    public static Integer[][] winningLines = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, {1, 5, 9}, {3, 5, 7}};

    /**
     *
     * @return
     */
    public static HashSet<HashSet<Integer>> getWinningLines() {
        HashSet<HashSet<Integer>> winlines = new HashSet<HashSet<Integer>>();

        for (Integer[] line : winningLines) {
            HashSet<Integer> winningline = new HashSet(Arrays.asList(line));
            winlines.add(winningline);
        }
        return winlines;
    }

    public static String help() {
        StringBuilder sb = new StringBuilder("Valid options are :\n");
        sb.append("\t-c\texecute in Console mode.\n")
                .append("\t-ui\texecute in UI mode(default).\n")
                .append("\t-help\tdisplay this help screen.\n");
        return sb.toString();
    }
}
