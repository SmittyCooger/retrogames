/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retro.games.tictactoe;

import retro.games.tictactoe.exceptions.InvalidArgumentsException;
import retro.games.tictactoe.utility.GameConstants;

/**
 *
 * @author HAMDI
 */
public class Main {

    public static void main(String[] args) {
        try{
        if (args.length > 1) {
            throw new InvalidArgumentsException();
        }
        if (args.length != 0) {
            switch (args[0]) {
                case "-ui":
                    GraphicalApp.main();
                    break;
                case "-c":
                    ConsoleApp.main();
                default:
                    throw new InvalidArgumentsException();
            }
        } else {
            GraphicalApp.main();
        }
        }catch(InvalidArgumentsException ex){
            System.out.println(ex.getMessage());
        }
    }
}
