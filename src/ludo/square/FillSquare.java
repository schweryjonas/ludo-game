package ludo.square;


import ludo.Board;

/** 
 * The FillSquare is a Square that has no responsibility in the game,
 * that means, the players token can't land on it. It serves to initialize
 * the board. 
 */
public class FillSquare extends Square {
	public FillSquare(Board board, int rowPosition, int colPosition) {
		super(board, rowPosition, colPosition);
	}
	
	@Override
	public String squareLabel() {
		return "##";
	}

	
}