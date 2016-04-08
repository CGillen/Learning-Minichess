package pieces;

import board.chess;

import java.util.Vector;

/**
 * Created by Corey on 4/6/2016.
 */
public class king extends piece {
	public king(int x, int y, boolean white) {
		super(x, y, white ? 'K' : 'k');
		value = 1000;
	}

	@Override
	public Vector<move> possibleMoves() {
		Vector moves = new Vector<>();

		// Cardinal directions
		if (x+1 <= 4 && !chess.isOwn(x+1, y)) moves.add(new move(x, y, 1, 0));
		if (x-1 >= 0 && !chess.isOwn(x-1, y)) moves.add(new move(x, y, -1, 0));
		if (y+1 <= 5 && !chess.isOwn(x, y+1)) moves.add(new move(x, y, 0, 1));
		if (y-1 >= 0 && !chess.isOwn(x, y-1)) moves.add(new move(x, y, 0, -1));

		// Diagonal directions
		if (x+1 <= 4 && y+1 <= 5 && !chess.isOwn(x+1, y+1)) moves.add(new move(x, y, 1, 1));
		if (x+1 <= 4 && y-1 >= 0 && !chess.isOwn(x+1, y-1)) moves.add(new move(x, y, 1, -1));
		if (x-1 >= 0 && y+1 <= 5 && !chess.isOwn(x-1, y+1)) moves.add(new move(x, y, -1, 1));
		if (x-1 >= 0 && y-1 >= 0 && !chess.isOwn(x-1, y-1)) moves.add(new move(x, y, -1, -1));

		return moves;
	}
}
