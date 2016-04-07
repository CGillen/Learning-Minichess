package pieces;

import java.util.Vector;

/**
 * Created by Corey on 4/6/2016.
 */
public abstract class piece {
	int x;
	int y;
	char type;

	public piece(int x, int y, char type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public abstract Vector<move> possibleMoves();

	public void move(int xMove, int yMove) {
		x += xMove;
		y += yMove;
	}
	public char getChar() {
		return type;
	}
}
