package pieces;

/**
 * Created by Corey on 4/6/2016.
 */
public class move {
	int xStart, yStart, xEnd, yEnd;

	public move(int xStart, int yStart, int xMove, int yMove) {
		this.xStart = xStart;
		this.yStart = yStart;
		this.xEnd = this.xStart + xMove;
		this.yEnd = this.yStart + yMove;
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
