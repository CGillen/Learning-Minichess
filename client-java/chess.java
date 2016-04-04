import java.util.HashMap;
import java.util.Vector;

import java.lang.Character;

public class chess {
	private static int turn;
	private static String player;
	private static char[][] board;
	private static final HashMap<Character, Integer> piecePoints = new HashMap<>();

	public static void reset() {
		// reset the state of the game / your internal variables - note that this function is highly dependent on your implementation
		turn = 1;
		player = "W";
		board = new char[][] {	"kqbnr\n".toCharArray(),
								"ppppp\n".toCharArray(),
								".....\n".toCharArray(),
								".....\n".toCharArray(),
								"PPPPP\n".toCharArray(),
								"RNBQK\n".toCharArray() };
		piecePoints.put('k', -100);	// Cost of black king
		piecePoints.put('q', -80);	// Cost of black queen
		piecePoints.put('b', -25);	// Cost of black bishop
		piecePoints.put('n', -30);	// Cost of black knight
		piecePoints.put('r', -30);	// Cost of black rook
		piecePoints.put('p', -50);	// Cost of black pawn
		piecePoints.put('K', 100);	// Cost of white king
		piecePoints.put('Q', 80);	// Cost of white queen
		piecePoints.put('B', 25);	// Cost of white bishop
		piecePoints.put('N', 30);	// Cost of white knight
		piecePoints.put('R', 30);	// Cost of white rook
		piecePoints.put('P', 50);	// Cost of white pawn
		piecePoints.put('.', 0);	// Cost of empty spot
		piecePoints.put('\n', 0);	// Cost of newline
	}
	
	public static String boardGet() {
		// return the state of the game - one example is given below - note that the state has exactly 40 or 41 characters
		
		String strOut = "";

		strOut += Integer.toString(turn);
		strOut += " ";
		strOut += player + "\n";
		strOut += new String(board[0]);
		strOut += new String(board[1]);
		strOut += new String(board[2]);
		strOut += new String(board[3]);
		strOut += new String(board[4]);
		strOut += new String(board[5]);

		return strOut;
	}
	
	public static void boardSet(String strIn) {
		// read the state of the game from the provided argument and set your internal variables accordingly - note that the state has exactly 40 or 41 characters

		String[] strLines = strIn.split("\n");
		String[] line1 = strLines[0].split(" ");

		turn = Integer.parseInt(line1[0]);
		player = line1[1];
		board[0] = (strLines[1] + '\n').toCharArray();
		board[1] = (strLines[2] + '\n').toCharArray();
		board[2] = (strLines[3] + '\n').toCharArray();
		board[3] = (strLines[4] + '\n').toCharArray();
		board[4] = (strLines[5] + '\n').toCharArray();
		board[5] = (strLines[6] + '\n').toCharArray();

	}
	
	public static char winner() {
		// determine the winner of the current state of the game and return '?' or '=' or 'W' or 'B' - note that we are returning a character and not a string
		int boardPoints = 0;

		for (int y=0; y<board.length;++y) {
			for (int x=0; x<board[0].length ;++x) {
				boardPoints += piecePoints.get(board[y][x]);
			}
		}

		if (boardPoints > 0) {
			return 'W';
		} else if (boardPoints < 0) {
			return 'B';
		} else {
			return '=';
		}

		//return '?';
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
		// with reference to the state of the game, return whether the provided argument is a piece from the side not on move - note that we could but should not use the other is() functions in here but probably

		boolean upper = Character.isUpperCase(charPiece);
		return ((player.equals("W") && !upper) || (player.equals("B") && upper)) && charPiece != '.';
	}
	
	public static boolean isOwn(char charPiece) {
		// with reference to the state of the game, return whether the provided argument is a piece from the side on move - note that we could but should not use the other is() functions in here but probably

		boolean upper = Character.isUpperCase(charPiece);
		return ((player.equals("W") && upper) || (player.equals("B") && !upper)) && charPiece != '.';
	}
	
	public static boolean isNothing(char charPiece) {
		// return whether the provided argument is not a piece / is an empty field - note that we could but should not use the other is() functions in here but probably
		
		return charPiece == '.';
	}
	
	public static int eval() {
		// with reference to the state of the game, return the the evaluation score of the side on move - note that positive means an advantage while negative means a disadvantage
		
		return 0;
	}
	
	public static Vector<String> moves() {
		// with reference to the state of the game and return the possible moves - one example is given below - note that a move has exactly 6 characters
		
		Vector<String> strOut = new Vector<String>();
		
		strOut.add("a5-a4\n");
		strOut.add("b5-b4\n");
		strOut.add("c5-c4\n");
		strOut.add("d5-d4\n");
		strOut.add("e5-e4\n");
		strOut.add("b6-a4\n");
		strOut.add("b6-c4\n");
		
		return strOut;
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
		// perform the supplied move (for example "a5-a4\n") and update the state of the game / your internal variables accordingly - note that it advised to do a sanity check of the supplied move
	}
	
	public static String moveRandom() {
		// perform a random move and return it - one example output is given below - note that you can call the chess.movesShuffled() function as well as the chess.move() function in here
		
		return "a5-a4\n";
	}
	
	public static String moveGreedy() {
		// perform a greedy move and return it - one example output is given below - note that you can call the chess.movesEvaluated() function as well as the chess.move() function in here
		
		return "a5-a4\n";
	}
	
	public static String moveNegamax(int intDepth, int intDuration) {
		// perform a negamax move and return it - one example output is given below - note that you can call the the other functions in here
		
		return "a5-a4\n";
	}
	
	public static String moveAlphabeta(int intDepth, int intDuration) {
		// perform a alphabeta move and return it - one example output is given below - note that you can call the the other functions in here
		
		return "a5-a4\n";
	}
	
	public static void undo() {
		// undo the last move and update the state of the game / your internal variables accordingly - note that you need to maintain an internal variable that keeps track of the previous history for this
	}
}