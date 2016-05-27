package pieces;

import board.chess;

import java.util.BitSet;
import java.util.Vector;

/**
 * Created by Corey on 4/6/2016.
 */
public class Rook extends Piece {

	private static BitSet[][]	zobrist = new BitSet[][]{
			{BitSet.valueOf(new byte[]{-45,-102,-17,-121,63,-70,91,0,-63,-73,-13,-60,62,90,-118,-26}), BitSet.valueOf(new byte[]{101,-54,6,33,-87,80,77,91,-28,-14,86,-45,97,86,-59,33}), BitSet.valueOf(new byte[]{-108,-75,123,-51,38,118,-29,-105,56,52,110,35,-122,-55,78,-64}), BitSet.valueOf(new byte[]{-19,-71,-45,92,118,76,37,-19,14,19,92,-53,53,-110,71,119}), BitSet.valueOf(new byte[]{-85,115,-110,60,-122,-24,8,11,-121,14,-60,-89,109,123,-77,6})},
			{BitSet.valueOf(new byte[]{68,38,29,-9,-97,48,-11,-118,-57,-61,-1,73,119,-108,125,-102}), BitSet.valueOf(new byte[]{-128,66,105,65,-19,31,-20,-92,-43,104,62,-104,17,72,124,81}), BitSet.valueOf(new byte[]{8,90,-67,-105,60,35,45,-13,67,96,-19,-57,-123,-73,21,-100}), BitSet.valueOf(new byte[]{-39,-77,-93,72,95,-79,-14,13,103,82,-19,-19,33,81,-9,-52}), BitSet.valueOf(new byte[]{84,-90,118,-43,-84,32,44,93,-10,112,68,108,-34,59,118,14})},
			{BitSet.valueOf(new byte[]{-83,4,-25,-33,25,-81,-116,77,109,-128,95,-120,-55,-122,-12,-19}), BitSet.valueOf(new byte[]{-126,-29,-42,-40,-7,-89,-90,27,-88,9,120,111,-79,-43,-103,-71}), BitSet.valueOf(new byte[]{-55,88,65,-28,-34,-7,-107,-97,26,124,-122,-8,-66,-26,72,63}), BitSet.valueOf(new byte[]{-96,-97,45,67,91,-112,-85,75,120,51,121,-98,56,61,100,48}), BitSet.valueOf(new byte[]{119,119,-106,56,-26,-40,-26,-53,83,-104,29,15,-12,80,-53,-5})},
			{BitSet.valueOf(new byte[]{39,35,68,75,97,101,65,116,-44,-120,-38,68,22,11,-113,-62}), BitSet.valueOf(new byte[]{83,-30,11,-45,-56,105,79,-96,-76,23,-28,-95,98,0,37,-66}), BitSet.valueOf(new byte[]{120,39,-86,-36,-102,-62,-85,30,-62,-17,36,-85,103,76,78,100}), BitSet.valueOf(new byte[]{-15,-14,67,49,127,-114,56,-2,-65,9,-65,-103,114,121,11,-11}), BitSet.valueOf(new byte[]{-102,-55,-43,24,41,-66,-86,-82,41,115,94,-26,5,-82,-47,63})},
			{BitSet.valueOf(new byte[]{119,-27,110,6,-28,-122,-15,-42,-5,96,43,41,-74,-45,-72,-120}), BitSet.valueOf(new byte[]{-103,121,99,-94,-121,-61,38,-105,66,-95,-114,-61,-19,110,-26,-33}), BitSet.valueOf(new byte[]{-23,-24,-98,93,94,74,-89,58,116,-64,21,89,-102,114,-80,-9}), BitSet.valueOf(new byte[]{-33,-73,111,-127,62,47,-22,-19,-25,124,55,108,11,64,-92,58}), BitSet.valueOf(new byte[]{-87,10,43,-6,-68,-103,-107,-27,81,-87,-126,-10,119,-44,127,-66})},
			{BitSet.valueOf(new byte[]{-87,-65,-1,115,56,-6,21,-13,-101,-1,-112,9,-82,-36,-97,92}), BitSet.valueOf(new byte[]{36,-41,126,-4,-47,119,-36,-53,121,71,-11,85,6,81,-43,3}), BitSet.valueOf(new byte[]{-4,117,-83,-19,87,-80,-124,-92,112,29,-23,54,41,-51,20,-114}), BitSet.valueOf(new byte[]{40,-108,-89,82,-33,53,-14,111,48,68,48,94,-14,19,12,-59}), BitSet.valueOf(new byte[]{-78,50,-66,54,48,31,-64,-48,-19,117,-121,-87,-8,102,-4,24})}
	};

	public Rook(int x, int y, boolean white) {
		super(white ? 'R' : 'r');
		value = new double[][]{
				{20, 20, 20, 20, 20},
				{20, 30, 30, 30, 30},
				{20, 30, 40, 30, 20},
				{20, 30, 40, 30, 20},
				{30, 30, 30, 30, 30},
				{30, 30, 30, 30, 30},
		};
	}

	@Override
	public Vector<Move> possibleMoves(int x, int y) {
		Vector moves = new Vector<>();
		boolean u = true;
		boolean d = true;
		boolean r = true;
		boolean l = true;

		for (int i = 1; i <= 5; ++i) {
			if (x+i <= 4 && r) {
				if (!chess.isOwn(x+i,y)) moves.add(new Move(x, y, i, 0));
				if (!chess.isNothing(x+i,y)) r = false;
			}
			if (x-i >= 0 && l) {
				if (!chess.isOwn(x-i,y)) moves.add(new Move(x, y, -i, 0));
				if (!chess.isNothing(x-i,y)) l = false;
			}
			if (y+i <= 5 && u) {
				if (!chess.isOwn(x,y+i)) moves.add(new Move(x, y, 0, i));
				if (!chess.isNothing(x,y+i)) u = false;
			}
			if (y-i >= 0 && d) {
				if (!chess.isOwn(x,y-i)) moves.add(new Move(x, y, 0, -i));
				if (!chess.isNothing(x,y-i)) d = false;
			}
		}

		return moves;
	}

	public static BitSet getZobrist(int x, int y) {
		return zobrist[y][x];
	}
}
