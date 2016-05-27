package pieces;

import board.chess;

import java.util.BitSet;
import java.util.Vector;

/**
 * Created by Corey on 4/6/2016.
 */
public class King extends Piece {

	private static BitSet[][] zobrist = new BitSet[][]{
			{BitSet.valueOf(new byte[]{90,53,56,67,65,121,-28,93,101,8,-38,58,-15,-19,-104,127}), BitSet.valueOf(new byte[]{41,-39,68,109,116,-36,-11,-77,84,-12,33,-99,36,96,-62,-7}), BitSet.valueOf(new byte[]{46,106,-66,-123,-112,-122,-4,-104,67,120,-112,-47,43,-48,35,-40}), BitSet.valueOf(new byte[]{83,2,28,-87,-53,-61,46,17,90,-16,42,126,50,109,-56,93}), BitSet.valueOf(new byte[]{113,-74,127,-2,29,5,-8,-26,29,-85,-89,-88,-65,79,37,77})},
			{BitSet.valueOf(new byte[]{120,6,43,43,-118,50,-16,-124,-13,23,118,102,-26,-110,-83,-37}), BitSet.valueOf(new byte[]{9,-82,-10,22,126,-51,-31,-29,77,41,17,8,-65,-18,-68,-57}), BitSet.valueOf(new byte[]{32,74,117,-35,14,-40,-76,-123,118,124,14,20,-53,-82,-55,-83}), BitSet.valueOf(new byte[]{-8,-107,-20,-114,-51,-99,102,-41,-15,-99,-49,-45,-90,10,-35,1}), BitSet.valueOf(new byte[]{-71,38,-33,85,122,-103,21,-83,125,-15,113,-99,-122,6,6,23})},
			{BitSet.valueOf(new byte[]{-51,-87,-26,56,34,-100,103,65,4,-70,78,86,-99,77,10,12}), BitSet.valueOf(new byte[]{-28,-111,56,-33,108,2,-4,25,121,94,-109,-110,-88,9,48,96}), BitSet.valueOf(new byte[]{126,-85,9,-66,66,-82,111,-114,-101,-74,-73,98,10,57,74,61}), BitSet.valueOf(new byte[]{102,-59,111,95,28,62,-66,108,-50,5,84,-81,13,-18,-35,15}), BitSet.valueOf(new byte[]{51,-33,-41,-121,97,-71,106,16,-57,27,-42,-86,36,53,-58,-76})},
			{BitSet.valueOf(new byte[]{9,-82,39,27,119,-108,-106,-19,-89,43,70,-75,93,-106,10,86}), BitSet.valueOf(new byte[]{80,-113,-51,4,-32,-40,-23,-59,-53,-48,-36,-34,-52,120,-22,-94}), BitSet.valueOf(new byte[]{124,-94,21,94,-38,105,32,94,27,-3,-66,5,40,94,-80,-32}), BitSet.valueOf(new byte[]{-104,-80,-55,-9,-36,50,54,-34,-112,105,42,23,68,76,-34,-62}), BitSet.valueOf(new byte[]{-84,-87,123,74,-109,68,125,-43,-127,49,-62,111,-122,-10,-112,65})},
			{BitSet.valueOf(new byte[]{48,67,90,-113,-46,-15,-70,92,-86,-2,64,-44,32,44,-77,-122}), BitSet.valueOf(new byte[]{33,123,-101,80,-34,-88,-100,-61,-53,-15,-7,87,8,-84,86,32}), BitSet.valueOf(new byte[]{-114,119,-84,49,80,59,28,-23,-84,72,108,25,-47,64,-78,-110}), BitSet.valueOf(new byte[]{73,34,119,52,101,-8,-92,-29,-47,46,73,31,-28,-76,-34,-4}), BitSet.valueOf(new byte[]{22,14,-10,114,90,-73,-15,127,-39,57,16,122,-10,-36,-99,-96})},
			{BitSet.valueOf(new byte[]{67,-82,47,0,-96,-6,-118,24,73,125,-115,-28,23,-117,59,52}), BitSet.valueOf(new byte[]{-1,-98,18,111,-40,14,108,-16,-69,12,-115,-21,26,15,46,-64}), BitSet.valueOf(new byte[]{-37,66,-68,108,28,-95,78,-28,72,56,-27,-92,42,-111,-28,119}), BitSet.valueOf(new byte[]{14,-14,-71,-39,80,10,-113,46,72,80,-82,52,-107,77,-50,-77}), BitSet.valueOf(new byte[]{-42,55,-59,-54,-101,39,38,-14,57,56,-48,-33,46,-126,15,17})}
	};

	public King(int x, int y, boolean white) {
		super(white ? 'K' : 'k');
		value = new double[][]{
				{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},
				{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},
				{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},
				{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},
				{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},
				{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY}
		};
	}

	@Override
	public Vector<Move> possibleMoves(int x, int y) {
		Vector moves = new Vector<>();

		// Cardinal directions
		if (x+1 <= 4 && !chess.isOwn(x+1, y)) moves.add(new Move(x, y, 1, 0));
		if (x-1 >= 0 && !chess.isOwn(x-1, y)) moves.add(new Move(x, y, -1, 0));
		if (y+1 <= 5 && !chess.isOwn(x, y+1)) moves.add(new Move(x, y, 0, 1));
		if (y-1 >= 0 && !chess.isOwn(x, y-1)) moves.add(new Move(x, y, 0, -1));

		// Diagonal directions
		if (x+1 <= 4 && y+1 <= 5 && !chess.isOwn(x+1, y+1)) moves.add(new Move(x, y, 1, 1));
		if (x+1 <= 4 && y-1 >= 0 && !chess.isOwn(x+1, y-1)) moves.add(new Move(x, y, 1, -1));
		if (x-1 >= 0 && y+1 <= 5 && !chess.isOwn(x-1, y+1)) moves.add(new Move(x, y, -1, 1));
		if (x-1 >= 0 && y-1 >= 0 && !chess.isOwn(x-1, y-1)) moves.add(new Move(x, y, -1, -1));

		return moves;
	}

	public static BitSet getZobrist(int x, int y) {
		return zobrist[y][x];
	}
}
