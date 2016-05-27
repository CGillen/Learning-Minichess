package pieces;

import board.chess;

import java.util.BitSet;
import java.util.Vector;

/**
 * Created by Corey on 4/6/2016.
 */
public class Bishop extends Piece {

	private static BitSet[][] zobrist = new BitSet[][]{
		{BitSet.valueOf(new byte[]{-30, 65, 39, -83, -24, 40, 45, 74, 2, 117, -82, 113, 102, -128, 122, -114}), BitSet.valueOf(new byte[]{-100, -82, 50, -100, 115, -123, -122, -62, 75, -39, 125, -2, 78, 42, 124, -17}), BitSet.valueOf(new byte[]{110, -8, -106, -42, -85, -116, -3, 108, 77, 79, 96, -94, -64, 120, -14, -117}), BitSet.valueOf(new byte[]{-88, 14, -83, 16, -119, -123, 81, -102, 17, -64, 13, 126, -21, -70, -20, 97}), BitSet.valueOf(new byte[]{-1, -43, -112, -71, -74, 16, 81, -36, 69, -47, 83, -117, -106, -76, -8, 34})},
		{BitSet.valueOf(new byte[]{3, 11, 65, -53, -45, 55, -111, -101, 86, -2, -124, -47, -81, 49, 125, -114}), BitSet.valueOf(new byte[]{-49, -58, -50, -4, 66, 16, 24, 3, -4, 60, 100, 126, -75, -104, 105, 78}), BitSet.valueOf(new byte[]{-76, -65, 116, 27, 80, 74, -43, -45, 73, 23, 45, -27, -6, 77, -39, 22}), BitSet.valueOf(new byte[]{-30, 98, -57, -126, 40, -117, 89, 86, -80, 45, 12, -48, 61, -119, 17, -127}), BitSet.valueOf(new byte[]{80, -3, -30, 69, 69, -126, 38, -99, -76, -46, -49, -3, 78, -37, 43, 88})},
		{BitSet.valueOf(new byte[]{30, 56, -59, -16, 69, 73, 57, -115, 26, 112, 39, -108, 98, -105, 8, -108}), BitSet.valueOf(new byte[]{-11, -1, -47, 71, 49, 55, -24, -55, -47, -26, -35, 41, -92, 58, 105, 55}), BitSet.valueOf(new byte[]{98, 6, -90, 111, -76, 122, -61, 56, 19, -118, -42, 18, 6, -59, -23, -46}), BitSet.valueOf(new byte[]{24, 115, 97, -102, -79, 91, 64, 6, 26, 108, -16, 18, -121, 61, 95, 57}), BitSet.valueOf(new byte[]{59, -60, -89, -101, 28, 58, 40, -32, -25, -56, -104, -47, 4, 0, 41, -97})},
		{BitSet.valueOf(new byte[]{116, 118, 99, 75, -61, 57, 86, -111, 78, -64, -40, -42, 5, -101, 32, -122}), BitSet.valueOf(new byte[]{-27, 23, -39, 92, -119, -53, 122, -61, -42, -22, 115, 103, 31, 19, 5, -122}), BitSet.valueOf(new byte[]{125, -18, -109, -61, 35, 106, 120, -96, -107, -8, 49, 65, -89, -127, -103, -55}), BitSet.valueOf(new byte[]{1, 10, -96, -118, -32, -51, 69, 72, 101, 123, -46, 76, 44, -18, -28, -115}), BitSet.valueOf(new byte[]{36, -63, 34, -104, -39, 5, 15, 27, 56, 9, 97, -37, -108, 99, -43, 74})},
		{BitSet.valueOf(new byte[]{-99, -73, -54, 37, 60, 11, 52, 93, 98, -17, -61, 47, 99, 15, 1, -27}), BitSet.valueOf(new byte[]{112, 74, 15, -110, 87, -94, 121, -66, 123, 95, 81, -44, 5, -113, 44, -106}), BitSet.valueOf(new byte[]{124, 97, 65, 73, 89, -10, -41, -104, 40, 98, -26, 28, 119, -92, -103, 19}), BitSet.valueOf(new byte[]{82, 124, 112, 33, 94, 114, -28, -34, -77, 115, 94, -96, 114, -112, -83, 65}), BitSet.valueOf(new byte[]{114, 48, -84, 69, 53, 11, -11, -55, 21, -94, -79, -5, -56, 127, -25, -13})},
		{BitSet.valueOf(new byte[]{-105, -124, -52, -76, 71, -59, -101, -53, 3, 69, -128, 71, 84, -62, 121, 27}), BitSet.valueOf(new byte[]{-121, 12, -36, -67, -73, 13, 51, 23, -6, 59, 1, 115, 84, 63, 23, -46}), BitSet.valueOf(new byte[]{118, 83, 46, 34, 66, 47, 88, -7, 4, 106, -111, 89, -20, 59, 98, -84}), BitSet.valueOf(new byte[]{41, 103, -47, 8, 68, -15, 35, -58, -20, 62, -118, -51, 41, -77, -118, -60}), BitSet.valueOf(new byte[]{-28, -25, -87, -1, 117, 71, 116, -110, -127, 6, 74, -76, -128, -122, 98, 76})}
	};

	public Bishop(int x, int y, boolean white) {
		super(white ? 'B' : 'b');
		value = new double[][]{
				{40, 30, 20, 30, 40},
				{30, 40, 30, 40, 30},
				{20, 30, 40, 30, 20},
				{20, 30, 40, 30, 20},
				{30, 40, 30, 40, 30},
				{40, 30, 20, 30, 40},
		};
	}

	@Override
	public Vector<Move> possibleMoves(int x, int y) {
		Vector moves = new Vector<>();
		boolean ur = true;
		boolean ul = true;
		boolean dr = true;
		boolean dl = true;

		for (int i = 1; i <= 4; ++i) {
			if (x+i <= 4 && y+i <= 5 && ur) {
				if (!chess.isOwn(x + i, y + i)) moves.add(new Move(x, y, i, i));
				if (!chess.isNothing(x + i, y + i)) ur = false;
			}
			if (x+i <= 4 && y-i >= 0 && ul) {
				if (!chess.isOwn(x + i, y - i)) moves.add(new Move(x, y, i, -i));
				if (!chess.isNothing(x + i, y - i)) ul = false;
			}
			if (x-i >= 0 && y+i <= 5 && dr) {
				if (!chess.isOwn(x - i, y + i)) moves.add(new Move(x, y, -i, i));
				if (!chess.isNothing(x - i, y + i)) dr = false;
			}
			if (x-i >= 0 && y-i >= 0 && dl) {
				if (!chess.isOwn(x - i, y - i)) moves.add(new Move(x, y, -i, -i));
				if (!chess.isNothing(x - i, y - i)) dl = false;
			}
		}

		if (x+1 <= 4 && chess.isNothing(x+1, y)) moves.add(new Move(x, y, 1, 0));
		if (x-1 >= 0 && chess.isNothing(x-1, y)) moves.add(new Move(x, y, -1, 0));
		if (y+1 <= 5 && chess.isNothing(x, y+1)) moves.add(new Move(x, y, 0, 1));
		if (y-1 >= 0 && chess.isNothing(x, y-1)) moves.add(new Move(x, y, 0, -1));

		return moves;
	}

	public static BitSet getZobrist(int x, int y) {
		return zobrist[y][x];
	}
}
