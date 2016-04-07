package pieces;

import java.util.Vector;

/**
 * Created by Your name on 4/6/2016.
 */
public class empty extends piece {
	public empty(int x, int y) {
		super(x, y, '.');
		value = 0;
	}

	@Override
	public Vector<move> possibleMoves() {
		return null;
	}
}
