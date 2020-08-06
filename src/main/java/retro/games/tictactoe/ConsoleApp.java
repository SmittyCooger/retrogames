package retro.games.tictactoe;

import java.util.Scanner;

import retro.games.tictactoe.config.Player;
import retro.games.tictactoe.exceptions.PositionTakenException;
import retro.games.tictactoe.utility.GameConstants;

public class ConsoleApp {

    static char[] pos = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
    static Scanner sc = new Scanner(System.in);

    public static void init() {
        GameEngine.GM.start(null);
    }

    public static void nextTurn() throws PositionTakenException {
        int p;
        Player player = GameEngine.GM.getCurrentPlayer();
        System.out.println(String.format(GameConstants.PLAYERSTURN, player.getName()));
        p = sc.nextInt();

        while (p < 1 || p > 9) {
            System.out.println("Plz enter a valid position!!");
            p = sc.nextInt();
        }

        GameEngine.executeTurn(p);
        updateBoard(p - 1, player);
    }

    public static void updateBoard(int position, Player player) {
        pos[position] = player.getToken();
    }

    public static void DrawBoard() {
        StringBuilder sb = new StringBuilder(" %c | %c | %c ");
        sb.append("\n");
        sb.append("---|---|---");
        sb.append("\n");
        sb.append(" %c | %c | %c ");
        sb.append("\n");
        sb.append("---|---|---");
        sb.append("\n");
        sb.append(" %c | %c | %c \n\n");

        System.out.println(
                String.format(sb.toString(), pos[0], pos[1], pos[2], pos[3], pos[4], pos[5], pos[6], pos[7], pos[8]));

    }

    public static void main() {
        ConsoleApp.init();
        while (!GameEngine.GM.matchHasEnded()) {
            DrawBoard();
            try {
                nextTurn();
            } catch (PositionTakenException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
        DrawBoard();
        System.out.println(GameEngine.GM.displayResults());
    }
}
