package ludo;

import java.util.*;
import ludo.Game;
import ludo.Token;

/**
 * Represents a single Player in the Ludo game.
 * 
 * The responsibilities of the player are that he can access his 
 * four different tokens and that he can join the game.
 */
public class Player {
	
	private char letter;
	private List<Token> token;
	private boolean winner = false;
	boolean rolled6 = false; //@Silas careful about visibility, should probably be private
    
    /**
     * Initializes the player with his specific identification A, B, C or D
     * and adds numbers to the players tokens in order to differentiate between them
     * @param letter A, B, C or D
     */
	public Player (char letter) {
		this.letter = letter;
		this.token = fillToken(new ArrayList<Token>());
		invariant();
	}

	private boolean invariant(){
        return Character.isLetter(letter);
    }
	
	/**
	 * Fills the player with its tokens
	 * @param array of type Token
	 * @return the filled in Token array
	 */
	private List<Token> fillToken(ArrayList<Token> array) {
	    assert array != null;
		for (int i = 0; i < 4; i++) {
			array.add(new Token(letter, i));
		}
		return array;
	}

	/**
	 * Returns only one random element of the array from the specific players tokens
	 * since the player will randomly move one of his four tokens after rolling the die
	 * @return randomly chosen Token of the player
	 */

	public Token getToken(){
		Random r = new Random();
		int randomInt = r.nextInt(token.size());
		Token returnToken =  this.token.get(randomInt);
		if (returnToken.getFinished()) {
			return getToken();
		}
		return returnToken;
	}
	
	/**
	 * Returns all the tokens that can be moved by the player
	 * @return array of tokens from specific player
	 */
	public List<Token> getPlayerTokens() {
		return this.token;
	}
    
	/**
	 * Player joins the game by placing his four tokens int his specific
	 * HomeSquares
	 */
	public void joinGame(Game game) {
		for(int i = 0; i< this.token.size(); i ++) {
			token.get(i).joinGameToken(game);
			token.get(i).rightFinishLineSquare(game);
		}
    }
    
	/**
	 * @return the winning player if the game is over, that is, all the players
	 * tokens are on the GoalSquare
	 */
	public boolean winner() {
		//@Silas if you have 100 tokens, this might get a bit difficult to do =) you could iterate over the list and return false
		//if a token is not finished, and return true at the end (i.e. haven't returned false)
		if(token.get(0).getFinished() 
				&& token.get(1).getFinished() 
					&& token.get(2).getFinished() 
						&& token.get(3).getFinished())
					winner = true;
		return winner;
	}
	
	/**
	 * @return string representation of the player
	 */
	public String toString() {
		return letter + "";
	}

	/**
	 * @return the players char
	 */
	public char getLetter(){
		return this.letter;
	}
}
