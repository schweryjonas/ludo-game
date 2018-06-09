package ludo.square;

import ludo.Board;
import ludo.Token;

/**
 * The EnterFinishingLineSquare is a Square that is special for
 * every player in the Game. Namely, it is the Square before a 
 * players token enters the FinishingLine. 
 * 
 * The responsibility of this Square is that every player changes 
 * his path individually to reach the GoalSquare. 
 */
public class EnterFinishLineSquare extends Square {

    private char player;

    public EnterFinishLineSquare(Board board, int rowPosition, int colPosition, char player){
        super(board,rowPosition,colPosition);
        this.player = player;
    }
    
    @Override
    public boolean isEnterFinishLineSquare() {

        return true;
    }

    public char isPlayersEnterFinishLineSquare(char playerLetter){
        return this.player;
    }


    public Square get(){
        return this;
    }

    @Override
    public String squareLabel() {

        if(this.token == null)
            return "--";
        else
            return this.token + "";
    }
}

