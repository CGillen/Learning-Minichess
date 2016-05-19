package board;

import pieces.Move;

/**
 * Created by Corey on 4/14/2016.
 */
public class ScoredMove {
	public Move move;
	public double score;

	ScoredMove(Move move, double score) {
		this.move = move;
		this.score = score;
	}
}
