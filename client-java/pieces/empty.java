package pieces;

import java.util.Vector;

/**
 * Created by Your name on 4/6/2016.
 */
public class Empty extends Piece {
	public Empty(int x, int y) {
		super(x, y, '.');
		value = 0;
	}

	@Override
	public Vector<Move> possibleMoves() {
		return null;
	}
}
