package ludo;

/**
 * Represents a Die that rolls random numbers.
 */

public class Die {
	
	private final int max_faces = 6;
	private int face;
	
	/**
	 * Initializes a new Die with the gives faces
	 * @param face number of faces which has to be greater than zero
	 */
	public Die(int face) {
		assert face > 0;
		this.face = face;
	}
	
	/**
	 * Rolls the Die and calculates a random number
	 * @return face of the die which was randomly calculated by the 
	 *         method and lies between 1 and the constant max_faces
	 */
	public int roll() {
		int face = (int) (Math.random() * max_faces + 1);
		assert face >= 1 && face <= max_faces;
		return face;
	}
	
    /**
     * Getter Method to return the actual face value of the die 
     * @return actual face of the die
     */
	public int getFace() {
		return this.face;
	}
}
