package ludo;

import ludo.square.Square;
import org.junit.Before;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HomeSquareTest {

	private Game game;
	private Board board;
	public List<Square> path;
	public Deque<Player> players;
	public Die die;

	@Before
	public void setUp(){
		board = new Board();
		die = new Die(6);
		players = new LinkedList<>();
		players.add(new Player('A'));
		players.add(new Player('B'));
		game = new Game(players,board);
	}
	
	/**
	 * Tests if the initialization on the HomeSquare is correct.
	 */
	@Test 
	public void startOneHomeSquare() {
		Token token = game.currentToken();
		Square start = token.getSquare();
		assertEquals(true, start.isHomeSquare());	
	}
	
	/**
	 * Tests if a HomeSquare is Empty after Player rolled six and moved on.
	 */
	@Test
	public void moveAndLand(){
		Token token = game.currentToken();
		Square start = token.getSquare();
		assertEquals(true, start.isHomeSquare());
		token.move(6);
		assertEquals(true, start.isEmpty());
	}
	
	/**
	 * Tests if a Game is still occupied with the Token when he didn't roll 6 and could not move on to the
	 * starSquare.
	 */
	@Test
	public void CanNotMoveAndLand(){
		Token token = game.currentToken();
		Square start = token.getSquare();
		assertEquals(true, start.isHomeSquare());
		token.move(3);
		Square stop = token.getSquare();
		assertEquals(true, stop.isHomeSquare());
		assertEquals(start, stop);
	}

}
