package ludo;

import java.util.*;


import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

	/**
	 * Tests initialization of the player array.
	 */
	@Test 
	public void playerTokensTest() {
		Player player = new Player('A');
		List<Token> tokens = new LinkedList<>();
		tokens.addAll(player.getPlayerTokens());
		assertEquals(tokens.get(0).toString(), "A0");
		assertEquals(tokens.get(1).toString(), "A1");
		assertEquals(tokens.get(2).toString(), "A2");
		assertEquals(tokens.get(3).toString(), "A3");
		assertEquals(player.getPlayerTokens().toString(), "[A0, A1, A2, A3]");
	}
	/**
	 * Tests for a winner
	 */
	@Test 
	public void winnerTest() {
		Board board = new Board();
		Player player = new Player('A');
		Deque<Player> players = new LinkedList<>();
		players.add(player);
		Game game = new Game(players, board);
		Die die = new Die(6);
		game.play(die);
		assertEquals(game.getWinner(), player);
		assertEquals(player.winner(), true);
	}
}
