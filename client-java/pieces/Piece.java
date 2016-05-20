package pieces;

import java.util.BitSet;
import java.util.Vector;

/**
 * Created by Corey on 4/6/2016.
 */
public abstract class Piece {
	private char type;
	private static double[][] value;
	private static BitSet[][] zobrist;

	public Piece(char type) {
		this.type = type;
	}

	public abstract Vector<Move> possibleMoves(int x, int y);

	public char getChar() {
		return type;
	}

	public static double getValue(int x, int y, boolean white) {
		if (white) {
			return value[(5*(y+1)) % 6][x];
		}
		return value[y][(4*(x+1)) % 5];
	}

	public static BitSet getZobrist(int x, int y) {
		return zobrist[y][x];
	}
}
