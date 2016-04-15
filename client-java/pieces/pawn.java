package pieces;

import board.chess;

import java.util.Vector;

/**
 * Created by Corey on 4/6/2016.
 */
public class pawn extends Piece {

	public pawn(int x, int y, boolean white) {
		super(x, y, white ? 'P' : 'p');
		value = 1;
	}

	@Override
	public Vector<Move> possibleMoves() {
		Vector moves = new Vector<>();
		int yDirection = Character.isUpperCase(type) ? 1 : -1;

		if (x < 4 && chess.isEnemy(x + 1, y + yDirection)) moves.add(new Move(x, y, 1, yDirection));
		if (x > 0 && chess.isEnemy(x - 1, y + yDirection)) moves.add(new Move(x, y, -1, yDirection));
		if (chess.isNothing(x, y+yDirection)) moves.add(new Move(x, y, 0, yDirection));

		return moves;
	}
}
