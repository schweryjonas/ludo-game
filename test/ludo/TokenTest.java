package ludo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

import ludo.square.Square;


public class TokenTest {

    public Board board;
    public List<Square> path;
    public Token token;

    /**
     * Tests the token moving method.
     */
    @Before
    public void setUpTokenTest() {
        board = new Board();
        path = board.getPath();
        token = new Token('A', 1);
    }

    /**
     * Tests a simple Token move.
     */
    @Test
    public void gameTokenMoving() {
        Square start = path.get(5);
        token.setSquare(start);
        Square stop = path.get(9);
        token.move(4);
        Square end = token.getSquare();
        assertEquals(stop, end);
    }

    /**
    * Tests the case that a token moves from the last element of the list and lands on the right square.
    */
    @Test
    public void moveTokenTooMuch(){
        Square start = path.get(51); 
        token.setSquare(start);
        token.move(1);
        Square square = token.getSquare();
        Token tokenWho = square.getToken();
        assertEquals(token, tokenWho);
        Square end = token.getSquare();
        int stop = path.indexOf(end);
        assertEquals(0,stop); 
    }
    
    /**
     * Tests the Case that a Token moves from some Element of the List, passing the last Element, and lands on the right Square
     */
    @Test
    public void moveOverLimitOfPath(){
        Square start = path.get(48); 
        token.setSquare(start);
        token.move(6);
        Square square = token.getSquare();
        Token tokenWho = square.getToken();
        assertEquals(token, tokenWho);
        Square end = token.getSquare();
        int stop = path.indexOf(end);
        assertEquals(2,stop); 
    }
}
