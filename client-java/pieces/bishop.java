package pieces;

import board.board;

import java.util.Vector;

/**
 * Created by Corey on 4/6/2016.
 */
public class bishop extends piece {
	public bishop(int x, int y, boolean white) {
		super(x, y, white ? 'B' : 'b');
	}

	@Override
	public Vector<move> possibleMoves() {
		Vector moves = new Vector<>();
		boolean ur = true;
		boolean ul = true;
		boolean dr = true;
		boolean dl = true;

		for (int i = 1; i <= 4; ++i) {
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
		}

		if (x+1 <= 4 && board.isNothing(x+1, y)) moves.add(new move(x, y, 1, 0));
		if (x-1 >= 0 && board.isNothing(x-1, y)) moves.add(new move(x, y, -1, 0));
		if (y+1 <= 5 && board.isNothing(x, y+1)) moves.add(new move(x, y, 0, 1));
		if (y-1 >= 0 && board.isNothing(x, y-1)) moves.add(new move(x, y, 0, -1));

		return moves;
	}
}
