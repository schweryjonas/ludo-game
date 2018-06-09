package ludo.render;
import ludo.Board;

/**
 * Renders the game with the specific squares on it as a string 
 * in the console.
 *
 * Is always aware of the actual state of the board in order that 
 * is possible to print every played round with the actual position 
 * of the tokens on it.
 */

public class Renderer {

    private Board board;

    /**
     * Initialize the Renderer with the actual board.
     */
    public Renderer(Board board){
        this.board = board;
        makeBoard();
        invariant();
    }
    
    private boolean invariant() {
    	return board != null;
    }

    /**
     * Gets the board with the actual state and represents
     * it as a string.
     */
    private void makeBoard(){

        String[][] boardToPrint = board.getBoard();
        for(int i = 0; i < boardToPrint.length; i++){
            System.out.println("");
            for(int j = 0; j < boardToPrint[i].length; j++){
                System.out.print(board.toString(i,j));
            }
        }
    }
    
    public String toString(){
        return "";
    }


}
