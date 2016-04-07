package pieces;

import board.board;

import java.util.Vector;

/**
 * Created by Corey on 4/6/2016.
 */
public class queen extends piece {
	public queen(int x, int y, boolean white) {
		super(x, y, white ? 'Q' : 'q');
	}

	@Override
	public Vector<move> possibleMoves() {
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
				if (!board.isOwn(x + i, y + i)) moves.add(new move(x, y, i, i));
				if (!board.isNothing(x + i, y + i)) ur = false;
			}
			if (x+i <= 4 && y-i >= 0 && ul) {
				if (!board.isOwn(x + i, y - i)) moves.add(new move(x, y, i, -i));
				if (!board.isNothing(x + i, y - i)) ul = false;
			}
			if (x-i >= 0 && y+i <= 5 && dr) {
				if (!board.isOwn(x - i, y + i)) moves.add(new move(x, y, -i, i));
				if (!board.isNothing(x - i, y + i)) dr = false;
			}
			if (x-i >= 0 && y-i >= 0 && dl) {
				if (!board.isOwn(x - i, y - i)) moves.add(new move(x, y, -i, -i));
				if (!board.isNothing(x - i, y - i)) dl = false;
			}
			// Cardinal moves
			if (x+i <= 4 && r) {
				if (!board.isOwn(x+i,y)) moves.add(new move(x, y, i, 0));
				if (!board.isNothing(x+i,y)) r = false;
			}
			if (x-i >= 0 && l) {
				if (!board.isOwn(x-i,y)) moves.add(new move(x, y, -i, 0));
				if (!board.isNothing(x-i,y)) l = false;
			}
			if (y+i <= 5 && u) {
				if (!board.isOwn(x,y+i)) moves.add(new move(x, y, 0, i));
				if (!board.isNothing(x,y+i)) u = false;
			}
			if (y-i >= 0 && d) {
				if (!board.isOwn(x,y-i)) moves.add(new move(x, y, 0, -i));
				if (!board.isNothing(x,y-i)) d = false;
			}
		}

		return moves;
	}
}
