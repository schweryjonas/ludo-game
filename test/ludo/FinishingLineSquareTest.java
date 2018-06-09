package ludo;

import ludo.square.FinishingLineSquare;
import ludo.square.Square;
import org.junit.*;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class FinishingLineSquareTest {

    /**
     * FinishingLine has not a much of Logic or specialCases so we only Test the initialization
     */
    @Test
     public void testIsEnterFinishingLineSquare() {
    	 Board board = mock(Board.class);
    	 Square square= new FinishingLineSquare(board, 1,1 );
    	 assertEquals(square.isFinishingLineSquare(), true );
     }

}
