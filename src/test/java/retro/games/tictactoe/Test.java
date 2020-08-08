/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retro.games.tictactoe;

import java.util.Arrays;
import java.util.HashSet;
import retro.games.tictactoe.config.AIPlayer;

/**
 *
 * @author HAMDI
 */
public class Test {
    
    public static void main(String[] args) {
        int[] ai_pos = {3};
        int[] hm_pos = {1,5};
        int[] free = {2,4,6,7,8,9};
        HashSet<Integer> ai = new HashSet();
        ai.add(3);
        HashSet<Integer> human = new HashSet();
        human.add(1);
        human.add(5);
        HashSet<Integer> freePositions = new HashSet();
        freePositions.add(2);
        freePositions.add(4);
        freePositions.add(6);
        freePositions.add(7);
        freePositions.add(8);
        freePositions.add(9);
        AIPlayer player = new AIPlayer('X');
        double move = player.calculateNextMove(ai, human, freePositions);
        System.out.println(move);
        System.out.println("Total calls: "+player.calls);
    }
}
