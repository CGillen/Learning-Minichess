package board;

import pieces.Move;

/**
 * Created by Corey on 4/14/2016.
 */
public class ScoredMove {
	public Move move;
	public int score;

	ScoredMove(Move move, int score) {
		this.move = move;
		this.score = score;
	}
}
