package ludo;

import org.junit.*;

import ludo.Game;
import ludo.Die;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DieTest {
	
	/*
	 * @Silas this isn't really testing the die, since you mock it. However, it is difficult to test a random component. I think the best you could do
	 * is do e.g. 1000 rolls and check if the value is always 1 <= roll <= 6, to have some "certainty"
	 */
	
	@Test
	public void testRollAndGetFaceOfDie () {
		Game game = mock(Game.class);
		Die die = mock(Die.class); 
		when(game.notOver()).thenReturn(true);
		int roll = 6;
		when(die.roll()).thenReturn(roll);
		assertEquals(6, roll );
		int face = 4;
		when(die.getFace()).thenReturn(face);
		assertEquals(die.getFace(), 4);
	}
	
}