package ludo.square;


import ludo.Board;
import ludo.Token;

/**
 * The HomeSquare is a Square where the player tokens are placed before
 * the tokens can enter the game. Every player has its own four HomeSquares
 * in which his tokens are placed initially.
 * 
 * The responsibility of this Square is that a players token can only leave it
 * when the player rolls a six. Furthermore, special rules can transfer a players
 * token back to its HomeSquare. 
 */
public class HomeSquare extends Square {

	private char playerLetter;
	private Square starSquare;
	
	public HomeSquare(Board board, int rowPosition, int colPosition, char playerLetter) {
		super(board, rowPosition, colPosition);
		this.playerLetter = playerLetter;
	}

	/**
	 * Private Method in HomeSquare to evaluate the right StarSquare  for this HomeSquare via the board Method get
	 * PlayerStartSquare.
	 * Gets called by moveAndLand (below).
	 */

	private void findStarSquare(){

		this.starSquare = board.getPlayersStartSquare(this, playerLetter);
		assert starSquare != null;
	}

	/**
	 * Since the path starts on the StarSquare a Token that moves from the HomeSquare
	 * Square to an other should start on the StarSquare. moveAndLand finds the right
	 * StarSquare and gives this as a parameter to the findSquare() Method.
	 * @param token to move from the HomeSquare to an other Square
	 * @param moves how many Squares to advance
	 * @return the new Square to move on to
	 */
	@Override
	public Square moveAndLand(Token token, int moves){
		findStarSquare();
		if (moves == 6) return this.starSquare;
		return this; 
	}
	
	@Override
	public boolean isHomeSquare() {
		return true;
	}
	
	@Override
	public String squareLabel() {
		if(this.token == null)
			return "  ";
		else
			return this.token + "";
	}
	
	public char getPlayer(){
		return this.playerLetter;
	}

}
