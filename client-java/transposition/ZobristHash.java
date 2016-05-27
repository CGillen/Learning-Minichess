package transposition;

import board.chess;
import pieces.*;

import java.util.BitSet;
import java.util.Timer;

/**
 * Created by Corey on 5/19/2016.
 */
public class ZobristHash {
	private static BitSet zobrist = new BitSet(128);
	private static final BitSet zobristWhite = BitSet.valueOf(new byte[]{25,28,-8,-38,-100,99,-94,84,13,-17,-128,80,-35,108,-19,96});
	private static final BitSet zobristBlack = BitSet.valueOf(new byte[]{75,119,58,44,77,104,35,-126,78,-121,-107,40,101,-65,-18,-29});

	public static void init() {
		String board = chess.boardGet();
		String[] lines = board.split("\n");
		String turn = lines[0].split(" ")[0];
		String player = lines[0].split(" ")[1];

		zobrist.clear();

		// Xor wiwth next to move
		zobrist.xor(player.equals("W") ? zobristWhite : zobristBlack);

		for (int x=0; x < 5; ++x) {
			for (int y=1; y < 7; ++y) {
				xor(lines[y].charAt(x), x, y-1);
			}
		}
	}

	public static BitSet update(Move moveIn) {

		String charIn = moveIn.toString();
		int xStart = charIn.charAt(0) - 'a';
		int xEnd = charIn.charAt(3) - 'a';
		int yStart = Character.getNumericValue(charIn.charAt(1)) - 1;
		int yEnd = Character.getNumericValue(charIn.charAt(4)) - 1;
		char orig = moveIn.original.getChar();
		char cap = moveIn.capture.getChar();

		String board = chess.boardGet();
		String[] lines = board.split("\n");
		String turn = lines[0].split(" ")[0];
		String player = lines[0].split(" ")[1];

		// Update hash
		xor(orig, xStart, yStart);
		xor('.', xStart, yStart);
		xor(cap, xEnd, yEnd);
		xor(orig, xEnd, yEnd);
		zobrist.xor(zobristWhite);
		zobrist.xor(zobristBlack);

		return zobrist;
	}

	public static BitSet getHash() {
		return zobrist;
	}

	private static void xor(char piece, int x, int y) {
		switch (Character.toLowerCase(piece)) {
			case '.': zobrist.xor(Empty.getZobrist(x, y)); break;
			case 'b': zobrist.xor((Bishop.getZobrist(x, y))); break;
			case 'k': zobrist.xor(King.getZobrist(x, y)); break;
			case 'n': zobrist.xor(Knight.getZobrist(x, y)); break;
			case 'p': zobrist.xor(Pawn.getZobrist(x, y)); break;
			case 'q': zobrist.xor(Queen.getZobrist(x, y)); break;
			case 'r': zobrist.xor(Rook.getZobrist(x, y)); break;
		}
	}
}
