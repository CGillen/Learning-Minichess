package pieces;

import java.util.Vector;

/**
 * Created by Corey on 4/6/2016.
 */
public abstract class Piece {
	int x;
	int y;
	char type;
	float value;

	public Piece(int x, int y, char type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public abstract Vector<Move> possibleMoves();

	public void move(int xMove, int yMove) {
		x += xMove;
		y += yMove;
	}
	public char getChar() {
		return type;
	}

	public float getValue() {
		return value;
	}
}
