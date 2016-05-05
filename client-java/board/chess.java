package board;

import pieces.*;

import java.util.*;

public class chess {
	private static int turn;
	private static String player;
	private static Piece[][] board;
	private static Stack<Move> prevMoves = new Stack<>();
	private static double bScore, wScore;

	public static void reset() {
		// reset the state of the game / your internal variables - note that this function is highly dependent on your implementation
		turn = 1;
		player = "W";
		bScore = wScore = 0;
		board = new Piece[][]{
				{new Rook(0, 0, true), new Knight(1, 0, true), new Bishop(2, 0, true), new Queen(3, 0, true), new King(4, 0, true)},
				{new Pawn(0, 1, true), new Pawn(1, 1, true), new Pawn(2, 1, true), new Pawn(3, 1, true), new Pawn(4, 1, true)},
				{new Empty(0, 2), new Empty(1, 2), new Empty(2, 2), new Empty(3, 2), new Empty(4, 2)},
				{new Empty(0, 3), new Empty(1, 3), new Empty(2, 3), new Empty(3, 3), new Empty(4, 3)},
				{new Pawn(0, 4, false), new Pawn(1, 4, false), new Pawn(2, 4, false), new Pawn(3, 4, false), new Pawn(4, 4, false)},
				{new King(0, 5, false), new Queen(1, 5, false), new Bishop(2, 5, false), new Knight(3, 5, false), new Rook(4, 5, false)}
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
					case 'p': board[y][x] = new Pawn(x, y, Character.isUpperCase(toConsider)); break;
					case 'K':
					case 'k': board[y][x] = new King(x, y, Character.isUpperCase(toConsider)); break;
					case 'Q':
					case 'q': board[y][x] = new Queen(x, y, Character.isUpperCase(toConsider)); break;
					case 'B':
					case 'b': board[y][x] = new Bishop(x, y, Character.isUpperCase(toConsider)); break;
					case 'N':
					case 'n': board[y][x] = new Knight(x, y, Character.isUpperCase(toConsider)); break;
					case 'R':
					case 'r': board[y][x] = new Rook(x, y, Character.isUpperCase(toConsider)); break;
					case '.': board[y][x] = new Empty(x, y); break;
				}
			}
		}

		evalRaw();
	}
	
	public static char winner() {
		// determine the winner of the current state of the game and return '?' or '=' or 'W' or 'B' - note that we are returning a character and not a string
		int kings = 0;

		for (int x=0; x < board.length; ++x) {
			for (int y=0; y < board[0].length; ++y) {
				kings = (board[x][y].getChar() == 'K') ? kings + 1 : kings;
				kings = (board[x][y].getChar() == 'k') ? kings - 1 : kings;
			}
		}

		if (turn > 40 && kings == 0) {
			return '=';
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
		// with reference to the state of the game, return whether the provided argument is a pieces.Piece from the side not on pieces.Move - note that we could but should not use the other is() functions in here but probably

		boolean upper = Character.isUpperCase(charPiece);
		return ((player.equals("W") && !upper) || (player.equals("B") && upper)) && charPiece != '.';
	}

	public static boolean isOwn(int x, int y) { return isOwn(board[y][x].getChar()); }
	public static boolean isOwn(char charPiece) {
		// with reference to the state of the game, return whether the provided argument is a pieces.Piece from the side on pieces.Move - note that we could but should not use the other is() functions in here but probably

		boolean upper = Character.isUpperCase(charPiece);
		return ((player.equals("W") && upper) || (player.equals("B") && !upper)) && charPiece != '.';
	}

	public static boolean isNothing(int x, int y) { return isNothing(board[y][x].getChar()); }
	public static boolean isNothing(char charPiece) {
		// return whether the provided argument is not a pieces.Piece / is an Empty field - note that we could but should not use the other is() functions in here but probably

		return charPiece == '.';
	}

	public static int eval() {
	//	return evalRaw();
		return player.equals("W") ? (int)(wScore - bScore) : (int)(bScore - wScore);
	}

	public static int evalRaw() {
		// with reference to the state of the game, return the the evaluation score of the side on pieces.Move - note that positive means an advantage while negative means a disadvantage
		int bSum = 0;
		int wSum = 0;
		int score = 0;

		for (int y=board.length-1; y>=0; --y) {
			for (int x=0; x<board[0].length; ++x) {
				if (Character.isUpperCase(board[y][x].getChar())) {
					wSum += board[y][x].getValue(x, y, true);
				} else {
					bSum += board[y][x].getValue(x, y, false);
				}
			}
		}

		score = player.equals("W") ? wSum - bSum : bSum - wSum;
		bScore = bSum;
		wScore = wSum;

		return score;
	}

	public static Vector<String> moves() {
		// with reference to the state of the game and return the possible moves - one example is given below - note that a pieces.Move has exactly 6 characters

		Vector<String> strOut = new Vector<String>();
		Vector<Move> toConsider = null;

		for (int y=board.length-1; y>=0; --y) {
			for (int x=0; x<board[0].length; ++x) {
				if (player.equals("W") && Character.isUpperCase(board[y][x].getChar())) {
					toConsider = board[y][x].possibleMoves(x, y);
					for (Move m : toConsider) {
						strOut.add(m.toString());
					}
				} else if (player.equals("B") && Character.isLowerCase(board[y][x].getChar())) {
					toConsider = board[y][x].possibleMoves(x, y);
					for (Move m : toConsider) {
						strOut.add(m.toString());
					}
				}
			}
		}

		return strOut;
	}

	public static Vector<ScoredMove> movesScored() {
		// with reference to the state of the game and return the possible moves with the eval score of the board after the move

		Vector<ScoredMove> moves = new Vector<ScoredMove>();
		Vector<Move> toConsider = null;
		int score;

		for (int y=board.length-1; y>=0; --y) {
			for (int x=0; x<board[0].length; ++x) {
				if (player.equals("W") && Character.isUpperCase(board[y][x].getChar())) {
					toConsider = board[y][x].possibleMoves(x, y);
					for (Move m : toConsider) {
						move(m.toString());
						score = eval();
						undo();
						moves.add(new ScoredMove(m, score));
					}
				} else if (player.equals("B") && Character.isLowerCase(board[y][x].getChar())) {
					toConsider = board[y][x].possibleMoves(x, y);
					for (Move m : toConsider) {
						move(m.toString());
						score = eval();
						undo();
						moves.add(new ScoredMove(m, score));
					}
				}
			}
		}

		return moves;
	}

	public static Vector<String> movesShuffled() {
		// with reference to the state of the game, determine the possible moves and shuffle them before returning them - note that you can call the board.chess.moves() function in here

		Vector<String> toReturn = moves();
		Collections.shuffle(toReturn);

		return toReturn;
	}

	public static Vector<String> movesEvaluated() {
		// with reference to the state of the game, determine the possible moves and sort them in order of an increasing evaluation score before returning them - note that you can call the board.chess.moves() function in here

		Vector<ScoredMove> toSort = movesScored();
		Vector<String> toReturn = new Vector<String>();

		Collections.shuffle(toSort);

		Collections.sort(toSort, new Comparator<ScoredMove>() {
			@Override
			public int compare(ScoredMove o1, ScoredMove o2) {
				return (int)(o1.score - o2.score);
			}
		});

		for (ScoredMove m : toSort) {
			toReturn.add(m.move.toString());
		}

		return toReturn;
	}

	public static void move(String charIn) {
		// perform the supplied pieces.Move (for example "a5-a4\n") and update the state of the game / your internal variables accordingly - note that it advised to do a sanity check of the supplied pieces.Move

		int xStart = charIn.charAt(0) - 'a';
		int xEnd = charIn.charAt(3) - 'a';
		int yStart = Character.getNumericValue(charIn.charAt(1)) - 1;
		int yEnd = Character.getNumericValue(charIn.charAt(4)) - 1;

		char pieceChar = board[yStart][xStart].getChar();
		Piece piece = board[yStart][xStart];
		if (isEnemy(pieceChar)) return;

		Vector<Move> moves = board[yStart][xStart].possibleMoves(xStart, yStart);
		int xDiff = xEnd - xStart;
		int yDiff = yEnd - yStart;
		Move givenMove = new Move(xStart, yStart, xDiff, yDiff, board[yStart][xStart].getChar(), board[yEnd][xEnd].getChar(), player.equals("W"));

		Vector<String> moveStrings = new Vector<>(moves.size());
		for (Move m: moves) {
			moveStrings.add(m.toString());
		}

		if (moveStrings.contains(givenMove.toString())) {
			board[yEnd][xEnd] = board[yStart][xStart];
			board[yStart][xStart] = new Empty(xStart, yStart);
			// Special case: Pawn promotion
			if (Character.toLowerCase(pieceChar) == 'p') {
				if (yEnd == 5 && player.equals("W")) {
					board[yEnd][xEnd] = new Queen(xEnd, yEnd, true);
				} else if (yEnd == 0 && player.equals("B")) {
					board[yEnd][xEnd] = new Queen(xEnd, yEnd, false);
				}
			}
			turn += player.equals("W") ? 0 : 1;
			player = (player.equals("W")) ? "B" : "W";
			prevMoves.push(givenMove);

			if (Character.isUpperCase(pieceChar)) {
				if (!(piece instanceof King)) {
					wScore += (piece.getValue(xEnd, yEnd, true) - piece.getValue(xStart, yStart, true));
				}
				if (givenMove.capture instanceof King) {
					bScore = Double.NEGATIVE_INFINITY;
				} else {
					bScore -= givenMove.capture.getValue(xEnd, yEnd, false);
				}

			} else {
				if (!(piece instanceof King)) {
					bScore += (piece.getValue(xEnd, yEnd, false) - piece.getValue(xStart, yStart, false));
				}
				if (givenMove.capture instanceof King) {
					wScore = Double.NEGATIVE_INFINITY;
				} else {
					wScore -= givenMove.capture.getValue(xEnd, yEnd, true);
				}
			}
		}
	}

	public static String moveRandom() {
		// perform a random pieces.Move and return it - one example output is given below - note that you can call the board.chess.movesShuffled() function as well as the board.chess.pieces.Move() function in here

		String move = movesShuffled().get(0);
		move(move);

		return move;
	}

	public static String moveGreedy() {
		// perform a greedy pieces.Move and return it - one example output is given below - note that you can call the board.chess.movesEvaluated() function as well as the board.chess.pieces.Move() function in here

		String move = movesEvaluated().get(0);
		move(move);

		return move;
	}

	public static String moveNegamax(int intDepth, int intDuration) {
		// perform a negamax pieces.Move and return it - one example output is given below - note that you can call the the other functions in here

		long startTime = System.currentTimeMillis();
		long turnTime;
		String best = null;
		String bestSoFar = null;
		int score = -500000000;
		int scoreSoFar = -500000000;
		int temp = 0;
		Vector<String> moves = movesEvaluated();

		if (turn < 10) {
			turnTime = 5000;
		} else if (turn < 25) {
			turnTime = 7000;
		} else {
			turnTime = 8500;
		}

		if (intDepth >= 0) {
			for (String move : moves) {
				move(move);
				temp = -moveNegamaxRecursive(intDepth - 1);
				undo();

				if (temp > score) {
					best = move;
					score = temp;
					System.out.println("Score: " + score + " New best move: " + best);
				}
			}
		} else {
			for (int i=4; i<15 && System.currentTimeMillis() - startTime < turnTime/3; ++i) {
				System.out.println("Depth: " + i);
				for (String move : moves) {
					if (System.currentTimeMillis() - startTime > turnTime) {
						System.out.println("Out of time. Canceling.");
						break;
					}
					move(move);
					temp = -moveNegamaxRecursive(i - 1);
					undo();

					if (temp > score) {
						bestSoFar = move;
						scoreSoFar = temp;
					}
				}
				if (scoreSoFar > score && System.currentTimeMillis() - startTime < turnTime) {
					best = bestSoFar;
					score = scoreSoFar;
					System.out.println("Score: " + scoreSoFar + " New best move: " + bestSoFar);
					if (score == 500000) break;
				}
			}
		}

		System.out.println( "Duration: " + (System.currentTimeMillis() - startTime) + " Score: " + score + " Going with move: " + best);
		if (best == null) best = moveGreedy();
		move(best);
		return best;
	}

	private static int moveNegamaxRecursive(int depth) {
		if (depth == 0 || winner() != '?') {
			if (winner() != '=' && winner() != '?') {
				return -500000 - depth;
			}
			return eval();
		}

		int score = -500000000;
		Vector<String> moves = movesShuffled();

		for (String move : moves) {
			move(move);
			score = Math.max(score, -moveNegamaxRecursive(depth - 1));
			undo();
		}

		return score;
	}

	public static String moveAlphabeta(int intDepth, int intDuration) {
		// perform a alphabeta pieces.Move and return it - one example output is given below - note that you can call the the other functions in here

		long startTime = System.currentTimeMillis();
		long turnTime = 0;
		String best = null;

		alphaBeta abRunnable = new alphaBeta(intDepth);
		Thread abThread = new Thread(abRunnable);
		abThread.start();

		if (turn < 10) {
			turnTime = 5000;
		} else if (turn < 25) {
			turnTime = 7000;
		} else {
			turnTime = 8500;
		}
		System.out.println("Running AlphaBeta for: " + turnTime + "ms.");

		while ( (intDepth >= 0 || System.currentTimeMillis() - startTime < turnTime)
				&& abRunnable.running) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		abRunnable.running = false;
		try {
			abThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized (abRunnable.best) {
			best = abRunnable.best;
		}

		if (best == null) {
			System.out.println("Something is wrong, we didn't find a best move!");
			return moveGreedy();
		}
		System.out.println("Out of time, or execution finished. Time: " + (System.currentTimeMillis() - startTime) + ". Going with best so far: " + best);
		move(best);
		return best;
	}

	public static void undo() {
		// undo the last pieces.Move and update the state of the game / your internal variables accordingly - note that you need to maintain an internal variable that keeps track of the previous history for this
		Move toUndo = prevMoves.pop();
		String charIn = toUndo.toString();
		int xStart = charIn.charAt(0) - 'a';
		int xEnd = charIn.charAt(3) - 'a';
		int yStart = Character.getNumericValue(charIn.charAt(1)) - 1;
		int yEnd = Character.getNumericValue(charIn.charAt(4)) - 1;

		// Reset board positions
		board[yStart][xStart] = toUndo.original;
		board[yEnd][xEnd] = toUndo.capture;

		// Reset turn tracking
		turn -= player.equals("W") ? 1 : 0;
		player = (player.equals("W")) ? "B" : "W";

		if (toUndo.capture instanceof King) {
			evalRaw();
		} else {
			// Reset bScore and wScore
			if (Character.isUpperCase(toUndo.original.getChar())) {
				if (!(toUndo.original instanceof King)) {
					wScore -= (toUndo.original.getValue(xEnd, yEnd, true) - toUndo.original.getValue(xStart, yStart, true));
				}
				bScore += toUndo.capture.getValue(xEnd, yEnd, false);
			} else {
				if (!(toUndo.original instanceof King)) {
					bScore -= (toUndo.original.getValue(xEnd, yEnd, false) - toUndo.original.getValue(xStart, yStart, false));
				}
				wScore += toUndo.capture.getValue(xEnd, yEnd, true);
			}
		}
	}
}