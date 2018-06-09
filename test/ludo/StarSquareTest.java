package ludo;

import ludo.square.StarSquare;
import ludo.square.HomeSquare;
import ludo.square.Square;
import org.junit.*;

import java.util.*;
import static org.junit.Assert.*;


public class StarSquareTest {

    private Game game;
    private Board board;
    public List<Square> path;
    public Deque<Player> players;
    public Die die;

    @Before
    public void setUpStarSquareTest(){

        board = new Board();
        die = new Die(6);
        players = new LinkedList<>();
        players.add(new Player('A'));
        players.add(new Player('B'));
        game = new Game(players,board);
    }

    /**
     * The same as in homeSquare, it tests if a StarSquare stays empty when the Token didn't rolled six
     */

    @Test
    public void TestStarSquareEntering(){

        Token token = game.currentToken();
        token.move(3);
        Square end = token.getSquare();
        assertEquals(true, end.isHomeSquare());


    }
    /**
     * Tests if the Token enters the StarSquare when rolled 6
     */

    @Test
    public void TestTokenMovesToStarSquare(){

        Token token = game.currentToken();
        token.move(6);
        Square end = token.getSquare();
        assertEquals(true, end.isStarSquare());

    }

    /**
     * Tests the SquareLabel and if multiple Tokens can stay on the StarSquare
     */
    @Test
    public void testSquareLabel(){

        Square starSquare = new StarSquare(board,2,2, 'A');
        Token token1 = new Token('A',1);
        Token token2 = new Token('B',1);
        Token token3 = new Token('C',1);
        starSquare.enter(token1);
        starSquare.enter(token2);
        assertEquals("2T", starSquare.squareLabel());
        starSquare.enter(token3);
        assertEquals(token1.getSquare(),token2.getSquare());
        assertEquals("3T", starSquare.squareLabel());

    }

    /*
    *Tests the PrintLine Method and if multiple tokens can enter this Square
     */
    @Test
    public void test10TokensEntering(){
        Token token1 = new Token('A',1);
        Token token2 = new Token('B',1);
        Token token3 = new Token('C',1);
        Token token4 = new Token('A',1);
        Token token5 = new Token('B',1);
        Token token6 = new Token('C',1);
        Token token7 = new Token('A',1);
        Token token8 = new Token('B',1);
        Token token9 = new Token('C',1);
        Token token10 = new Token('A',1);
        Square starSquare = new StarSquare(board,2,2, 'A');
        Square homeSquare = new HomeSquare(board,3,3,'A');
        token1.setHomeSquare(homeSquare);

        starSquare.enter(token1);
        starSquare.enter(token2);
        starSquare.enter(token3);
        starSquare.enter(token4);
        starSquare.enter(token5);
        starSquare.enter(token6);
        starSquare.enter(token7);
        starSquare.enter(token8);

        assertEquals("8T", starSquare.squareLabel());
        starSquare.enter(token9);

        assertEquals(token1.getSquare(),token9.getSquare());
        assertEquals("9T", starSquare.squareLabel());

        starSquare.enter(token10);
        assertEquals("10T", starSquare.squareLabel());



    }

}
