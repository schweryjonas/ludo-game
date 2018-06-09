package ludo;

import org.junit.*;

import ludo.square.Square;

import java.util.*;

import static org.junit.Assert.*;


public class EnterFinishLineTest {

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
        path = board.getPath();
    }

    /**
     * Test to check if a Token enters the FinishLine if the StartSquare is the EnterFinishLineSquare
     */
    @Test
	public void testEnterFinishLineFromA() {
        Token token = game.currentToken();
        Square start = token.getEnterFinishLineSquare();
        token.setSquare(start);
        token.move(1);
        Square end = token.getSquare();
        Square stop = board.getFinishLinePath(token).get(1);
        assertEquals(end, stop); 
	}

    /**
     * Tests the case from moving somewhere on the Path (square 49) onto the FinishLinePath
     * This is only tested for Player A.
     */
    @Test
    public void testEnterFinishLine() {
        Token token = game.currentToken();
        Square start = path.get(49);
        token.setSquare(start);
        token.move(4);
        Square end = token.getSquare();
        Square stop = board.getFinishLinePath(token).get(3);
        assertEquals(end, stop);
    }

}
