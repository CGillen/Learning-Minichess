package pieces;

import board.chess;

import java.util.Vector;

/**
 * Created by Corey on 4/6/2016.
 */
public class bishop extends piece {
	public bishop(int x, int y, boolean white) {
		super(x, y, white ? 'B' : 'b');
		value = 3;
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
				if (!chess.isOwn(x + i, y + i)) moves.add(new move(x, y, i, i));
				if (!chess.isNothing(x + i, y + i)) ur = false;
			}
			if (x+i <= 4 && y-i >= 0 && ul) {
				if (!chess.isOwn(x + i, y - i)) moves.add(new move(x, y, i, -i));
				if (!chess.isNothing(x + i, y - i)) ul = false;
			}
			if (x-i >= 0 && y+i <= 5 && dr) {
				if (!chess.isOwn(x - i, y + i)) moves.add(new move(x, y, -i, i));
				if (!chess.isNothing(x - i, y + i)) dr = false;
			}
			if (x-i >= 0 && y-i >= 0 && dl) {
				if (!chess.isOwn(x - i, y - i)) moves.add(new move(x, y, -i, -i));
				if (!chess.isNothing(x - i, y - i)) dl = false;
			}
		}

		if (x+1 <= 4 && chess.isNothing(x+1, y)) moves.add(new move(x, y, 1, 0));
		if (x-1 >= 0 && chess.isNothing(x-1, y)) moves.add(new move(x, y, -1, 0));
		if (y+1 <= 5 && chess.isNothing(x, y+1)) moves.add(new move(x, y, 0, 1));
		if (y-1 >= 0 && chess.isNothing(x, y-1)) moves.add(new move(x, y, 0, -1));

		return moves;
	}
}