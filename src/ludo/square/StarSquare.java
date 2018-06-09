package ludo.square;

import ludo.Board;
import ludo.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * The StarSquare is a special Square that the players tokens land on,
 * when they enter the game.
 * 
 * It is part of the general path where every players token can lan on.
 */

public class StarSquare extends Square {

	private char playerLetter;
	List<Token> tokenList = new ArrayList<Token>();

	public StarSquare(Board board, int rowPosition, int colPosition, char player) {
		super(board, rowPosition, colPosition);
		this.playerLetter = player;
	}
	
	@Override
	public boolean isStarSquare() {
		return true;
	}

	public char getPlayer(){
		return this.playerLetter;
	}

	/**
	 * StarSquare does not only have a isStarSquare but also a isPlayerStarSquare method
	 * to evaluate to which Player this StarSquare belongs
	 * @param playerLetter A, B, C, D for each Player
	 * @return true if the playerLetters are the same, false if not.
	 */

	public boolean isPlayersStartSquare(char playerLetter){
		if(this.playerLetter == playerLetter){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * the Token entering is added to the List of Tokens
	 * @param token
	 */
	@Override
	public void enter(Token token) {
		assert token != null;
		tokenList.add(token);

	}
	/**
	 * the Token leaving is removed from the List of Tokens
	 * @param token
	 */
	@Override
	public void leave(Token token) {
		assert token != null;
		tokenList.remove(token);
	}

	/**
	 * multiple Players can Enter this Square and will land on this Square.
	 * @return
	 */
	@Override
	public Square landHereSendHome() {

			return this;
	}

	/*
	 * This Method is only used for Tests not for the Game
	 */
	@Override
	public Token getToken(){
		return tokenList.get(0);
	}

	/**
	 * Either prints the Token occupying this Square or how many Tokens occupy this Square
	 * as in: 3T for "3 Tokens are on this Square".
	 * @return
	 */
	@Override
	public String squareLabel() {

		if(tokenList.isEmpty())
			return "**";
		if(tokenList.size() == 1){
			return tokenList.get(0) + "";
		}
		else
			return tokenList.size() + "T";
	}
}

