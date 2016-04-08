package pieces;

import board.chess;

import java.util.Vector;

/**
 * Created by Corey on 4/6/2016.
 */
public class knight extends piece {
	public knight(int x, int y, boolean white) {
		super(x, y, white ? 'N' : 'n');
		value = 3;
	}

	@Override
	public Vector<move> possibleMoves() {
		Vector moves = new Vector<>();

		if (x+1 <= 4 && y+2 <= 5 && !chess.isOwn(x+1, y+2)) moves.add(new move(x, y, 1, 2));
		if (x+1 <= 4 && y-2 >= 0 && !chess.isOwn(x+1, y-2)) moves.add(new move(x, y, 1, -2));
		if (x-1 >= 0 && y+2 <= 5 && !chess.isOwn(x-1, y+2)) moves.add(new move(x, y, -1, 2));
		if (x-1 >= 0 && y-2 >= 0 && !chess.isOwn(x-1, y-2)) moves.add(new move(x, y, -1, -2));
		if (x+2 <= 4 && y+1 <= 5 && !chess.isOwn(x+2, y+1)) moves.add(new move(x, y, 2, 1));
		if (x+2 <= 4 && y-1 >= 0 && !chess.isOwn(x+2, y-1)) moves.add(new move(x, y, 2, -1));
		if (x-2 >= 0 && y+1 <= 5 && !chess.isOwn(x-2, y+1)) moves.add(new move(x, y, -2, 1));
		if (x-2 >= 0 && y-1 >= 0 && !chess.isOwn(x-2, y-1)) moves.add(new move(x, y, -2, -1));

		return moves;
	}
}
