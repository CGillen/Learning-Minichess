package pieces;

import board.chess;

import java.util.BitSet;
import java.util.Vector;

/**
 * Created by Corey on 4/6/2016.
 */
public class Knight extends Piece {
	private static BitSet[][] zobrist = new BitSet[][]{
		{BitSet.valueOf(new byte[]{36,71,122,-66,95,-36,43,-98,78,96,29,-49,-74,106,-59,11}), BitSet.valueOf(new byte[]{-78,8,29,-93,75,-79,76,67,-63,89,26,-99,-5,-78,-32,-40}), BitSet.valueOf(new byte[]{56,-97,50,118,-60,-127,-77,88,-59,55,-124,49,11,93,-10,35}), BitSet.valueOf(new byte[]{56,-121,-127,-116,82,-122,-48,-30,-39,-7,-65,111,-43,39,-98,-61}), BitSet.valueOf(new byte[]{-34,-42,7,-122,-102,33,-7,-22,51,-13,-6,91,113,28,-105,-43})},
		{BitSet.valueOf(new byte[]{-111,-1,-94,-37,-45,-123,126,26,-29,-54,-73,-27,46,-26,120,22}), BitSet.valueOf(new byte[]{-89,-90,20,43,-122,6,-4,4,46,30,-99,-82,-88,-89,76,56}), BitSet.valueOf(new byte[]{-116,-104,-7,17,82,-65,77,-105,-125,-96,124,-23,72,69,-13,-62}), BitSet.valueOf(new byte[]{103,118,-30,117,-79,-106,-113,-9,7,-56,-119,-10,-14,66,72,51}), BitSet.valueOf(new byte[]{36,-61,-49,-13,12,-71,-68,-41,100,-127,122,-3,51,16,-85,81})},
		{BitSet.valueOf(new byte[]{-79,122,-24,117,111,72,-34,37,23,104,126,45,91,-77,109,-43}), BitSet.valueOf(new byte[]{113,117,104,20,-16,-77,-111,-42,87,-61,-115,-59,25,121,-33,93}), BitSet.valueOf(new byte[]{68,77,-87,-52,54,38,-98,37,80,-92,-66,111,126,-98,61,18}), BitSet.valueOf(new byte[]{6,100,-67,-116,-110,9,-68,87,-47,51,10,-124,-5,61,-79,39}), BitSet.valueOf(new byte[]{-113,20,96,12,79,-35,123,-21,2,-125,96,-29,-27,93,-23,27})},
		{BitSet.valueOf(new byte[]{-91,-17,-55,-5,82,-39,-83,107,-82,60,-57,-120,47,-3,-51,-94}), BitSet.valueOf(new byte[]{-119,40,-60,-62,-37,1,79,97,42,63,-60,78,-70,-98,-43,69}), BitSet.valueOf(new byte[]{-13,-10,110,-19,-63,-98,-57,-102,-56,-79,35,-79,49,-125,-26,-98}), BitSet.valueOf(new byte[]{78,-10,-89,35,-111,-125,-103,95,53,-38,0,0,98,-84,-93,-23}), BitSet.valueOf(new byte[]{-112,-62,-7,-13,43,6,-54,93,34,82,-80,31,-98,-102,-75,95})},
		{BitSet.valueOf(new byte[]{-84,-59,0,84,113,24,-17,-41,106,126,5,99,-106,-37,-93,-19}), BitSet.valueOf(new byte[]{33,86,97,39,95,-109,-71,70,-37,56,-104,-29,-117,-61,16,-56}), BitSet.valueOf(new byte[]{-2,-125,-118,-123,-98,47,-107,-50,23,60,-65,10,25,37,90,99}), BitSet.valueOf(new byte[]{91,2,-64,88,44,-64,-58,-38,29,-111,120,-31,45,-74,113,-61}), BitSet.valueOf(new byte[]{127,6,-37,-100,-93,61,-123,-84,28,-28,37,94,54,21,-66,124})},
		{BitSet.valueOf(new byte[]{48,9,-37,-40,-37,-5,-9,-110,-59,-72,-98,-116,23,-92,122,-11}), BitSet.valueOf(new byte[]{42,101,-6,98,92,122,84,118,-86,-79,-69,35,95,-39,57,99}), BitSet.valueOf(new byte[]{122,106,1,-66,-90,-125,-47,19,126,12,80,33,-81,-100,50,7}), BitSet.valueOf(new byte[]{120,67,-44,-100,-22,107,115,-27,-66,-41,38,93,-27,99,32,76}), BitSet.valueOf(new byte[]{30,-69,-113,86,-93,-111,33,18,35,-26,-120,51,87,57,-92,-25})}
	};

	public Knight(int x, int y, boolean white) {
		super(white ? 'N' : 'n');
		value = new double[][]{
				{20, 20, 20, 20, 20},
				{20, 35, 40, 35, 20},
				{20, 40, 60, 40, 20},
				{20, 40, 60, 40, 20},
				{20, 35, 40, 35, 20},
				{20, 20, 20, 20, 20},
		};
	}

	@Override
	public Vector<Move> possibleMoves(int x, int y) {
		Vector moves = new Vector<>();

		if (x+1 <= 4 && y+2 <= 5 && !chess.isOwn(x+1, y+2)) moves.add(new Move(x, y, 1, 2));
		if (x+1 <= 4 && y-2 >= 0 && !chess.isOwn(x+1, y-2)) moves.add(new Move(x, y, 1, -2));
		if (x-1 >= 0 && y+2 <= 5 && !chess.isOwn(x-1, y+2)) moves.add(new Move(x, y, -1, 2));
		if (x-1 >= 0 && y-2 >= 0 && !chess.isOwn(x-1, y-2)) moves.add(new Move(x, y, -1, -2));
		if (x+2 <= 4 && y+1 <= 5 && !chess.isOwn(x+2, y+1)) moves.add(new Move(x, y, 2, 1));
		if (x+2 <= 4 && y-1 >= 0 && !chess.isOwn(x+2, y-1)) moves.add(new Move(x, y, 2, -1));
		if (x-2 >= 0 && y+1 <= 5 && !chess.isOwn(x-2, y+1)) moves.add(new Move(x, y, -2, 1));
		if (x-2 >= 0 && y-1 >= 0 && !chess.isOwn(x-2, y-1)) moves.add(new Move(x, y, -2, -1));

		return moves;
	}

	public static BitSet getZobrist(int x, int y) {
		return zobrist[y][x];
	}
}
