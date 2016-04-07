import board.board;

import java.util.Vector;

public class chess {

	public static void reset() {
		// reset the state of the game / your internal variables - note that this function is highly dependent on your implementation
		board.reset();
	}
	
	public static String boardGet() {
		// return the state of the game - one example is given below - note that the state has exactly 40 or 41 characters
		return board.getBoardStr();
	}
	
	public static void boardSet(String strIn) {
		// read the state of the game from the provided argument and set your internal variables accordingly - note that the state has exactly 40 or 41 characters
		board.setBoard(strIn);
	}
	
	public static char winner() {
		// determine the winner of the current state of the game and return '?' or '=' or 'W' or 'B' - note that we are returning a character and not a string
		return board.winner();
	}
	
	public static boolean isValid(int intX, int intY) {
		if (intX < 0) {
			return false;
			
		} else if (intX > 4) {
			return false;
			
		}
		
		if (intY < 0) {
			return false;
			
		} else if (intY > 5) {
			return false;
			
		}
		
		return true;
	}
	
	public static boolean isEnemy(char charPiece) {
		// with reference to the state of the game, return whether the provided argument is a pieces.piece from the side not on pieces.move - note that we could but should not use the other is() functions in here but probably

		return board.isEnemy(charPiece);
	}
	
	public static boolean isOwn(char charPiece) {
		// with reference to the state of the game, return whether the provided argument is a pieces.piece from the side on pieces.move - note that we could but should not use the other is() functions in here but probably

		return board.isOwn(charPiece);
	}
	
	public static boolean isNothing(char charPiece) {
		// return whether the provided argument is not a pieces.piece / is an empty field - note that we could but should not use the other is() functions in here but probably

		return board.isNothing(charPiece);
	}
	
	public static int eval() {
		// with reference to the state of the game, return the the evaluation score of the side on pieces.move - note that positive means an advantage while negative means a disadvantage
		
		return 0;
	}
	
	public static Vector<String> moves() {
		// with reference to the state of the game and return the possible moves - one example is given below - note that a pieces.move has exactly 6 characters
		
		return board.moves();
	}
	
	public static Vector<String> movesShuffled() {
		// with reference to the state of the game, determine the possible moves and shuffle them before returning them - note that you can call the chess.moves() function in here
		
		return new Vector<String>();
	}
	
	public static Vector<String> movesEvaluated() {
		// with reference to the state of the game, determine the possible moves and sort them in order of an increasing evaluation score before returning them - note that you can call the chess.moves() function in here
		
		return new Vector<String>();
	}
	
	public static void move(String charIn) {
		// perform the supplied pieces.move (for example "a5-a4\n") and update the state of the game / your internal variables accordingly - note that it advised to do a sanity check of the supplied pieces.move

		board.move(charIn);
	}
	
	public static String moveRandom() {
		// perform a random pieces.move and return it - one example output is given below - note that you can call the chess.movesShuffled() function as well as the chess.pieces.move() function in here
		
		return "a2-a3\n";
	}
	
	public static String moveGreedy() {
		// perform a greedy pieces.move and return it - one example output is given below - note that you can call the chess.movesEvaluated() function as well as the chess.pieces.move() function in here
		
		return "a2-a3\n";
	}
	
	public static String moveNegamax(int intDepth, int intDuration) {
		// perform a negamax pieces.move and return it - one example output is given below - note that you can call the the other functions in here
		
		return "a2-a3\n";
	}
	
	public static String moveAlphabeta(int intDepth, int intDuration) {
		// perform a alphabeta pieces.move and return it - one example output is given below - note that you can call the the other functions in here
		
		return "a2-a3\n";
	}
	
	public static void undo() {
		// undo the last pieces.move and update the state of the game / your internal variables accordingly - note that you need to maintain an internal variable that keeps track of the previous history for this
	}
}