package ludo.square;

import ludo.Token;

/**
 * Serves as an Application Programming Interface for all square types.
 *
 * Every Square must be part of the board, and therefore of the game and
 * has to provide methods that the players tokens can move along the board.
 * Since the game is static, every Square has to know its exact position
 * on the board.
 */
public interface ISquare {

	/**
	 * Retrieve the row position on the board.
	 *
	 * @return current row position
	 */
	public int getRowPosition();

	/**
	 * Set the row position of the square in the board to initialize the board
	 *
	 * @param
	 */
	public void setRowPosition(int rowPosition);

	/**
	 * Retrieve the column position on the board.
	 *
	 * @return current column position
	 */
	public int getColPosition();

	/**
	 * Set the column position of the square in the board to initialize the board.
	 *
	 * @param position
	 */
	public void setColPosition(int colPosition);

	/**
	 * Records that a token enters into this square
	 *
	 * @param token the token that lands on this square
	 */
	public void enter(Token token);

	/**
	 * Lets the token leave from this square.
	 *
	 * @param token the token that leaves this square
	 */
	public void leave(Token token);

	/**
	 * Checks if it is a Square of type HomeSquare.
	 */
	public boolean isHomeSquare();

	/**
	 * Checks if it is a Square of type GoalSquare.
	 */
	public boolean isGoalSquare();

	/**
	 * Checks if it is a Square of type StarSquare
	 */
	public boolean isStarSquare();

	/**
	 * Checks if it is a Square of type EnterFinishingLineSquareSquare
	 */
	public boolean isEnterFinishingLineSquare();

	/**
	 * Checks if already a token is on this square.
	 *
	 * @return true, if the is already a token it, false otherwise
	 */
	//public boolean isOccupied();

	/**
	 * String representation of the square on the board.
	 */
	public String squareLabel();

	/**
	 * Method lands the token on the new Square and returns it to the token
	 *
	 * @param token the token to be moved
	 * @param moves the moves it has to make
	 * @return the square it lands on
	 */
	public Square moveAndLand(Token token, int moves);

	/**
	 * checks if the Square is occupied if yes it returns the Tokens HomeSquare
	 *
	 * @return either the square or the HomeSquare
	 */
	public Square landHereSendHome();

	/**
	 * checks if a Token is already on that square
	 *
	 * @return
	 */
	public boolean isEmpty();

	/**
	 *
	 */
	public void sendTokenHome(Token token);
}