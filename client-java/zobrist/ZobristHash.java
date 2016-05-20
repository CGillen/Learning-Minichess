package zobrist;

import board.chess;
import pieces.*;

import java.util.BitSet;
import java.util.regex.Pattern;

/**
 * Created by Corey on 5/19/2016.
 */
public class ZobristHash {
	private static BitSet zobrist = new BitSet(128);
	private static final BitSet zobristWhite = new BitSet(128);
	private static final BitSet zobristBlack = new BitSet(128);

	public void init() {
		String board = chess.boardGet();
		String[] lines = board.split("\n");
		String turn = lines[0].split(" ")[0];
		String player = lines[0].split(" ")[1];

		// Xor wiwth next to move
		zobrist.xor(player.equals("W") ? zobristWhite : zobristBlack);

		for (int x=0; x < 5; ++x) {
			for (int y=1; y < 7; ++y) {
				xor(lines[y].charAt(x), x, y);
			}
		}

		zobrist.clear();
	}

	public BitSet update(Move moveIn) {

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

	public BitSet getHash() {
		return zobrist;
	}

	private void xor(char piece, int x, int y) {

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
