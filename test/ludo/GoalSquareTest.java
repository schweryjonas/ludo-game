package ludo;

import ludo.square.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GoalSquareTest {

    Square goalSquare;
    Board board;
	
	@Before
	public void setUp() {
	    board = new Board();
		goalSquare = new GoalSquare(board, 1,1); 
	}
	
    /**
     * Tests if tokens entering the GoalSquare are finished(), checks that none is sent home.
     */
	@Test
	public void secondToEnterGoalSquare(){
	    Token token = new Token('A', 1);
	    Token token1 = new Token('A',2);
	    goalSquare.enter(token);
	    goalSquare.enter(token1);
        assertEquals(true,token.getFinished());
        assertEquals(true,token1.getFinished());
	    assertEquals(token.getSquare(), token1.getSquare());
    }
	
    /**
     * Tests the same with 4 tokens.
     */
    @Test
    public void fourthTokenToEnterGoalSquare(){
        Token token = new Token('A', 1);
        Token token1 = new Token('A',2);
        Token token2 = new Token('A', 1);
        Token token3 = new Token('A',2);
        goalSquare.enter(token);
        goalSquare.enter(token1);
        goalSquare.enter(token2);
        goalSquare.enter(token3);
        assertEquals(true,token.getFinished());
        assertEquals(true,token1.getFinished());
        assertEquals(true,token2.getFinished());
        assertEquals(true,token3.getFinished());
        assertEquals(token3.getSquare(), token1.getSquare());
        assertEquals(token3.getSquare(), token2.getSquare());
        assertEquals(token3.getSquare(), token.getSquare());
    }

}
