package ludo;

import ludo.square.Square;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SquareTest {

    private Board board;
    public List<Square> path;
    public Deque<Player> players;
    public Die die;
    public Token token;
    public Token goHomeToken;

    @Before
    public void setUp(){

        board = new Board();
        path = board.getPath();
        token = new Token('A', 1);
        goHomeToken = new Token('B', 1);
    }
    /**
     * Tests if aToken is sent Home if someone moves on his Square
     */
    @Test
    public void sendTokenHome(){

        Square start = path.get(5);
        goHomeToken.setSquare(start);
        start.enter(goHomeToken);

        Square end = path.get(10);
        goHomeToken.setHomeSquare(end);

        Square start2 = path.get(4);
        start2.enter(token);
        token.setSquare(start2);
        token.move(1);


        assertEquals(end, goHomeToken.getSquare());
        assertFalse(goHomeToken.getSquare().equals(token.getSquare()));


    }

}
