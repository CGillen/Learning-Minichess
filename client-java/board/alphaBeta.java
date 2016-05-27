package board;

import transposition.Node;
import transposition.Transposition;
import transposition.ZobristHash;

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
			for (int i=4; i<20 && running; ++i) {
				System.out.println("Depth: " + i);
				for (String move : moves) {
					//if (!running) return;

					move(move);
					temp = -moveAlphabetaRecursive(i - 1, - beta, -alpha);
					undo();

					// If ran out of time quit out before updating our best move
					if (Double.isNaN(temp)) {
						return;
					}

					if (temp > alpha) {
						bestSoFar = move;
						alphaSoFar = temp;
					}
				}
				if (alphaSoFar > alpha) {
					synchronized (best) {
						best = bestSoFar;
						alpha = alphaSoFar;
						System.out.println("Score: " + alphaSoFar + " New best move: " + bestSoFar);
						if (Double.isInfinite(alpha) && alpha > 0) {
							break;
						}
					}
				}
			}
		}

		running = false;
		System.out.println("Finished - Score: " + alpha + " Going with move: " + best);
	}

	private double moveAlphabetaRecursive(int depth, double alpha, double beta) {
		double oldAlpha = alpha;
		//++chess.abCalls;

		if (!running) return Double.NaN;
		if (depth == 0 || winner() != '?') {
			double eval = eval();
			if (Double.isInfinite(eval)) {
				return Double.NEGATIVE_INFINITY;
			}
			return eval;
		}

		// Load from transposition table
		Transposition trans = chess.transTable.get(ZobristHash.getHash());
		if (trans != null) {
			if (trans.getDepth() > depth) {
				switch (trans.getNode()) {
					case EXACT: return trans.getScore();
					case LOWER: alpha = Math.max(alpha, trans.getScore()); break;
					case UPPER: beta = Math.min(beta, trans.getScore()); break;
				}
			}
		}


		double score = Double.NEGATIVE_INFINITY;
		Vector<String> moves = movesShuffled();

		for (String move : moves) {
			move(move);
			score = Math.max(score, -moveAlphabetaRecursive(depth - 1, -beta, -alpha));
			undo();

			// If we ran out of time unroll the stack
			if (Double.isNaN(score)) {
				return Double.NaN;
			}

			alpha = Math.max(alpha, score);
			if (alpha >= beta) {
				break;
			}
		}

		// Store to the transposition table
		Node toStore = Node.EXACT;
		if (score <= oldAlpha) {
			toStore = Node.UPPER;
		} else if (score >= beta) {
			toStore = Node.LOWER;
		}
		chess.transTable.put(ZobristHash.getHash(), new Transposition(score, depth, toStore));

		return (int)score;
	}
}
