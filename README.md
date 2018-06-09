## Ludo Game

Short description of the implemented rules in the Ludo Game:

	- Every player has four tokens which all have a fixed HomeSquare in the players static area.

	- Players move the tokens clockwise around the board.

	- The player in the left upper corner starts rolling the die.

	- After rolling the die, the player decides randomly which of his tokens he moves along.

	- To bring a token out the HomeSquare, the player has to roll a six.

	- If the current players token lands on a square which is is occupied, the token that was on this
	  square is sent back to his HomeSquare.
	  
	- StarSquare are special squares, where more than one player can land on. That is, if a token lands
	  on a StarSquare which is occupied, no token will be transfered to its HomeSquare. If more than one
	  token is an a square, the square label changes for example to 2T, where 2 stands for the number of
	  tokens and T stands for token.

	- After one round along the board, each player has his own finish line which the tokens have
	  to pass to reach the GoalSquare.

	- If a token lands on the finish line, the token can't be moved until the player rolls the 
	  exact number the token needs to reach the GoalSquare.

	- If a player has all his four tokens in the GoalSquare, this player wins the game! Everytime a new token
	  lands ons its GoalSquare, the square label changes for example to 3A, when player A has 3 tokens on its 
	  GoalSquare and only one token remaining at the board. Logically, if 3 tokens are in the GoalSquare of A,
	  in every next round player A moves the remaining token if possible until it reaches the GoalSquare and 
	  Player A wins or until another Player wins the game.

