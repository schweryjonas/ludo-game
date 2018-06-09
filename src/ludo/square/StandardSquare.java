package ludo.square;


import ludo.Board;

/** 
 * The StandardSquare is a Square that is part of the general path of the players
 * tokens. 
 * 
 * Its special responsibilities are that tokens of every player can enter and leave it.
 */

public class StandardSquare extends Square {
	
	public StandardSquare(Board board, int rowPosition, int colPosition) {
		super(board, rowPosition, colPosition);
	}


	@Override
    public String squareLabel() {

        if(this.token == null)
            return "--";
        else
            return this.token + "";
    }


    public boolean isPlayersStartSquare(char playerLetter){
            return false;
    }
    @Override
    public boolean isEmpty(){

        if(this.token == null)
            return true;
        else
            return false;

    }

}
