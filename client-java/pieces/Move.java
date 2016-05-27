package pieces;

/**
 * Created by Corey on 4/6/2016.
 */
public class Move {
	private int xStart, yStart, xEnd, yEnd;
	public Piece capture, original;

	public Move(String charIn) {
		this((charIn.charAt(0) - 'a'), (Character.getNumericValue(charIn.charAt(1)) - 1), ((charIn.charAt(3) - 'a') - (charIn.charAt(0) - 'a')), ((Character.getNumericValue(charIn.charAt(4)) - 1) - (Character.getNumericValue(charIn.charAt(1)) - 1)));
	}

	public Move(int xStart, int yStart, int xMove, int yMove) {
		this(xStart, yStart, xMove, yMove, '.', '.', true);
	}

	public Move(int xStart, int yStart, int xMove, int yMove, char original, char capture, boolean white) {
		this.xStart = xStart;
		this.yStart = yStart;
		this.xEnd = this.xStart + xMove;
		this.yEnd = this.yStart + yMove;

		switch (Character.toLowerCase(original)) {
			case 'p': this.original = new Pawn(xEnd, yEnd, white); break;
			case 'r': this.original = new Rook(xEnd, yEnd, white); break;
			case 'n': this.original = new Knight(xEnd, yEnd, white); break;
			case 'b': this.original = new Bishop(xEnd, yEnd, white); break;
			case 'q': this.original = new Queen(xEnd, yEnd, white); break;
			case 'k': this.original = new King(xEnd, yEnd, white); break;
		}
		switch (Character.toLowerCase(capture)) {
			case 'p': this.capture = new Pawn(xStart, yStart, !white); break;
			case 'r': this.capture = new Rook(xStart, yStart, !white); break;
			case 'n': this.capture = new Knight(xStart, yStart, !white); break;
			case 'b': this.capture = new Bishop(xStart, yStart, !white); break;
			case 'q': this.capture = new Queen(xStart, yStart, !white); break;
			case 'k': this.capture = new King(xStart, yStart, !white); break;
			case '.': this.capture = new Empty(xStart, yStart); break;
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
