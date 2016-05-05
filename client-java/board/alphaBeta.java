package board;

import java.util.Vector;

import static board.chess.*;

/**
 * Created by Corey on 4/21/2016.
 */
public class alphaBeta implements Runnable {

	private int intDepth;
	volatile public String best = "";
	volatile public boolean running = true;

	alphaBeta(int intDepth) {

		this.intDepth = intDepth;
	}

	@Override
	public void run() {

		String bestSoFar = null;
		double temp = 0;
		double alpha = Double.NEGATIVE_INFINITY;
		double alphaSoFar = Double.NEGATIVE_INFINITY;
		double beta = Double.POSITIVE_INFINITY;
		Vector<String> moves = movesEvaluated();

		if (intDepth >= 0) {
			for (String move : moves) {
				move(move);
				temp = -moveAlphabetaRecursive(intDepth - 1, - beta, -alpha);
				undo();

				if (temp > alpha) {
					synchronized (best) {
						best = move;
					}
					alpha = temp;
				}
			}
		} else {
			for (int i=4; i<15 && running; ++i) {
				System.out.println("Depth: " + i);
				for (String move : moves) {
					if (!running) return;

					move(move);
					temp = -moveAlphabetaRecursive(i - 1, - beta, -alpha);
					undo();

					if (temp > alpha) {
						bestSoFar = move;
						alphaSoFar = temp;
					}
				}
				if (alphaSoFar > alpha) {
					synchronized (best) {
						best = bestSoFar;
					}
					alpha = alphaSoFar;
					System.out.println("Score: " + alphaSoFar + " New best move: " + bestSoFar);
				}
			}
		}

		running = false;
		System.out.println("Finished - Score: " + alpha + " Going with move: " + best);
	}

	private double moveAlphabetaRecursive(int depth, double alpha, double beta) {
		if (!running) return -1;
		if (depth == 0 || winner() != '?') {
			double eval = eval();
			if (Double.isInfinite(eval)) {
				return Double.NEGATIVE_INFINITY;
			}
			return eval;
		}

		double score = Double.NEGATIVE_INFINITY;
		Vector<String> moves = movesShuffled();

		for (String move : moves) {
			move(move);
			score = Math.max(score, -moveAlphabetaRecursive(depth - 1, -beta, -alpha));
			undo();

			alpha = Math.max(alpha, score);
			if (alpha >= beta) {
				break;
			}
		}

		return (int)score;
	}
}
