package pieces;

/**
 * Created by Corey on 4/6/2016.
 */
public class move {
	private int xStart, yStart, xEnd, yEnd;
	public piece capture, original;

	public move(int xStart, int yStart, int xMove, int yMove) {
		this(xStart, yStart, xMove, yMove, '.', '.', true);
	}

	public move(int xStart, int yStart, int xMove, int yMove, char original, char capture, boolean white) {
		this.xStart = xStart;
		this.yStart = yStart;
		this.xEnd = this.xStart + xMove;
		this.yEnd = this.yStart + yMove;

		switch (Character.toLowerCase(original)) {
			case 'p': this.original = new pawn(xEnd, yEnd, white); break;
			case 'r': this.original = new rook(xEnd, yEnd, white); break;
			case 'n': this.original = new knight(xEnd, yEnd, white); break;
			case 'b': this.original = new bishop(xEnd, yEnd, white); break;
			case 'q': this.original = new queen(xEnd, yEnd, white); break;
			case 'k': this.original = new king(xEnd, yEnd, white); break;
		}
		switch (Character.toLowerCase(capture)) {
			case 'p': this.capture = new pawn(xStart, yStart, !white); break;
			case 'r': this.capture = new rook(xStart, yStart, !white); break;
			case 'n': this.capture = new knight(xStart, yStart, !white); break;
			case 'b': this.capture = new bishop(xStart, yStart, !white); break;
			case 'q': this.capture = new queen(xStart, yStart, !white); break;
			case 'k': this.capture = new king(xStart, yStart, !white); break;
			case '.': this.capture = new empty(xStart, yStart); break;
		}
	}

	public String toString() {
		String toReturn = "";

		toReturn += (char) ('a' + xStart);
		toReturn += Integer.toString(yStart + 1);
		toReturn += '-';
		toReturn += (char) ('a' + xEnd);
		toReturn += Integer.toString(yEnd + 1);
		toReturn += '\n';

		return toReturn;
	}
}
