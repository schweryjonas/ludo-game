package ludo.square;

import ludo.Game;
import ludo.Token;
import ludo.Player;
import ludo.Board;

/**
 * Square functionality common to all types of squares.
 */
public abstract class Square implements ISquare {
	
	protected Board board;
	protected Game game;
	protected int rowPosition;
	protected int colPosition;
	protected Player player;
	protected Token token;

	public Square(Board board, int rowPosition, int colPosition) {
		
		this.board = board;
		this.rowPosition = rowPosition;
		this.colPosition = colPosition;
		assert invariant();
	}
	
	private boolean invariant() {
		return  board != null
				  && rowPosition >= 0
					 && colPosition >= 0;
	}

	public Token getToken(){
		return this.token;
	}
	
	public int getRowPosition() {
		return this.rowPosition;
	}
	
	public void setRowPosition(int position) {
		this.rowPosition = position;
	}
	
	public int getColPosition() {
		return this.colPosition;
	}
	
	public void setColPosition(int position) {
		this.colPosition = position;
	}
	
	public void enter(Token token) {
		assert token != null;
		this.token = token;
	}

    public void leave(Token token) {
    	assert token != null;
    	this.token = null;
    }

	public boolean isEnterFinishLineSquare() {
		return false;
	}

	//landHereOrGoHome should be implemented here
	//maybe we should change the Game so that every Token has four designated HomeSquares
	//if not the landHereOrGoHome Method has to evaluate too much (?)

    public Square moveAndLand(Token token, int moves){
    	return board.findSquare(this, token, moves).landHereSendHome();
	}

	public Square landHereSendHome() {

	    if(this.token != null){
	        sendTokenHome(this.token);
        }
		return this;

	}

	public void sendTokenHome(Token token){
        this.leave(token);
	    token.getHomeSquare().enter(token);
	    token.setSquare(token.getHomeSquare());
    }

	public boolean isFinishingLineSquare(){
		return false;
	}
	
    public boolean isHomeSquare() {
    	return false;
    }
    
    public boolean isGoalSquare() {
    	return false;
    }
    
    public boolean isStarSquare() {
    	return false;
    }
    
    public boolean isEnterFinishingLineSquare() {
    	return false;
    }

	public String squareLabel() {
		if(this.token == null) {
			return "##";
		} else {
			return this.token + "";
		}
	}

	public char getPlayer(){
		return ' '; 
	}

	public boolean isEmpty(){
		return this.token == null ? true : false;
	}
	
	public boolean isPlayersStartSquare(char playerLetter){
		return false;
	}



}
