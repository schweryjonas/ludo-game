package ludo;

import org.junit.*;


import java.util.Deque;
import java.util.LinkedList;

import static org.junit.Assert.*;


public class GameTest {

	public Game testGame;
	public Die die;
	Board board;
	public Deque<Player> mockPlayers;

	@Before
	public void setUpGameTest() {
		die = new Die(6);
		board = new Board();
		mockPlayers = new LinkedList<>();
		mockPlayers.add(new Player('A'));
		mockPlayers.add(new Player('B'));
	}

	/**
	 * Tests the Winner after a Game is played to the End
	 */
	@Test
	public void TestWinner() {
		testGame = new Game(mockPlayers, board);
		testGame.play(die);
		Player A = mockPlayers.getFirst();
		assertEquals(A.getLetter(), 'A');
		assertFalse(testGame.currentPlayer().winner());
		Player B = mockPlayers.getLast();
		assertEquals(B.getLetter(), 'B');
		assertEquals(testGame.getPlayersList().getLast(), testGame.getWinner());
	}

	/**
	 * Tests the Game with 3 Players
	 */
	@Test
	public void TestWithThreePlayers() {
		mockPlayers.add(new Player('C'));
		testGame = new Game(mockPlayers, board);
		testGame.play(die);
	}
	
	/**
	 * Tests the Game with 4 Players
	 */
	@Test
	public void TestWithFourPlayers() {
		mockPlayers.add(new Player('C'));
		mockPlayers.add(new Player('D'));
		testGame = new Game(mockPlayers, board);
		testGame.play(die);
	}
}
