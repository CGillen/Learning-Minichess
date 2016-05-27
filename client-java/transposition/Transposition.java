package transposition;

/**
 * Created by Corey on 5/26/2016.
 */
public class Transposition {
	double score;
	boolean valid;
	int depth;
	Node node;

	public Transposition(double score, int depth, Node node) {
		this.score = score;
		this.depth = depth;
		this.node = node;
		this.valid = true;
	}

	public void invalidate() {
		valid = false;
	}

	public Node getNode() {
		return node;
	}

	public int getDepth() {
		return depth;
	}

	public double getScore() {
		return score;
	}
}
