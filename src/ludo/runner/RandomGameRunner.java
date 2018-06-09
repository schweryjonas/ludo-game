package ludo.runner;

import java.util.*;

import ludo.Board;
import ludo.Die;
import ludo.square.Square;
import ludo.Game;
import ludo.Player;

/**
 * Like the class name implies, the RandomGameRunner runs the game
 * randomly until the game is finished, that is, one player has all
 * his four tokens in the destination.
 */

public class RandomGameRunner {
	
	private Game game;
	private Board board;
	List<Square> squares;  //@Silas should be private
	
	/**
	 * Initialize a new RandomGameRunner and play the game.
	 */
	public static void main(String[] args) {
        RandomGameRunner runner = new RandomGameRunner();
	}
	
	/**
	 * Initialize a new RandomGameRunner with the number of 
	 * players, a die with the number of faces, a board where
	 * the tokens move on and a game. 
	 * The play method is evokes directly in the constructor,
	 * so always a new RandomGameRunner is created, the game 
	 * is played.
	 */
	public RandomGameRunner() {
        Player playerA = new Player('A');
        Player playerB = new Player('B');
        Deque<Player> players = new LinkedList<>();
        players.add(playerA);
        players.add(playerB);
        Die die = new Die(6);
        board = new Board(); //@Silas: you can keep the board variable local, it's not used anywhere in the class
        game = new Game(players, board);  //@Silas same for the game
        play(die);
	}
	
	/**
	 * Evokes the play method from the game, to play
	 * the game
	 * @param die, must not be null
	 */
	public void play(Die die) {
		assert die != null;
        game.play(die);
	}
}
