/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retro.games.tictactoe.exceptions;

import retro.games.tictactoe.utility.GameConstants;

/**
 *
 * @author HAMDI
 */
public class InvalidArgumentsException extends Exception {

    /**
     * Creates a new instance of <code>InvalidArgumentsException</code> without
     * detail message.
     */
    public InvalidArgumentsException() {
        super("Invalid arguments passed to the command\n"+GameConstants.help());
    }

    /**
     * Constructs an instance of <code>InvalidArgumentsException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidArgumentsException(String msg) {
        super(msg);
    }
}
