package pieces;

import board.chess;

import java.util.Vector;

/**
 * Created by Corey on 4/6/2016.
 */
public class Rook extends Piece {
	public Rook(int x, int y, boolean white) {
		super(white ? 'R' : 'r');
		value = 5;
	}

	@Override
	public Vector<Move> possibleMoves(int x, int y) {
		Vector moves = new Vector<>();
		boolean u = true;
		boolean d = true;
		boolean r = true;
		boolean l = true;

		for (int i = 1; i <= 5; ++i) {
			if (x+i <= 4 && r) {
				if (!chess.isOwn(x+i,y)) moves.add(new Move(x, y, i, 0));
				if (!chess.isNothing(x+i,y)) r = false;
			}
			if (x-i >= 0 && l) {
				if (!chess.isOwn(x-i,y)) moves.add(new Move(x, y, -i, 0));
				if (!chess.isNothing(x-i,y)) l = false;
			}
			if (y+i <= 5 && u) {
				if (!chess.isOwn(x,y+i)) moves.add(new Move(x, y, 0, i));
				if (!chess.isNothing(x,y+i)) u = false;
			}
			if (y-i >= 0 && d) {
				if (!chess.isOwn(x,y-i)) moves.add(new Move(x, y, 0, -i));
				if (!chess.isNothing(x,y-i)) d = false;
			}
		}

		return moves;
	}
}
