package board;

import java.util.Vector;

import static board.chess.*;

/**
 * Created by Corey on 4/21/2016.
 */
public class alphaBeta implements Runnable {

	private int intDepth;
	volatile public String best = null;
	volatile public boolean running = true;

	alphaBeta(int intDepth) {

		this.intDepth = intDepth;
	}

	@Override
	public void run() {

		String bestSoFar = null;
		int temp = 0;
		int alpha = -500000000;
		int alphaSoFar = -500000000;
		int beta = 500000000;
		Vector<String> moves = movesEvaluated();

		if (intDepth >= 0) {
			for (String move : moves) {
				move(move);
				temp = -moveAlphabetaRecursive(intDepth - 1, - beta, -alpha);
				undo();

				if (temp > alpha) {
					best = move;
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
					best = bestSoFar;
					alpha = alphaSoFar;
					System.out.println("Score: " + alphaSoFar + " New best move: " + bestSoFar);
				}
			}
		}

		running = false;
		System.out.println("Finished - Score: " + alpha + " Going with move: " + best);
	}

	private int moveAlphabetaRecursive(int depth, int alpha, int beta) {
		if (!running) return -1;
		if (depth == 0 || winner() != '?') {
			int eval = eval();
			if (Math.abs(eval) > 1000) {
				return -500000 - depth;
			}
			return eval;
		}

		int score = -500000000;
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

		return score;
	}
}
