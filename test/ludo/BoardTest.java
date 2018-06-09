package ludo;

import ludo.square.Square;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BoardTest {
    public Board board;
    public List<Square> path;
    public List<Square> finishPath;
    public Token token;


    /**
     * Tests the token moving Method
     */
    @Before
    public void setUp() {
        board = new Board();
        token = new Token('A', 1);
        path = board.getPath();
        finishPath = board.getFinishLinePath(token);
    }

    /**
     * Tests the findGoalSquare Method
     * the Token should only move when he lands on the GoalSquare, else he should remain on the startSquare
     */
    @Test
    public void moveFromFinishLineNotReachingGoal(){
        Square start = finishPath.get(3);
        start.enter(token);
        token.setSquare(start);
        token.move(1);
        assertEquals(start,token.getSquare());
    }
    
    /**
     * Tests the findGoalSquare Method when the Token rolls the right number to move to GoalSquare
     * the Token should only move when he lands on the GoalSquare, else he should remain on the startSquare
     */
    @Test
    public void moveFromFinishLineReachGoal(){
        Square start = finishPath.get(5);
        start.enter(token);
        token.setSquare(start);
        token.move(1);
        assertEquals(finishPath.get(6),token.getSquare());
        assertEquals(true, token.getSquare().isGoalSquare());
    }
    
    /**
     * Tests 3 simple cases for the FindSquare Method
     */
    @Test
    public void testFindSquare(){
        Square start = path.get(10);
        Square stop = board.findSquare(start,token,5);
        assertEquals(15, path.indexOf(stop));
        Square stop2 = board.findSquare(stop,token,1);
        assertEquals(16, path.indexOf(stop2));
        Square stop3 = board.findSquare(stop2,token,9);
        assertEquals(25, path.indexOf(stop3));
    }
    
    /**
     * Tests 1 simple case for the FindGoalSquare Method for not reaching the Goal
     */
    @Test
    public void testFindGoalSquare(){
        Square start = finishPath.get(0);
        Square stop = board.findGoalSquare(start,token,10);
        assertEquals(0, finishPath.indexOf(stop));
        assertEquals(false,stop.isGoalSquare());
    }
    
    /**
     * Tests 1 simple case for the FindGoalSquare Method for reaching the Goal
     */
    @Test
    public void testFindGoalSquare2(){
        Square start = finishPath.get(5);
        Square stop = board.findGoalSquare(start,token,1);
        assertEquals(6, finishPath.indexOf(stop));
        assertEquals(true, stop.isGoalSquare());
    }
}
