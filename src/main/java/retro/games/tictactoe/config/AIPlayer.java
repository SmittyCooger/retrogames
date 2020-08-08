/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retro.games.tictactoe.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import retro.games.tictactoe.GameEngine;
import static retro.games.tictactoe.GameEngine.WINNING_LINES;
import retro.games.tictactoe.GameStatus;

/**
 *
 * @author HAMDI
 */
public class AIPlayer extends Player {

    public int calls;
    
    public AIPlayer(char token) {
        super("AI", PlayerType.COMPUTER, token);
    }

    /**
     * Calculates the best move for the computer with the minimum loss.
     */
    public int calculateNextMove(HashSet<Integer> aiPositions, HashSet<Integer> humanPositions, HashSet<Integer> freePositions) {
        int bestMove = 0;
        double bestScore = Double.NEGATIVE_INFINITY;
        for (Integer pos : freePositions) {
            HashSet<Integer> free = (HashSet<Integer>) freePositions.clone();
            free.remove(pos);
            aiPositions.add(pos);
            double score = minimax(aiPositions, humanPositions, free, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY,false);
            aiPositions.remove(pos);
            if (score > bestScore) {
                bestScore = score;
                bestMove = pos;
            }
        }
        return bestMove;
    }

    private double minimax(HashSet<Integer> aiPositions, HashSet<Integer> humanPositions, HashSet<Integer> freePositions, double alpha, double beta,boolean isMaximizing) {
        this.calls++;
        if (this.calculateResult(aiPositions) == GameStatus.Ended) {
            return 1;
        } else if (this.calculateResult(humanPositions) == GameStatus.Ended) {
            return -1;
        } else if (freePositions.isEmpty()) {
            return 0;
        }

        if (isMaximizing) {
            double bestScore = Double.NEGATIVE_INFINITY;
            for (Integer pos : freePositions) {
                HashSet<Integer> free = (HashSet<Integer>) freePositions.clone();
                free.remove(pos);
                aiPositions.add(pos);
                double score = minimax(aiPositions, humanPositions, free, alpha, beta, false);
                aiPositions.remove(pos);
                bestScore = Math.max(score, bestScore);
                alpha = Double.max(alpha, score);
                if(beta <= alpha)
                    break;
            }
            return bestScore;
        } else {
            double bestScore = Double.POSITIVE_INFINITY;
            for (Integer pos : freePositions) {
                HashSet<Integer> free = (HashSet<Integer>) freePositions.clone();
                free.remove(pos);
                humanPositions.add(pos);
                double score = minimax(aiPositions, humanPositions, free, alpha, beta, true);
                humanPositions.remove(pos);
                bestScore = Math.min(score, bestScore);
                beta = Double.min(beta, score);
                if(alpha >= beta)
                    break;
            }
            return bestScore;
        }
    }

    private GameStatus calculateResult(Set<Integer> currentTurn) {
        for (HashSet<Integer> line : WINNING_LINES) {
            if (currentTurn.containsAll(line)) {
                return GameStatus.Ended;
            }
        }
        return GameStatus.InProcess;
    }
}
