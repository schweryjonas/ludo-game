package ludo;

import ludo.square.*;
import java.util.*;

/**
 * Represents one of the four tokens that each player has.
 * 
 * The responsibilities of the token are moving the number of moves
 * the player rolls, joining the game and knowing if it is on the 
 * board because the token is not able to move if it is not on the 
 * board. Every token is represented with a valid char and a number 
 * greater or equal than zero.
 */

public class Token {
	
	private char letter;
	private int number;
	private int rowPosition;
	private int columnPosition;
	private boolean finished = false;
	private boolean isOnBoard = false;
	private Square square;
	private Square homeSquare;
	private Square EnterFinishLineSquare;
	
	/**
	 * Initialization of a new token that consists of a letter and the
	 * corresponding number.
	 * @param letter the same as the corresponding player
	 * @param number any number that is equal or greater than zero
	 */
	public Token (char letter, int number ) {
		this.letter = letter;
		this.number = number;
		invariant();
	}

	private boolean invariant() {
		return Character.isLetter(letter) 
				&& number >= 0;
	}
	


    /**
     * sets the Token on a specific Square
     * @param square the square to set him on
     */
	public void setSquare(Square square){
		assert square != null;
	    this.square = square;
    }

	/**
	 * Serves for retrieving the actual square of the token
	 * @return actual square
	 */
    public Square getSquare(){
	    return this.square;
    }
	

	
	/**
	 * Token enters in his HomeSquares when it joins the game.
	 * @param game
	 */
	public void joinGameToken(Game game) {
		assert game != null;
		for (Square s : game.getHomeSquares()) {
			if (s.getPlayer() == this.letter && s.isEmpty()) {
				s.enter(this);
				this.square = s;
				this.homeSquare = s;
				break;
			}
		}
	}

	public void rightFinishLineSquare(Game game){
		this.EnterFinishLineSquare = game.getEnterFinishLineSquare(this);
	}

	public Square getEnterFinishLineSquare(){
		return this.EnterFinishLineSquare;
	}
	
	public Square getHomeSquare(){
		return this.homeSquare;
	}

	public void setHomeSquare(Square square){
		this.homeSquare = square;
	}
	/**
	 * Move on the game board.
	 * @param moves amount of squares to move forward, must be > 0
	 */
	public void move (int moves) {
		assert moves > 0;
		assert square != null;
		this.square.leave(this);
		this.square = this.square.moveAndLand(this, moves);
		this.square.enter(this);
	}

	/**
	 * @param finished is a boolean value that is true if a token
	 * reaches the GoalSquare, false otherwise
	 */
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	/**
	 * @return true, if a token reached his GoalSquare, false otherwise
	 */
	public boolean getFinished() {
		return finished;
	}
	
	/**
	 * String representation of the token.
	 */
	public String toString() {
		return letter + "" + number;
	}

	/**
	 * @return the corresponding letter of the token which indicates
	 * to which player the token belongs
	 */
	public char getTokenLetter(){
	    return letter;
    }

	/**
	 * Sets the new row position of the token in the board after moving
	 * along the board.
	 * @param row in the board, must be > 0
	 */
	public void setRowPosition(int row) {
		assert row >= 0;
		this.rowPosition = row;
	}
	/**
	 * Serves for retrieving the actual row of the token
	 * @return current row position in the board, must be > 0
	 */
	public int getRowPosition() {
		this.rowPosition = square.getRowPosition();
		return rowPosition;
	}

	/**
	 * Sets the new column position of the token in the board after moving
	 * along the board.
	 * @param column in the board, must be > 0
	 */
	public void setColumnPosition(int column) {
		assert column >= 0;
		this.columnPosition = column;
	}

	/**
	 * Serves for retrieving the actual column of the token.
	 * @return current row position in the board
	 */
	public int getColumnPosition() {
		this.columnPosition = square.getRowPosition();
		return columnPosition;
	}
}
