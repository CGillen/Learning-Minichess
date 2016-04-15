package pieces;

import board.chess;

import java.util.Vector;

/**
 * Created by Corey on 4/6/2016.
 */
public class Queen extends Piece {
	public Queen(int x, int y, boolean white) {
		super(x, y, white ? 'Q' : 'q');
		value = 9;
	}

	@Override
	public Vector<Move> possibleMoves() {
		Vector moves = new Vector<>();
		boolean ur = true;
		boolean ul = true;
		boolean dr = true;
		boolean dl = true;
		boolean u = true;
		boolean d = true;
		boolean r = true;
		boolean l = true;

		for (int i = 1; i <= 5; ++i) {
			// Diagonal moves
			if (x+i <= 4 && y+i <= 5 && ur) {
				if (!chess.isOwn(x + i, y + i)) moves.add(new Move(x, y, i, i));
				if (!chess.isNothing(x + i, y + i)) ur = false;
			}
			if (x+i <= 4 && y-i >= 0 && ul) {
				if (!chess.isOwn(x + i, y - i)) moves.add(new Move(x, y, i, -i));
				if (!chess.isNothing(x + i, y - i)) ul = false;
			}
			if (x-i >= 0 && y+i <= 5 && dr) {
				if (!chess.isOwn(x - i, y + i)) moves.add(new Move(x, y, -i, i));
				if (!chess.isNothing(x - i, y + i)) dr = false;
			}
			if (x-i >= 0 && y-i >= 0 && dl) {
				if (!chess.isOwn(x - i, y - i)) moves.add(new Move(x, y, -i, -i));
				if (!chess.isNothing(x - i, y - i)) dl = false;
			}
			// Cardinal moves
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
