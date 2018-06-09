package ludo;
import ludo.square.*;
import java.util.*;

/**
 * Represents the Board of the Ludo Game.
 * 
 * The responsibilities of the Board are setting all the squares
 * in the right position of the 2d representation of the board,
 * and providing the general path of the Ludo game each player 
 * can move its tokens on. 
 */

public class Board {

	private String[][] board;
	private static final int SIZE = 15;
	private List<Square> path;
	private List<Square> squares;
	private List<Square> FinishLineA;
	private List<Square> FinishLineB;
	private List<Square> FinishLineC;
	private List<Square> FinishLineD;

	/**
	 * Initializes the board with all required squares and the general path
	 */
	public Board() {
		this.board = initializeBoard();
		addSquares();
		setStarSquare();
		setHomeSquares();
		setFinishingLineSquare();
		setEnterFinishLineSquare();
		setGoalSquare();
		setStandardSquare();
		path();
		invariant();
		FinishLineA();
		FinishLineB();
		FinishLineC();
		FinishLineD();
	}

	private boolean invariant() {
		return squares.size() >= 0
				&& board.length == 15
				&& path.size() >= 0;
	}

	/**
	 * Initializes the string representation of the board. Will be transferred
	 * to the BoardRenderer Class in a later stage.
	 *
	 * @return 2d string array (15x15)
	 */
	public String[][] initializeBoard() {
		String[][] board = new String[SIZE][SIZE];
		assert board.length != 0;
		return board;
	}

	/**
	 * Getter Method for the String Array Board
	 *
	 * @return
	 */
	public String[][] getBoard() {
		return this.board;
	}

	/**
	 * To String method to represent the Board as a String, so the String output is
	 * the SquareLabel.
	 *
	 * @param rowPosition
	 * @param colPosition
	 * @return String of each Square in the Board
	 */
	public String toString(int rowPosition, int colPosition) {
		return (getSquareViaBoardPosition(rowPosition, colPosition).squareLabel());
	}

	/**
	 * Represents the length of the board which is given and
	 * does not change during the game
	 *
	 * @return 15
	 */
	public int getLength() {
		return SIZE;
	}

	/**
	 * Finds the Square to move on to with the LinkedList Path. When the End of the List is reached
	 * it goes to the First Square of the List.
	 * If the startSquare is a FinishingLineSquare it moves to the Method findFinishLineSquare directly.
	 * if one of the Squares passing is a EnterFinishLineSquare it moves to the findGoalSquare method with the remaining
	 * moves.
	 *
	 * @param square StartSquare
	 * @param moves
	 * @return square, where the player lands on
	 */


	public Square findSquare(Square square, Token token, int moves) {

		Square landSquare = square;
		int indexOfSquare = path.indexOf(square);


		if (square.isFinishingLineSquare()) {
			landSquare = findGoalSquare(square, token, moves);

		}
		if (path.contains(square)) {

			for (int i = 0; i < moves; i++) {
				if (indexOfSquare < path.size() -1) {
					if (landSquare.equals(token.getEnterFinishLineSquare())) {
						return findFinishLineSquare(landSquare, token, moves - i);
					}
					indexOfSquare++;
				}
				else {

					indexOfSquare = 0;

				}
				landSquare = path.get(indexOfSquare);

			}
		}
		return landSquare;

	}
	/**
	 * This Method gets called when a token already is on a finishingLineSquare when moving and returns
	 * the the Square to move to.
	 * @param square start
	 * @param token that is moving
	 * @param moves the moves to make
	 * @return landsquare
	 */

	public Square findFinishLineSquare(Square square, Token token, int moves){

		List<Square> finishLine = getFinishLinePath(token);

		return finishLine.get(moves);

	}

	/**
	 * This Method gets called by findSquare if a token is on the finishLine and could move to the goalSquare
	 * this method checks if the token moves directly to the GoalSquare or stays on it's start Square.
	 * Since a Token can only move to the GoalSquare when he rolls the exact right number this Method only returns
	 * the goalsquare when it is reached by moves.
	 *
	 * @param square startSquare
	 * @param token
	 * @param moves
	 * @return either the goalSquare or the startSquare
	 */

	public Square findGoalSquare(Square square, Token token, int moves){

		List<Square> finishLine = getFinishLinePath(token);
		int i = finishLine.indexOf(square);

		if(i + moves == finishLine.size()-1){
			return finishLine.get(6); //last Element or GoalSquare;
		}
		else{
			return square;
		}
	}

	/**
	 * Returns all the StarSquares which serve as a starting point in the path
	 * and every player has his own starting point on the general path.
	 *
	 * @param square
	 * @param playerLetter
	 * @return
	 */

	public Square getPlayersStartSquare(Square square, char playerLetter) {

		Square startSquare = square;
		List<Square> starSquares = new LinkedList<>();
		for (Square s : getSquaresList()) {
			if (s.isStarSquare()) {
				starSquares.add(s);
			}
		}
		for (Square s : starSquares) {
			if (s.getPlayer() == playerLetter) {
				startSquare = s;
			}
		}
		return startSquare;
	}

	/**
	 * Returns a square on a specific position
	 *
	 * @param position in the LinkedList
	 * @return square on the position indicated by the parameter
	 */
	public Square getSquare(int position) {
		return squares.get(position);
	}

	/**
	 * Added a getSquareViaBoardPosition, so that we can access the Square via it's board Position that can be accessed via int row and int col
	 *
	 * @param row int row the Board
	 * @param col int column the Board
	 * @return the Index of the SquareList of this particular BoardPosition by a simple calculation
	 */
	private Square getSquareViaBoardPosition(int row, int col) {
		int ListIndex = (row * 15) + col;
		return getSquare(ListIndex);
	}

	/**
	 * Initializes all Board positions as a FillSquare.
	 */
	private void addSquares() {
		this.squares = new LinkedList<>();
		for (int i = 0; i < getLength(); i++) {
			for (int j = 0; j < getLength(); j++) {
				Square square = new FillSquare(this, i, j);
				this.squares.add(square);
			}
		}
	}

	/**
	 * Getter method to retrieve the LinkedList of squares
	 *
	 * @return LinkedList with all the squares
	 */
	public List<Square> getSquaresList() {
		return this.squares;
	}

	/**
	 * Sets the position of a specific square in the LinkedList of squares
	 *
	 * @param position  in the LinkedList
	 * @param newSquare that will be on the specified position
	 */
	private void setSquares(int position, Square newSquare) {
		this.squares.set(position, newSquare);
	}

	/**
	 * Adds all the squares on which a players token can land during the game
	 * to a general path which is represented as a LinkedList
	 */
	private void path() {
		path = new LinkedList<>();
		path.add(getSquare(91));
		path.add(getSquare(92));
		path.add(getSquare(93));
		path.add(getSquare(94));
		path.add(getSquare(95));
		path.add(getSquare(81));
		path.add(getSquare(66));
		path.add(getSquare(51));
		path.add(getSquare(36));
		path.add(getSquare(21));
		path.add(getSquare(6));
		path.add(getSquare(7));
		path.add(getSquare(8));
		path.add(getSquare(23));
		path.add(getSquare(38));
		path.add(getSquare(53));
		path.add(getSquare(68));
		path.add(getSquare(83));
		path.add(getSquare(99));
		path.add(getSquare(100));
		path.add(getSquare(101));
		path.add(getSquare(102));
		path.add(getSquare(103));
		path.add(getSquare(104));
		path.add(getSquare(119));
		path.add(getSquare(134));
		path.add(getSquare(133));
		path.add(getSquare(132));
		path.add(getSquare(131));
		path.add(getSquare(130));
		path.add(getSquare(129));
		path.add(getSquare(143));
		path.add(getSquare(158));
		path.add(getSquare(173));
		path.add(getSquare(188));
		path.add(getSquare(203));
		path.add(getSquare(218));
		path.add(getSquare(217));
		path.add(getSquare(216));
		path.add(getSquare(201));
		path.add(getSquare(186));
		path.add(getSquare(171));
		path.add(getSquare(156));
		path.add(getSquare(141));
		path.add(getSquare(125));
		path.add(getSquare(124));
		path.add(getSquare(123));
		path.add(getSquare(122));
		path.add(getSquare(121));
		path.add(getSquare(120));
		path.add(getSquare(105));
		path.add(getSquare(90));
	}


	/**
	 * Getter method for the path the player tokens can land on
	 *
	 * @return the path as a LinkedList
	 */
	public List<Square> getPath() {
		return this.path;
	}


	/**
	 * Sets the HomeSquares of the players in the right place in the
	 * board. These are the starting points of each players tokens
	 */
	private void setHomeSquares() {

		setSquares(31, new HomeSquare(this, 2, 1, 'A'));
		setSquares(32, new HomeSquare(this, 2, 2, 'A'));
		setSquares(46, new HomeSquare(this, 3, 1, 'A'));
		setSquares(47, new HomeSquare(this, 3, 2, 'A')); //A

		setSquares(43, new HomeSquare(this, 2, 13, 'C'));
		setSquares(42, new HomeSquare(this, 2, 12, 'C'));
		setSquares(58, new HomeSquare(this, 3, 13, 'C'));
		setSquares(57, new HomeSquare(this, 3, 12, 'C')); //C

		setSquares(178, new HomeSquare(this, 11, 13, 'B'));
		setSquares(177, new HomeSquare(this, 11, 12, 'B'));
		setSquares(193, new HomeSquare(this, 12, 13, 'B'));
		setSquares(192, new HomeSquare(this, 12, 12, 'B')); //B

		setSquares(166, new HomeSquare(this, 11, 1, 'D'));
		setSquares(167, new HomeSquare(this, 11, 2, 'D'));
		setSquares(181, new HomeSquare(this, 12, 1, 'D'));
		setSquares(182, new HomeSquare(this, 12, 2, 'D')); //D
	}


	/**
	 * Sets the HomeSquares of the players in the right place in the
	 * board.
	 * They are represented as ** on the board.
	 */
	private void setStarSquare() {

		setSquares(91, new StarSquare(this, 6, 1, 'A')); //A
		setSquares(133, new StarSquare(this, 8, 13, 'B')); //B
		setSquares(23, new StarSquare(this, 1, 8, 'C')); //C
		setSquares(201, new StarSquare(this, 13, 6, 'D')); //D
	}

	/**
	 * Sets the FinishingLineSquares of each player in the right position
	 * They are represented as || in the board.
	 */
	private void setFinishingLineSquare() {

		setSquares(22, new FinishingLineSquare(this, 1, 7)); //A
		setSquares(37, new FinishingLineSquare(this, 2, 7));
		setSquares(52, new FinishingLineSquare(this, 3, 7));
		setSquares(67, new FinishingLineSquare(this, 4, 7));
		setSquares(82, new FinishingLineSquare(this, 5, 7));

		setSquares(142, new FinishingLineSquare(this, 13, 7)); //B
		setSquares(157, new FinishingLineSquare(this, 12, 7));
		setSquares(172, new FinishingLineSquare(this, 11, 7));
		setSquares(187, new FinishingLineSquare(this, 10, 7));
		setSquares(202, new FinishingLineSquare(this, 9, 7));

		setSquares(118, new FinishingLineSquare(this, 7, 13)); //C
		setSquares(117, new FinishingLineSquare(this, 7, 12));
		setSquares(116, new FinishingLineSquare(this, 7, 11));
		setSquares(115, new FinishingLineSquare(this, 7, 10));
		setSquares(114, new FinishingLineSquare(this, 7, 9));

		setSquares(106, new FinishingLineSquare(this, 7, 1)); //D
		setSquares(107, new FinishingLineSquare(this, 7, 2));
		setSquares(108, new FinishingLineSquare(this, 7, 3));
		setSquares(109, new FinishingLineSquare(this, 7, 4));
		setSquares(110, new FinishingLineSquare(this, 7, 5));
	}

	/**
	 * Sets the GoalSquares of each player in the right position
	 * They are represented as $$ in the board.
	 */
	private void setGoalSquare() {
		setSquares(111, new GoalSquare(this, 7, 6)); //A
		setSquares(113, new GoalSquare(this, 7, 8)); //B
		setSquares(97, new GoalSquare(this, 6, 7)); //C
		setSquares(127, new GoalSquare(this, 8, 7)); //D
	}

	/**
	 * Sets the FinishingLineSquares of each player in the right position
	 * They are represented as -- in the board.
	 */
	private void setEnterFinishLineSquare() {
		setSquares(105, new EnterFinishLineSquare(this, 7, 0, 'A')); //A
		setSquares(119, new EnterFinishLineSquare(this, 7, 14, 'B')); //B
		setSquares(7, new EnterFinishLineSquare(this, 0, 7, 'C')); //C
		setSquares(217, new EnterFinishLineSquare(this, 14, 7, 'D')); //D
	}

	/**
	 * Sets the StandardSquares of each player in the right position
	 * They are represented as -- in the board.
	 */
	private void setStandardSquare() {
		setSquares(6, new StandardSquare(this, 0, 6));
		setSquares(8, new StandardSquare(this, 0, 8));
		setSquares(21, new StandardSquare(this, 1, 6));
		setSquares(36, new StandardSquare(this, 2, 6));
		setSquares(38, new StandardSquare(this, 2, 8));
		setSquares(51, new StandardSquare(this, 3, 6));
		setSquares(53, new StandardSquare(this, 3, 8));
		setSquares(66, new StandardSquare(this, 4, 6));
		setSquares(68, new StandardSquare(this, 4, 8));
		setSquares(81, new StandardSquare(this, 5, 6));
		setSquares(83, new StandardSquare(this, 5, 8));
		setSquares(90, new StandardSquare(this, 6, 0));
		setSquares(92, new StandardSquare(this, 6, 2));
		setSquares(93, new StandardSquare(this, 6, 3));
		setSquares(94, new StandardSquare(this, 6, 4));
		setSquares(95, new StandardSquare(this, 6, 5));
		setSquares(99, new StandardSquare(this, 6, 9));
		setSquares(100, new StandardSquare(this, 6, 10));
		setSquares(101, new StandardSquare(this, 6, 11));
		setSquares(102, new StandardSquare(this, 6, 12));
		setSquares(103, new StandardSquare(this, 6, 13));
		setSquares(104, new StandardSquare(this, 6, 14));
		setSquares(120, new StandardSquare(this, 8, 0));
		setSquares(121, new StandardSquare(this, 8, 1));
		setSquares(122, new StandardSquare(this, 8, 2));
		setSquares(123, new StandardSquare(this, 8, 3));
		setSquares(124, new StandardSquare(this, 8, 4));
		setSquares(125, new StandardSquare(this, 8, 5));
		setSquares(129, new StandardSquare(this, 8, 9));
		setSquares(130, new StandardSquare(this, 8, 10));
		setSquares(131, new StandardSquare(this, 8, 11));
		setSquares(132, new StandardSquare(this, 8, 12));
		setSquares(134, new StandardSquare(this, 8, 14));
		setSquares(141, new StandardSquare(this, 9, 6));
		setSquares(143, new StandardSquare(this, 9, 8));
		setSquares(156, new StandardSquare(this, 10, 6));
		setSquares(158, new StandardSquare(this, 10, 8));
		setSquares(171, new StandardSquare(this, 11, 6));
		setSquares(173, new StandardSquare(this, 11, 8));
		setSquares(186, new StandardSquare(this, 12, 6));
		setSquares(188, new StandardSquare(this, 12, 8));
		setSquares(203, new StandardSquare(this, 13, 8));
		setSquares(216, new StandardSquare(this, 14, 6));
		setSquares(218, new StandardSquare(this, 14, 8));
	}

	/**
	 * checks for the right FinishLinePath and returns the one matching the Token i.e. the Player.
	 * @param token the token that requests his FinishLine
	 * @return the List of the right FinishLinePath
	 */

	public List<Square> getFinishLinePath(Token token) {

		switch (token.getTokenLetter()) {
			case 'A':
				return this.FinishLineA;
			case 'B':
				return this.FinishLineB;
			case 'C':
				return this.FinishLineC;
			case 'D':
				return this.FinishLineD;
			default:
				throw new IllegalArgumentException("Invalid Player: " + token.getTokenLetter());
		}

	}

	/**
	 * initializes the 4 FinishLinePathes (0 = EnterFinishLineSquare, 6 = GoalSquare)
	 */
	private void FinishLineC() {

		FinishLineC = new LinkedList<>();
		FinishLineC.add(getSquare(7));
		FinishLineC.add(getSquare(22));
		FinishLineC.add(getSquare(37));
		FinishLineC.add(getSquare(52));
		FinishLineC.add(getSquare(67));
		FinishLineC.add(getSquare(82));
		FinishLineC.add(getSquare(97));
	}

	private void FinishLineD() {

		FinishLineD = new LinkedList<>();
		FinishLineD.add(getSquare(217));
		FinishLineD.add(getSquare(202));
		FinishLineD.add(getSquare(187));
		FinishLineD.add(getSquare(172));
		FinishLineD.add(getSquare(157));
		FinishLineD.add(getSquare(142));
		FinishLineD.add(getSquare(127));
	}

	private void FinishLineB() {

		FinishLineB = new LinkedList<>();
		FinishLineB.add(getSquare(117));
		FinishLineB.add(getSquare(118));
		FinishLineB.add(getSquare(117));
		FinishLineB.add(getSquare(116));
		FinishLineB.add(getSquare(115));
		FinishLineB.add(getSquare(114));
		FinishLineB.add(getSquare(113));
	}

	private void FinishLineA() {

		FinishLineA = new LinkedList<>();
		FinishLineA.add(getSquare(105));
		FinishLineA.add(getSquare(106));
		FinishLineA.add(getSquare(107));
		FinishLineA.add(getSquare(108));
		FinishLineA.add(getSquare(109));
		FinishLineA.add(getSquare(110));
		FinishLineA.add(getSquare(111));
	}
}
   




