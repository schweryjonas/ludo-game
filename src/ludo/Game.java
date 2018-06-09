package ludo;
import ludo.render.Renderer;

import ludo.square.*;
import java.util.*;

/**
 * Represents an instance the the whole Ludo Game.
 * 
 * The responsibilities of the game are providing methods to play the game,
 * keeping track of the player and its tokens.
 */

public class Game {

	private Deque<Player> players;
	private Player winner;
	private Board board;
	List<Square> squares; //@Silas should be private
	
    /**
     * Initialize the game with the number of players. To play the game
     * there have to be minimum 2 and maximum 4 players.
     * @param players attendees of the game
     * @param board, can't be null
     */

	public Game(Deque<Player> players, Board board) {
        this.board = board;
        this.addPlayers(players);
        invariant();
    }

	private boolean invariant() {
		return players != null 
				&& players.size() >= 2 && players.size() <= 4
					&& board.getLength() != 0;
	}
	
	/**
	 * Runs the game until one player has all his four tokens in the GoalSquare.
	 *
	 * Starts the game by letting the player roll the die. In the beginning, only
	 * when a player rolls a 6, he can move a token, else the next player can
	 * roll the die 
	 */
	public void play(Die die) {
		int i = 0;
		while(notOver()){
		    int roll = die.roll();
            this.moveToken(roll);
			Renderer renderer = new Renderer(board); //@Silas you probably don't need to create a new renderer for every roll, just save it as an instance var
			System.out.println(renderer);
            System.out.println("Round: " + (i+1));
            System.out.println("Player " + players.getLast() + " rolled " + roll + "." ) ;
            System.out.println();
            i++;
			}
		System.out.println("Player " + winner + " is the winner! Congrats!");
	}
	
	/**
	 * @return winner, if the game has a winner, null otherwise
	 */
	public boolean notOver() {
		return winner == null;
	}
    
	/**
	 * Indicates if the game has a winner and therefore if the game stops 
	 * or continues.
	 * @return
	 */
	public boolean isOver() {
		return !this.notOver();
	}
	
	/**
	 * Player that currently plays one of his tokens.
	 */
	public Player currentPlayer() {
		return players.peek();
	}
    
	/**
	 * Token that belongs to the current player and that currently 
	 * moves along the board.
	 * @return
	 */
	public Token currentToken() {
		Player currentPlayer = currentPlayer();
		Token currentToken = currentPlayer.getToken();
		return currentToken;
	}

	/**
	 * Moves the token along the board by the number the current player rolled and
	 * lets the token land on the destination square.
	 * @param roll 
	 */
	public void moveToken(int roll) {
	    Token currentToken = currentToken();
		currentToken.move(roll);
		Player currentPlayer = currentPlayer();
		addNextPlayerToFront(currentPlayer);
		if (currentPlayer.winner()) {
			winner = currentPlayer;
		}
	}


	public Player getWinner(){

		return this.winner;
	}
	
	/**
	 * Removes the current player from the front and adds him to the back of the queue.
	 * @param currentPlayer, must not be null
	 */
	public void addNextPlayerToFront(Player currentPlayer) {
		assert currentPlayer != null;
		players.remove(); 
		players.add(currentPlayer); 
	}
    
	/**
	 * Adds all the players to the game.
	 * @param participants the added players, must not be null
	 */
	private void addPlayers(Deque<Player> participants) {
		assert participants != null;
		players = new LinkedList<>();
		players.addAll(participants);
		for (Player player : players) {
			player.joinGame(this);
		}
	}

	/**
	 * Returns the playersList in the current order. Is Only used for Testing.
	 * @return the playerList and their current Order
	 */

	public Deque<Player> getPlayersList(){

		return this.players;
	}

	/**
	 * Adds the HomeSquares of each player to the game. They serve as the
	 * start position of each players tokens.
	 * @return
	 */
	public List<Square> getHomeSquares() {
		List<Square> homeSquares = new LinkedList<Square>();
		for ( Square s : board.getSquaresList()) {
			if(s.isHomeSquare()) {
				homeSquares.add(s);
			}
		}
		return homeSquares;
	}
	
	/**
	 * Serves to be aware of the EnterFinishLineSquare's which are unique
	 * for every player.
	 * @param token, must not be null
	 * @return the Square for every token, on which it has to change from general
	 * 		   path to his individual path to enter the finishing line
	 */
	public Square getEnterFinishLineSquare(Token token){
	assert token != null;
		switch (token.getTokenLetter()) {
			case 'A':
				return board.getSquare(105);
			case 'B':
				return board.getSquare(119);
			case 'C':
				return board.getSquare(7);
			case 'D':
				return board.getSquare(217);
			default:
				throw new IllegalArgumentException("Invalid Player: " + token.getTokenLetter());
		}
	}
	
}





