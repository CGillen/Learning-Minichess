package pieces;

import java.util.Vector;

/**
 * Created by Corey on 4/6/2016.
 */
public abstract class Piece {
	char type;
	float value;

	public Piece(char type) {
		this.type = type;
	}

	public abstract Vector<Move> possibleMoves(int x, int y);

	public char getChar() {
		return type;
	}

	public float getValue() {
		return value;
	}
}
