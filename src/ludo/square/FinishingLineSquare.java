package ludo.square;


import ludo.Board;

/**
 * The FinishingLineSquare is a Square that is a part of each players
 * individual path before reaching te GoalSquare. 
 * 
 * The responsibility of this Square is that only tokens from one specific
 * players can land on it. Furthermore, when a players token is on this square,
 * it can only move forward, when the number the player rolls exactly matches
 * with the number of squares to pass to reach the GoalSquare.
 */
public class FinishingLineSquare extends Square {
	
	public FinishingLineSquare(Board board, int rowPosition, int colPosition) {
		super(board, rowPosition, colPosition);
	}
	@Override
	public boolean isFinishingLineSquare(){
		return true;
	}
	@Override
	public String squareLabel() {

		if(this.token == null)
			return "||";
		else
			return this.token + "";
	}

	
}
