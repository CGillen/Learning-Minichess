package pieces;

import board.chess;

import java.util.BitSet;
import java.util.Vector;

/**
 * Created by Corey on 4/6/2016.
 */
public class Queen extends Piece {

	private static BitSet[][]	zobrist = new BitSet[][]{
			{BitSet.valueOf(new byte[]{-79,52,-100,12,90,16,39,49,-50,-94,48,-2,62,41,11,99}), BitSet.valueOf(new byte[]{-108,-117,-62,107,-71,-1,-96,122,9,60,-101,68,54,-110,17,110}), BitSet.valueOf(new byte[]{99,101,-86,-84,57,-35,-26,18,-23,64,104,73,-50,-64,103,-109}), BitSet.valueOf(new byte[]{97,79,111,-39,79,-44,-65,45,42,-90,102,64,-117,102,-67,3}), BitSet.valueOf(new byte[]{1,7,113,-24,97,-6,-16,107,80,68,100,34,69,-6,-37,105})},
			{BitSet.valueOf(new byte[]{117,-98,-35,22,-87,-67,69,-101,91,-109,55,67,33,-22,-108,23}), BitSet.valueOf(new byte[]{94,-1,36,-50,-97,35,8,68,77,62,-124,-92,-122,112,87,-40}), BitSet.valueOf(new byte[]{32,127,11,76,-116,59,-78,-70,-67,-32,10,-70,-26,31,123,110}), BitSet.valueOf(new byte[]{-49,-99,108,-121,-17,-87,-23,-3,-110,-6,-70,-63,42,98,80,-95}), BitSet.valueOf(new byte[]{-51,-43,-3,67,79,55,-109,109,-61,-44,-119,-54,55,-75,-128,-13})},
			{BitSet.valueOf(new byte[]{-26,35,-124,-29,-77,73,70,-25,22,87,-35,-92,46,-126,-63,-72}), BitSet.valueOf(new byte[]{-67,-124,102,-96,-29,-108,69,3,85,125,105,93,37,-1,63,122}), BitSet.valueOf(new byte[]{87,42,8,93,79,1,-5,-109,34,-102,-105,-15,-44,23,-81,-79}), BitSet.valueOf(new byte[]{11,37,-110,-121,51,-51,-70,4,-77,-76,40,15,-115,107,62,-113}), BitSet.valueOf(new byte[]{7,-97,124,44,114,-5,80,-101,89,-35,-56,108,-11,34,-5,21})},
			{BitSet.valueOf(new byte[]{64,82,-13,-26,8,52,11,-15,5,127,-110,-85,-101,-48,121,-49}), BitSet.valueOf(new byte[]{30,-85,72,8,32,60,97,115,71,-56,-126,-28,-95,103,22,97}), BitSet.valueOf(new byte[]{25,-42,-28,8,-62,-119,-60,-34,97,41,-101,-28,106,-69,-5,-114}), BitSet.valueOf(new byte[]{-59,-112,-57,-33,82,89,-7,120,22,59,-4,56,-13,75,-38,83}), BitSet.valueOf(new byte[]{-8,-20,-48,-107,37,57,78,65,-47,-64,29,-127,-37,-17,44,16})},
			{BitSet.valueOf(new byte[]{-71,110,-6,-103,43,85,-71,-124,10,-41,10,-73,-10,-115,0,-56}), BitSet.valueOf(new byte[]{-93,-38,-1,-5,-41,-98,122,-103,-35,-12,35,-42,114,9,-62,111}), BitSet.valueOf(new byte[]{-87,103,-94,56,0,-95,-47,-11,100,-23,1,22,49,-93,-10,-73}), BitSet.valueOf(new byte[]{84,-109,78,82,-80,44,-96,90,11,24,-125,-84,-26,31,-45,114}), BitSet.valueOf(new byte[]{59,82,-5,14,43,-81,-119,109,102,30,81,91,32,-64,-42,-10})},
			{BitSet.valueOf(new byte[]{-10,95,-108,-11,-108,24,72,118,101,-65,-46,11,4,114,44,-100}), BitSet.valueOf(new byte[]{-110,48,-20,-3,71,69,-29,-11,43,45,14,-80,82,-21,69,-41}), BitSet.valueOf(new byte[]{-17,31,-44,-56,94,-60,-105,118,84,-31,126,-70,51,98,117,-5}), BitSet.valueOf(new byte[]{124,-2,-43,-75,91,-48,44,92,-90,48,-126,103,-78,-33,14,-38}), BitSet.valueOf(new byte[]{-45,16,28,47,-50,2,54,90,17,41,113,-43,-68,-24,8,-1})}
	};

	public Queen(int x, int y, boolean white) {
		super(white ? 'Q' : 'q');
		value = new double[][]{
				{60, 50, 40, 50, 60},
				{50, 60, 50, 60, 50},
				{40, 50, 60, 50, 40},
				{40, 50, 60, 50, 40},
				{50, 60, 50, 60, 50},
				{60, 50, 40, 50, 60},
		};
	}

	@Override
	public Vector<Move> possibleMoves(int x, int y) {
		Vector moves = new Vector<>();
		boolean ur = true;
		boolean ul = true;
		boolean dr = true;
		boolean dl = true;
		boolean u = true;
		boolean d = true;
		boolean r = true;
		boolean l = true;

		for (int i = 1; i <= 5; ++i) {
			// Diagonal moves
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
			// Cardinal moves
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
