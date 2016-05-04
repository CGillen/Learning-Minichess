package pieces;

import board.chess;

import java.util.Vector;

/**
 * Created by Corey on 4/6/2016.
 */
public class King extends Piece {

	public King(int x, int y, boolean white) {
		super(white ? 'K' : 'k');
		value = new int[][]{
				{-50, -50, -50, -50, -50},
				{-35, -35, -35, -35, -35},
				{-10, -10, -10, -10, -10},
				{0, 0, 0, 0, 0, 0},
				{70, 100, 100, 100, 200, 200},
				{100, 200, 300, 450, 500, 500},
		};
	}

	@Override
	public Vector<Move> possibleMoves(int x, int y) {
		Vector moves = new Vector<>();

		// Cardinal directions
		if (x+1 <= 4 && !chess.isOwn(x+1, y)) moves.add(new Move(x, y, 1, 0));
		if (x-1 >= 0 && !chess.isOwn(x-1, y)) moves.add(new Move(x, y, -1, 0));
		if (y+1 <= 5 && !chess.isOwn(x, y+1)) moves.add(new Move(x, y, 0, 1));
		if (y-1 >= 0 && !chess.isOwn(x, y-1)) moves.add(new Move(x, y, 0, -1));

		// Diagonal directions
		if (x+1 <= 4 && y+1 <= 5 && !chess.isOwn(x+1, y+1)) moves.add(new Move(x, y, 1, 1));
		if (x+1 <= 4 && y-1 >= 0 && !chess.isOwn(x+1, y-1)) moves.add(new Move(x, y, 1, -1));
		if (x-1 >= 0 && y+1 <= 5 && !chess.isOwn(x-1, y+1)) moves.add(new Move(x, y, -1, 1));
		if (x-1 >= 0 && y-1 >= 0 && !chess.isOwn(x-1, y-1)) moves.add(new Move(x, y, -1, -1));

		return moves;
	}
}
