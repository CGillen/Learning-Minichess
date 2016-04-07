package pieces;

import board.board;

import java.util.Vector;

/**
 * Created by Corey on 4/6/2016.
 */
public class pawn extends piece {

	public pawn(int x, int y, boolean white) {
		super(x, y, white ? 'P' : 'p');
		value = 1;
	}

	@Override
	public Vector<move> possibleMoves() {
		Vector moves = new Vector<>();
		int yDirection = Character.isUpperCase(type) ? 1 : -1;

		if (x < 4 && board.isEnemy(x + 1, y + yDirection)) moves.add(new move(x, y, 1, yDirection));
		if (x > 0 && board.isEnemy(x - 1, y + yDirection)) moves.add(new move(x, y, -1, yDirection));
		if (board.isNothing(x, y+yDirection)) moves.add(new move(x, y, 0, yDirection));

		return moves;
	}
}
