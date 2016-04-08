package board;

import pieces.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class chess {
	private static int turn;
	private static String player;
	private static piece[][] board;
	private static final Map<Character, Integer> piecePoints = new HashMap<>();

	public static void reset() {
		// reset the state of the game / your internal variables - note that this function is highly dependent on your implementation
		turn = 1;
		player = "W";
		board = new piece[][]{
				{new rook(0, 0, true), new knight(1, 0, true), new bishop(2, 0, true), new queen(3, 0, true), new king(4, 0, true)},
				{new pawn(0, 1, true), new pawn(1, 1, true), new pawn(2, 1, true), new pawn(3, 1, true), new pawn(4, 1, true)},
				{new empty(0, 2), new empty(1, 2), new empty(2, 2), new empty(3, 2), new empty(4, 2)},
				{new empty(0, 3), new empty(1, 3), new empty(2, 3), new empty(3, 3), new empty(4, 3)},
				{new pawn(0, 4, false), new pawn(1, 4, false), new pawn(2, 4, false), new pawn(3, 4, false), new pawn(4, 4, false)},
				{new king(0, 5, false), new queen(1, 5, false), new bishop(2, 5, false), new knight(3, 5, false), new rook(4, 5, false)},
		};
	}
	
	public static String boardGet() {
		// return the state of the game - one example is given below - note that the state has exactly 40 or 41 characters
		String strOut = "";

		strOut += Integer.toString(turn);
		strOut += " ";
		strOut += player + "\n";
		for (int y=board.length-1; y>=0; --y) {
			for (int x=0; x<board[0].length; ++x) {
				strOut += board[y][x].getChar();
			}
			strOut += '\n';
		}

		return strOut;
	}
	
	public static void boardSet(String strIn) {
		// read the state of the game from the provided argument and set your internal variables accordingly - note that the state has exactly 40 or 41 characters
		String[] strLines = strIn.split("\n");
		String[] line1 = strLines[0].split(" ");
		Vector<String> boardLines = new Vector<>();
		boardLines.add(strLines[6]);
		boardLines.add(strLines[5]);
		boardLines.add(strLines[4]);
		boardLines.add(strLines[3]);
		boardLines.add(strLines[2]);
		boardLines.add(strLines[1]);

		turn = Integer.parseInt(line1[0]);
		player = line1[1];

		char toConsider;

		for (int y=5; y>=0; --y) {
			for (int x=0; x<strLines[1].length(); ++x) {
				toConsider = boardLines.get(y).charAt(x);
				switch (toConsider) {
					case 'P':
					case 'p': board[y][x] = new pawn(x, y, Character.isUpperCase(toConsider)); break;
					case 'K':
					case 'k': board[y][x] = new king(x, y, Character.isUpperCase(toConsider)); break;
					case 'Q':
					case 'q': board[y][x] = new queen(x, y, Character.isUpperCase(toConsider)); break;
					case 'B':
					case 'b': board[y][x] = new bishop(x, y, Character.isUpperCase(toConsider)); break;
					case 'N':
					case 'n': board[y][x] = new knight(x, y, Character.isUpperCase(toConsider)); break;
					case 'R':
					case 'r': board[y][x] = new rook(x, y, Character.isUpperCase(toConsider)); break;
					case '.': board[y][x] = new empty(x, y); break;
				}
			}
		}
	}
	
	public static char winner() {
		// determine the winner of the current state of the game and return '?' or '=' or 'W' or 'B' - note that we are returning a character and not a string
		if (turn > 40) {
			return '=';
		}

		int kings = 0;

		for (int x=0; x < board.length; ++x) {
			for (int y=0; y < board[0].length; ++y) {
				kings = (board[x][y].getChar() == 'K') ? kings + 1 : kings;
				kings = (board[x][y].getChar() == 'k') ? kings - 1 : kings;
			}
		}

		if (kings > 0) {
			return 'W';
		} else if (kings < 0) {
			return 'B';
		}

		return '?';
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

	public static boolean isEnemy(int x, int y) { return isEnemy(board[y][x].getChar()); }
	public static boolean isEnemy(char charPiece) {
		// with reference to the state of the game, return whether the provided argument is a pieces.piece from the side not on pieces.move - note that we could but should not use the other is() functions in here but probably

		boolean upper = Character.isUpperCase(charPiece);
		return ((player.equals("W") && !upper) || (player.equals("B") && upper)) && charPiece != '.';
	}

	public static boolean isOwn(int x, int y) { return isOwn(board[y][x].getChar()); }
	public static boolean isOwn(char charPiece) {
		// with reference to the state of the game, return whether the provided argument is a pieces.piece from the side on pieces.move - note that we could but should not use the other is() functions in here but probably

		boolean upper = Character.isUpperCase(charPiece);
		return ((player.equals("W") && upper) || (player.equals("B") && !upper)) && charPiece != '.';
	}

	public static boolean isNothing(int x, int y) { return isNothing(board[y][x].getChar()); }
	public static boolean isNothing(char charPiece) {
		// return whether the provided argument is not a pieces.piece / is an empty field - note that we could but should not use the other is() functions in here but probably

		return charPiece == '.';
	}
	
	public static int eval() {
		// with reference to the state of the game, return the the evaluation score of the side on pieces.move - note that positive means an advantage while negative means a disadvantage
		float sum = 0;

		for (int y=board.length-1; y>=0; --y) {
			for (int x=0; x<board[0].length; ++x) {
				if (player.equals("W")) {
					if (Character.isUpperCase(board[y][x].getChar())) {
						sum += board[y][x].getValue();
					} else {
						sum -= board[y][x].getValue();
					}
				} else {
					if (Character.isUpperCase(board[y][x].getChar())) {
						sum -= board[y][x].getValue();
					} else {
						sum += board[y][x].getValue();
					}
				}
			}
		}

		return (int) sum;
	}
	
	public static Vector<String> moves() {
		// with reference to the state of the game and return the possible moves - one example is given below - note that a pieces.move has exactly 6 characters

		Vector<String> strOut = new Vector<String>();
		Vector<move> toConsider = null;

		for (int y=board.length-1; y>=0; --y) {
			for (int x=0; x<board[0].length; ++x) {
				if (player.equals("W") && Character.isUpperCase(board[y][x].getChar())) {
					toConsider = board[y][x].possibleMoves();
					for (move m : toConsider) {
						strOut.add(m.toString());
					}
				} else if (player.equals("B") && Character.isLowerCase(board[y][x].getChar())) {
					toConsider = board[y][x].possibleMoves();
					for (move m : toConsider) {
						strOut.add(m.toString());
					}
				}
			}
		}

		return strOut;
	}
	
	public static Vector<String> movesShuffled() {
		// with reference to the state of the game, determine the possible moves and shuffle them before returning them - note that you can call the board.chess.moves() function in here
		
		return new Vector<String>();
	}
	
	public static Vector<String> movesEvaluated() {
		// with reference to the state of the game, determine the possible moves and sort them in order of an increasing evaluation score before returning them - note that you can call the board.chess.moves() function in here
		
		return new Vector<String>();
	}

	public static void move(String charIn) {
		// perform the supplied pieces.move (for example "a5-a4\n") and update the state of the game / your internal variables accordingly - note that it advised to do a sanity check of the supplied pieces.move

		int xStart = charIn.charAt(0) - 'a';
		int xEnd = charIn.charAt(3) - 'a';
		int yStart = Character.getNumericValue(charIn.charAt(1)) - 1;
		int yEnd = Character.getNumericValue(charIn.charAt(4)) - 1;

		char piece = board[yStart][xStart].getChar();
		if (isEnemy(piece)) return;

		Vector<move> moves = board[yStart][xStart].possibleMoves();
		int xDiff = xEnd - xStart;
		int yDiff = yEnd - yStart;
		move givenMove = new move(xStart, yStart, xDiff, yDiff);

		Vector<String> moveStrings = new Vector<>(moves.size());
		for (move m: moves) {
			moveStrings.add(m.toString());
		}

		if (moveStrings.contains(givenMove.toString())) {
			board[yStart][xStart].move(xDiff, yDiff);
			board[yEnd][xEnd] = board[yStart][xStart];
			board[yStart][xStart] = new empty(xStart, yStart);
			// Special case: pawn promotion
			if (Character.toLowerCase(piece) == 'p') {
				if (yEnd == 5 && player.equals("W")) {
					board[yEnd][xEnd] = new queen(xEnd, yEnd, true);
				} else if (yEnd == 0 && player.equals("B")) {
					board[yEnd][xEnd] = new queen(xEnd, yEnd, false);
				}
			}
			turn += player.equals("W") ? 0 : 1;
			player = (player.equals("W")) ? "B" : "W";
		}
	}
	
	public static String moveRandom() {
		// perform a random pieces.move and return it - one example output is given below - note that you can call the board.chess.movesShuffled() function as well as the board.chess.pieces.move() function in here
		
		return "a2-a3\n";
	}
	
	public static String moveGreedy() {
		// perform a greedy pieces.move and return it - one example output is given below - note that you can call the board.chess.movesEvaluated() function as well as the board.chess.pieces.move() function in here
		
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