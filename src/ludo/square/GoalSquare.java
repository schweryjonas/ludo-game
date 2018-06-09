package ludo.square;


import java.util.*;

import ludo.Board;
import ludo.Token;

/** 
 * The GoalSquare is a Square that serves as the Last Square in the Game that
 * a players token can reach and is individually for every player. Logically,
 * means that this square is not part of the general path where every players 
 * tokens can land on.
 * 
 * The responsibility of this square the that it takes the players token that
 * reaches it out of the game. When all the four tokens of one player reached
 * this square, the game will terminate, since this player will win the game.
 * 
 */

public class GoalSquare extends Square {

	List<Token> reachedGoal = new ArrayList<Token>(4);
	

	public GoalSquare(Board board, int rowPosition, int colPosition) {
		super(board, rowPosition, colPosition);
	}
	
	@Override
	public boolean isGoalSquare() {
		return true;
	}

	/**
	 * prints the squareLabel or how many Tokens are already on GoalSquare
	 * @return
	 */
	@Override
	public String squareLabel() {
		if(reachedGoal.size() == 0)
			return "$$";
		else
			return reachedGoal.size() + "" + this.token.getTokenLetter();
	}
	
	@Override
	public Square landHereSendHome() {
		return this;
	};
	
	@Override
	public void enter(Token token) {
		assert token != null;
		this.token = token;
		this.token.setFinished(true);
		reachedGoal.add(token);
		
	}

	

}
